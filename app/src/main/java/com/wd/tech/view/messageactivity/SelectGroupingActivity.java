package com.wd.tech.view.messageactivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserAllGroupingadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.messagebean.DeleteFriendGroupingBean;
import com.wd.tech.bean.messagebean.SetCustomFriendGroupingBean;
import com.wd.tech.bean.messagebean.ShiftFriendGroupingBean;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventgroupid;
import com.wd.tech.presenter.Presenter;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//选择分组
public class SelectGroupingActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.btn_addgroup)
    Button btnAddgroup;
    @BindView(R.id.grouplist)
    SwipeRecyclerView grouplist;
    private int userId3;
    private MyUserAllGroupingadapter myUserAllGroupingadapter;
    private List<UserAllGroupingBean.ResultBean> userallgroupingresult;
    private PopupWindow popupWindow;
    private EditText group_name,update_friendmessage;
    private  int madapterPosition;
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
        grouplist.setSwipeItemMenuEnabled(true);
        grouplist.setSwipeMenuCreator(swipeMenuCreator);
        // 菜单点击监听。
        grouplist.setOnItemMenuClickListener(mMenuItemClickListener);
    }
//创建菜单
    SwipeMenuCreator swipeMenuCreator =new SwipeMenuCreator() {
    @Override
    public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
        SwipeMenuItem updateitem=  new SwipeMenuItem(SelectGroupingActivity.this);
        updateitem.setBackgroundColor(Color.BLUE)
                .setText("修改群组名")
                .setTextColor(Color.WHITE)
                .setTextSize(15) // 文字大小。
                .setWidth(180)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        rightMenu.addMenuItem(updateitem);
        SwipeMenuItem deleteitem=  new SwipeMenuItem(SelectGroupingActivity.this);
        deleteitem.setBackgroundColor(Color.RED)
                .setText("删除群组")
                .setTextColor(Color.WHITE)
                .setTextSize(15) // 文字大小。
                .setWidth(180)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        rightMenu.addMenuItem(deleteitem);
    }
};
//菜单监听器
    OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {

    @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
        menuBridge.closeMenu();
       int position = menuBridge.getDirection();
        int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            if (menuPosition == 0) {
                madapterPosition=adapterPosition;
                showupdatenamePopupWindow();

            } else {
                    presenter.getDeleteFriendGroupingBeandata(userallgroupingresult.get(adapterPosition).getGroupId());
            }
        }




};

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
            if (((ShiftFriendGroupingBean) o).getStatus().equals("0000")) {
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupingadapter.notifyDataSetChanged();
            }
        }
        if (o instanceof SetCustomFriendGroupingBean) {
            Toast.makeText(App.context, ((SetCustomFriendGroupingBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((SetCustomFriendGroupingBean) o).getStatus().equals("0000")) {
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupingadapter.notifyDataSetChanged();
            }
        }
        if (o instanceof DeleteFriendGroupingBean) {
            Toast.makeText(App.context, ((DeleteFriendGroupingBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((DeleteFriendGroupingBean) o).getStatus().equals("0000")){
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupingadapter.notifyDataSetChanged();
            }
        }


        if (o instanceof AlterFriendGroupingNameBean) {
            Toast.makeText(App.context, ((AlterFriendGroupingNameBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((AlterFriendGroupingNameBean) o).getStatus().equals("0000")){
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupingadapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick(R.id.btn_addgroup)
    public void onViewClicked() {
        showgroupPopupWindow();
    }

    //创建新分组并添加
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
    //修改组名
    private void showupdatenamePopupWindow() {
        View contentView = LayoutInflater.from(SelectGroupingActivity.this).inflate(R.layout.update_crowd_name, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_update = (Button) contentView.findViewById(R.id.btn_update);
        Button btn_updateno = (Button) contentView.findViewById(R.id.btn_updateno);
        update_friendmessage = (EditText) contentView.findViewById(R.id.update_friendmessage);
        btn_update.setOnClickListener(this);
        btn_updateno.setOnClickListener(this);
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
            case R.id.btn_update: {
                String update_friend = update_friendmessage.getText().toString().trim();
                presenter.getAlterFriendGroupingNameBeandata(userallgroupingresult.get(madapterPosition).getGroupId(), update_friend);
                popupWindow.dismiss();
            }
            break;
            case R.id.btn_updateno: {
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
