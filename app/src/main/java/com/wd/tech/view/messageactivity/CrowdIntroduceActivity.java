package com.wd.tech.view.messageactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
//群介绍
public class CrowdIntroduceActivity extends BaseActivity {

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_introduce;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }
}
