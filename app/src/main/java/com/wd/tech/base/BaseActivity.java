package com.wd.tech.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ProjectName: wdmovie
 * PackageName: com.bw.movie.base.base
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: wyq
 * CreateDate: 2020/03/19_下午 07:07
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //找布局id
        setContentView(bindLayoutId());
        getSupportActionBar().hide();
        mBind = ButterKnife.bind(this);
         presenter=initPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    protected abstract P initPresenter();

    protected abstract int bindLayoutId();


    protected abstract void initData();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
