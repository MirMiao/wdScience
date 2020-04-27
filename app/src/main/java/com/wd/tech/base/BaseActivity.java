package com.wd.tech.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.base.mvp.IBaseView;
import com.wd.tech.view.fragment.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int closeType = intent.getIntExtra("closeType", 1);
        if(closeType==1){
            Intent intent1=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }

    }
}
