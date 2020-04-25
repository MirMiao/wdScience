package com.wd.tech.view.main;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.SpUtil;

public class MyHomePageActivity extends BaseActivity {
    private android.widget.ImageView homeHeadpic;
    private android.widget.TextView homeName;
    private android.widget.TextView homeQianming;
    private android.widget.ImageView homeVip;
    private android.widget.ImageView homeQiandao;
    private android.widget.ImageView quShouchang;
    private android.widget.ImageView quGuanzhu;
    private android.widget.ImageView quTiezi;
    private android.widget.ImageView quTongzhi;
    private android.widget.ImageView quJifen;
    private android.widget.ImageView quRenwu;
    private android.widget.ImageView quShezhi;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_homepage;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        homeHeadpic = (ImageView) findViewById(R.id.home_headpic);
        homeName = (TextView) findViewById(R.id.home_name);
        homeQianming = (TextView) findViewById(R.id.home_qianming);
        homeVip = (ImageView) findViewById(R.id.home_vip);
        homeQiandao = (ImageView) findViewById(R.id.home_qiandao);
        quShouchang = (ImageView) findViewById(R.id.qu_shouchang);
        quGuanzhu = (ImageView) findViewById(R.id.qu_guanzhu);
        quTiezi = (ImageView) findViewById(R.id.qu_tiezi);
        quTongzhi = (ImageView) findViewById(R.id.qu_tongzhi);
        quJifen = (ImageView) findViewById(R.id.qu_jifen);
        quRenwu = (ImageView) findViewById(R.id.qu_renwu);
        quShezhi = (ImageView) findViewById(R.id.qu_shezhi);


        String nickn = SpUtil.getString("nickn");
        String headp = SpUtil.getString("headp");
        String signat = SpUtil.getString("signat");

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headp,homeHeadpic);

        homeName.setText(nickn);
        homeQianming.setText(signat);

        quShouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MyAllInfoCollectionActivity.class);
                startActivity(intent);
            }
        });

        quGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MyFollowUserActivity.class);
                startActivity(intent);
            }
        });

        quTiezi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MyTieziActivity.class);
                startActivity(intent);
            }
        });

        quTongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MySysNoticeActivity.class);
                startActivity(intent);
            }
        });

        quJifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MyUserIntegralActivity.class);
                startActivity(intent);
            }
        });

        quRenwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        quShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyHomePageActivity.this,MySetUpActivity.class);
                startActivity(intent);
            }
        });


    }
}
