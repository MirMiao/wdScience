package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdInfromactadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CheckCrowdApplyBean;
import com.wd.tech.bean.messagebean.CrowdInfromBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.TimeformatUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//群通知
public class CrowdInformActivity extends BaseActivity<Presenter> implements IContract.IView {


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.crowdinfrom_RecyclerView)
    RecyclerView crowdinfromRecyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private List<CrowdInfromBean.ResultBean> crowdinfromresult;
    private MyCrowdInfromactadapter myCrowdInfromactadapter;
    private int noticeId;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_inform;
    }

    @Override
    protected void initData() {
        presenter.getCrowdInfromBeandata(1, 5);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getCrowdInfromBeandata(1, 5);
                myCrowdInfromactadapter.update(crowdinfromresult);
            }
        });
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvnet(Eventstuast eventstuast) {
        presenter.getCheckCrowdApplyBeandata(noticeId, eventstuast.id);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowdInfromBean) {
            crowdinfromresult = ((CrowdInfromBean) o).getResult();
            myCrowdInfromactadapter = new MyCrowdInfromactadapter(crowdinfromresult, this);
            for (int i = 0; i < crowdinfromresult.size(); i++) {
                time.setText(TimeformatUtil.gettime(crowdinfromresult.get(i).getNoticeTime()));
                noticeId = crowdinfromresult.get(i).getNoticeId();
            }
            crowdinfromRecyclerView.setAdapter(myCrowdInfromactadapter);
            crowdinfromRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        if (o instanceof CheckCrowdApplyBean) {
            Toast.makeText(App.context, ((CheckCrowdApplyBean) o).getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


}
