package com.wd.tech.view.main;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MySysNoticeData;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

/*
 * 通知
 * */

public class MySysNoticeActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView sysnoticeFanhui;
    private androidx.recyclerview.widget.RecyclerView sysnoticeRecy;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_sys_notice;
    }

    @Override
    protected void initData() {
        presenter.getMySysNotice(1,5);
    }

    @Override
    protected void initView() {

        sysnoticeFanhui = (ImageView) findViewById(R.id.sysnotice_fanhui);
        sysnoticeRecy = (RecyclerView) findViewById(R.id.sysnotice_recy);

        sysnoticeFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void success(Object o) {
        if(o instanceof MySysNoticeData){

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
