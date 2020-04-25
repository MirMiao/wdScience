package com.wd.tech.view.communityactivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

public class PingLunActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.TextView quxiao;
    private android.widget.TextView queren;
    private android.widget.EditText pinglunfabiao;
    private android.widget.ImageView fabutupian;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_ping_lun;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        quxiao = (TextView) findViewById(R.id.quxiao);
        queren = (TextView) findViewById(R.id.queren);
        pinglunfabiao = (EditText) findViewById(R.id.pinglunfabiao);
        fabutupian = (ImageView) findViewById(R.id.fabutupian);

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = pinglunfabiao.getText().toString();
                presenter.getReleasepostdata(s,null);
            }
        });


    }

    @Override
    public void success(Object o) {
        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
