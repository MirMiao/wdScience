package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.MyAddHomeAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.myview.Mysearchview;

import butterknife.BindView;
import butterknife.ButterKnife;

//添加人或群
public class AddActivity extends BaseActivity {


    @BindView(R.id.tabadd)
    TabLayout tabadd;
    @BindView(R.id.mysearchview)
    Mysearchview mysearchview;
    @BindView(R.id.vpadd)
    ViewPager vpadd;
    private MyAddHomeAdapter myAddHomeAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initData() {
        myAddHomeAdapter = new MyAddHomeAdapter(getSupportFragmentManager());
        vpadd.setAdapter(myAddHomeAdapter);
        tabadd.setupWithViewPager(vpadd);
        vpadd.setOffscreenPageLimit(2);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

}
