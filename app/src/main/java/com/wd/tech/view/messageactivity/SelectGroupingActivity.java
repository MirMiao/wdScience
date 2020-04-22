package com.wd.tech.view.messageactivity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserAllGroupingadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.SetCustomFriendGroupingBean;
import com.wd.tech.bean.ShiftFriendGroupingBean;
import com.wd.tech.bean.UserAllGroupingBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventgroupid;
import com.wd.tech.presenter.Presenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
//选择分组
public class SelectGroupingActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.btn_addgroup)
    Button btnAddgroup;
    @BindView(R.id.grouplist)
    RecyclerView grouplist;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private int userId3;
    private MyUserAllGroupingadapter myUserAllGroupingadapter;
    private List<UserAllGroupingBean.ResultBean> userallgroupingresult;
    private PopupWindow popupWindow;
    private EditText group_name;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_select_grouping;
    }

    @Override
    protected void initData() {
        userId3 = getIntent().getIntExtra("userId3", 0);
        presenter.getUserAllGroupingBeandata();
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupingadapter.update(userallgroupingresult);
            }
        });
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    public void back(View view) {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEventid(Eventgroupid eventgroupid) {
        presenter.getShiftFriendGroupingBeandata(eventgroupid.groupid, userId3);
    }

    @Override
    public void success(Object o) {
        if (o instanceof UserAllGroupingBean) {
            userallgroupingresult = ((UserAllGroupingBean) o).getResult();
            myUserAllGroupingadapter = new MyUserAllGroupingadapter(userallgroupingresult, this);
            grouplist.setAdapter(myUserAllGroupingadapter);
            grouplist.setLayoutManager(new LinearLayoutManager(this));
        }
        if (o instanceof ShiftFriendGroupingBean) {
            Toast.makeText(App.context, ((ShiftFriendGroupingBean) o).getMessage(), Toast.LENGTH_LONG).show();
        }
        if (o instanceof SetCustomFriendGroupingBean) {
            Toast.makeText(App.context, ((SetCustomFriendGroupingBean) o).getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick(R.id.btn_addgroup)
    public void onViewClicked() {
        showgroupPopupWindow();
    }

    //c创建新分组并添加
    private void showgroupPopupWindow() {
        View contentView = LayoutInflater.from(SelectGroupingActivity.this).inflate(R.layout.add_newgroup_item, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_yesgroup = (Button) contentView.findViewById(R.id.btn_yesgroup);
        Button btn_nogroup = (Button) contentView.findViewById(R.id.btn_nogroup);
        group_name = (EditText) contentView.findViewById(R.id.group_name);
        btn_yesgroup.setOnClickListener(this);
        btn_nogroup.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(SelectGroupingActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_yesgroup: {
                String newgroup_name = group_name.getText().toString().trim();
                presenter.getSetCustomFriendGroupingBeandata(newgroup_name);
                popupWindow.dismiss();
            }
            break;
            case R.id.btn_nogroup: {
                popupWindow.dismiss();
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
