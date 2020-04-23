package com.wd.tech.view.messageactivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserAllGroupSttingadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AlterFriendGroupingNameBean;
import com.wd.tech.bean.messagebean.DeleteFriendGroupingBean;
import com.wd.tech.bean.messagebean.UserAllGroupingBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventgroupid;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class GroupSttingActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.group_sttingRecyclerView)
    RecyclerView groupSttingRecyclerView;
    @BindView(R.id.updategroupname)
    EditText updategroupname;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private List<UserAllGroupingBean.ResultBean> userallgroupresult;
    private MyUserAllGroupSttingadapter myUserAllGroupStting;
    private String trim;

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
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getUserAllGroupingBeandata();
                myUserAllGroupStting.update(userallgroupresult);
            }
        });
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEventgroupid(Eventgroupid eventgroupid) {
        trim = updategroupname.getText().toString().trim();
        if (trim != null) {
            presenter.getAlterFriendGroupingNameBeandata(eventgroupid.groupid, trim);
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void onEventid(Eventstuast eventstuast) {
        presenter.getDeleteFriendGroupingBeandata(eventstuast.id);
    }

    public void back(View view) {
        finish();
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
        }
        if (o instanceof AlterFriendGroupingNameBean) {
            Toast.makeText(App.context, ((AlterFriendGroupingNameBean) o).getMessage(), Toast.LENGTH_LONG).show();
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

}
