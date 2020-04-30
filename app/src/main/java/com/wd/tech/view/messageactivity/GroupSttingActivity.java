package com.wd.tech.view.messageactivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserAllGroupSttingadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.messagebean.DeleteFriendGroupingBean;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventgroupdeleteid;
import com.wd.tech.event.Eventgroupupdateid;
import com.wd.tech.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupSttingActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.group_sttingRecyclerView)
    RecyclerView groupSttingRecyclerView;
    @BindView(R.id.updategroupname)
    EditText updategroupname;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    private List<UserAllGroupingBean.ResultBean> userallgroupresult;
    private MyUserAllGroupSttingadapter myUserAllGroupStting;
    private String trim;
    private int groupupdateid;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_group_stting;
    }

    @Override
    protected void initData() {
        presenter.getUserAllGroupingBeandata();
    }
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    public void back(View view) {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEventgroupid(Eventgroupupdateid eventgroupupdateid) {

    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void onEventid(Eventgroupdeleteid eventgroupdeleteid) {

    }
    @Override
    public void success(Object o) {
        if (o instanceof UserAllGroupingBean) {
            userallgroupresult = ((UserAllGroupingBean) o).getResult();
            myUserAllGroupStting = new MyUserAllGroupSttingadapter(userallgroupresult, this);
            groupSttingRecyclerView.setAdapter(myUserAllGroupStting);
            groupSttingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (o instanceof DeleteFriendGroupingBean) {
            Toast.makeText(App.context, ((DeleteFriendGroupingBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((DeleteFriendGroupingBean) o).getStatus().equals("0000")){
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupStting.notifyDataSetChanged();
            }
        }
        if (o instanceof AlterFriendGroupingNameBean) {
            Toast.makeText(App.context, ((AlterFriendGroupingNameBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((AlterFriendGroupingNameBean) o).getStatus().equals("0000")){
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupStting.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        trim = updategroupname.getText().toString().trim();
        if (trim != null) {

        }
    }
}
