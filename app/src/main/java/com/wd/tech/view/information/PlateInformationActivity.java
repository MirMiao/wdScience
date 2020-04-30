package com.wd.tech.view.information;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.InfoRecommendListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/28  21:08
 * 作者 :苗恒
 * 功能 :
 */
public class PlateInformationActivity extends BaseActivity<Presenter> implements IContract.IView {
    List<InfoRecommendListEntity.ResultBean> list = new ArrayList<>();
    @BindView(R.id.rv_information)
    RecyclerView rvInformation;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    int i=1;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_plateinformation;
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        presenter.getInfoRecommendListData(id,1,10);
        rvInformation.setLayoutManager(new LinearLayoutManager(this));
        //设置允许为可上拉加载
        smartRefresh.setEnableLoadMore(true);
        smartRefresh.setEnableRefresh(true);
        //下拉刷新
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                i = 1;
                list.clear();
                presenter.getInfoRecommendListData(id, i, 10);
                //刷新完毕
                smartRefresh.finishRefresh();
            }
        });
        //上拉加载
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                i++;
                presenter.getInfoRecommendListData(id, i, 10);
                //加载完毕
                smartRefresh.finishLoadMore();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {
            if (o instanceof InfoRecommendListEntity) {
            InfoRecommendListEntity o1 = (InfoRecommendListEntity) o;
            list.addAll(o1.getResult());
            InfoRecommendListAdapter infoRecommendListAdapter = new InfoRecommendListAdapter(PlateInformationActivity.this, list);
            rvInformation.setAdapter(infoRecommendListAdapter);
            infoRecommendListAdapter.setOnItemClickListener(new InfoRecommendListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int getInformationId) {
                    //
                    Intent intent = new Intent(PlateInformationActivity.this, InformationInfoActivity.class);
                    intent.putExtra("id",getInformationId);
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


}
