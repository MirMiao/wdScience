package com.wd.tech.view.information;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.InfoRecommendListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.lang.reflect.Type;
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
    private List<LoginEntity.ResultBean> list1;
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
        try {
            SharedPreferences sp =getSharedPreferences("user",MODE_PRIVATE);
            String userInfo = sp.getString("userInfo", "");
            Gson gson = new Gson();
            Type type = new TypeToken<List<LoginEntity.ResultBean>>() {
            }.getType();
            list1 = gson.fromJson(userInfo, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        int id = getIntent().getIntExtra("id", 0);
        if (list1 != null) {
            presenter.getInfoRecommendListData(list1.get(0).getUserId(),list1.get(0).getSessionId(),id,1,10);
        }else{
            presenter.getInfoRecommendListData(0,"",id,1,10);
        }

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
                if (list1 != null) {
                    presenter.getInfoRecommendListData(list1.get(0).getUserId(),list1.get(0).getSessionId(),id, i, 10);

                }else{
                    presenter.getInfoRecommendListData(0,"",i, 1, 10);
                }
                //刷新完毕
                smartRefresh.finishRefresh();
            }
        });
        //上拉加载
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                i++;
                if (list1 != null) {
                    presenter.getInfoRecommendListData(list1.get(0).getUserId(),list1.get(0).getSessionId(),id, i, 10);
                }else{
                    presenter.getInfoRecommendListData(0,"",i, 1, 10);
                }

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
