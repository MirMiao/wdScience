package com.wd.tech.view.main;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MyFollowUserData;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

/*
 * 关注
 * */

public class MyFollowUserActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView followFanhui;
    private androidx.recyclerview.widget.RecyclerView followRecy;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_follow_user;
    }

    @Override
    protected void initData() {
        presenter.getMyFollowUser(1,10);
    }

    @Override
    protected void initView() {

        followFanhui = (ImageView) findViewById(R.id.follow_fanhui);
        followRecy = (RecyclerView) findViewById(R.id.follow_recy);

        followFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        followRecy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(Object o) {
        if(o instanceof MyFollowUserData){

        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
