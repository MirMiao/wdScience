package com.wd.tech.view.messageactivity;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
//创建群
public class SetCrowdActivity extends BaseActivity {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_set_crowd;
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
