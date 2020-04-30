package com.wd.tech.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.androidx.XBanner;
import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.InfoRecommendListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.informationentity.AddGreatRecordEntity;
import com.wd.tech.bean.informationentity.BannerEntity;
import com.wd.tech.bean.informationentity.InfoRecommendListEntity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.view.information.InformationInfoActivity;
import com.wd.tech.view.information.PlateActivity;
import com.wd.tech.view.information.SerchActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InformationFragment extends BaseFragment<Presenter> implements IContract.IView {
    List<InfoRecommendListEntity.ResultBean> list = new ArrayList<>();
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.xbaner)
    XBanner xbaner;
    @BindView(R.id.tv_xbannerHint)
    TextView tvXbannerHint;
    @BindView(R.id.rv_information)
    RecyclerView rvInformation;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefresh;
    int i = 1;
    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initView(View inflate) {
        rvInformation.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置允许为可上拉加载
        smartRefresh.setEnableLoadMore(true);
        smartRefresh.setEnableRefresh(true);
        //下拉刷新
        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                i = 1;
                list.clear();
                presenter.getInfoRecommendListData(i, 1, 10);
                //刷新完毕
                smartRefresh.finishRefresh();
            }
        });
        //上拉加载
        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                i++;
                presenter.getInfoRecommendListData(i, 1, 10);
                //加载完毕
                smartRefresh.finishLoadMore();
            }
        });
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        presenter.getBannerData(); //请求banner数据
        presenter.getInfoRecommendListData(i, 1, 10);
    }

    @Override
    public void success(Object o) {
        if (o instanceof BannerEntity) {
            List<BannerEntity.ResultBean> result = ((BannerEntity) o).getResult();
            xbaner.setIsClipChildrenMode(true);  //开启一瓶多图片模式
            xbaner.setPointsIsVisible(false);  //设置指示器
            xbaner.setBannerData(result);
            xbaner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getContext()).load(result.get(position).getImageUrl())
                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(8))) //圆角图
                            .error(R.mipmap.ic_launcher)
                            .placeholder(R.mipmap.ic_launcher_round)
                            .into((ImageView) view);
                    tvXbannerHint.setText(result.get(position).getTitle());
                }
            });

        } else if (o instanceof InfoRecommendListEntity) {
            InfoRecommendListEntity o1 = (InfoRecommendListEntity) o;
            list.addAll(o1.getResult());
            InfoRecommendListAdapter infoRecommendListAdapter = new InfoRecommendListAdapter(getContext(), list);
            rvInformation.setAdapter(infoRecommendListAdapter);
            infoRecommendListAdapter.setOnItemClickListener(new InfoRecommendListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int getInformationId) {
                    Intent intent = new Intent(getContext(), InformationInfoActivity.class);
                    intent.putExtra("id",getInformationId);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick({R.id.iv_menu, R.id.iv_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:  //跳转到菜单的atcitivy
                startActivity(new Intent(getContext(), PlateActivity.class));
                break;
            case R.id.iv_serch:
                startActivity(new Intent(getContext(), SerchActivity.class));
                break;
        }
    }


}
