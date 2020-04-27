package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserFriendInfromRecordadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CheckFriendApplyBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//好友通知
public class FriendInformActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.checkfriendapply_RecyclerView)
    RecyclerView checkfriendapplyRecyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private int noticeId;
    private MyUserFriendInfromRecordadapter myUserFriendInfromRecordadapter;
    private List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult;
    private int count=1;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_friend_inform;
    }


    @Override
    protected void initData() {
        noticeId = getIntent().getIntExtra("noticeId", 0);
        presenter.getUserFriendInfromRecorddata(1, count);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                count++;
                presenter.getUserFriendInfromRecorddata(1, count);
                myUserFriendInfromRecordadapter.loadmore(userfriendinfromrecordresult);
                refresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                count=1;
                presenter.getUserFriendInfromRecorddata(1, count);
                myUserFriendInfromRecordadapter.refresh(userfriendinfromrecordresult);
                refresh.finishRefresh();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(Eventstuast eventstuast) {
        presenter.getCheckFriendApplydata(noticeId, eventstuast.id);
        myUserFriendInfromRecordadapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendInfromRecordBean) {
            userfriendinfromrecordresult= ((UserFriendInfromRecordBean) o).getResult();
            for (int i = 0; i < userfriendinfromrecordresult.size(); i++) {
                myUserFriendInfromRecordadapter = new MyUserFriendInfromRecordadapter(userfriendinfromrecordresult, this);
                checkfriendapplyRecyclerView.setAdapter(myUserFriendInfromRecordadapter);
                checkfriendapplyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            }

        }

        if (o instanceof CheckFriendApplyBean) {
            if (((CheckFriendApplyBean) o).getStatus().equals("0000")) {
                Toast.makeText(App.context, ((CheckFriendApplyBean) o).getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
