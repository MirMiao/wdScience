package com.wd.tech.view.main;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

public class MyQianDaoActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView qiandaoFenhui;
    private android.widget.Button qiandaoQian;

    @Override
    protected Presenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_qian_dao;
    }

    @Override
    protected void initData() {
        presenter.getUserSign();
    }

    @Override
    protected void initView() {

        qiandaoFenhui = (ImageView) findViewById(R.id.qiandao_fenhui);

        qiandaoFenhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
