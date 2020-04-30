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

    }

    @Override
    protected void initView() {

        qiandaoFenhui = (ImageView) findViewById(R.id.qiandao_fenhui);
        qiandaoQian = (Button) findViewById(R.id.qiandao_qian);

        qiandaoFenhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        qiandaoQian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUserSign();
                Toast.makeText(MyQianDaoActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
                qiandaoQian.setText("已签到");
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
