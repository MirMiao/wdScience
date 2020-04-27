package com.wd.tech.view.main;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MySetUpData;
import com.wd.tech.bean.beanMyHomePage.MySetUpResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;
import com.wd.tech.view.activity.MainActivity;

public class MySetUpActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView setupFanhui;
    private android.widget.ImageView setupHeadpic;
    private android.widget.TextView setupName;
    private android.widget.TextView setupSex;
    private android.widget.TextView setupSignal;
    private android.widget.TextView setupBirthday;
    private android.widget.TextView setupPhone;
    private android.widget.TextView setupEmail;
    private android.widget.TextView setupJifen;
    private android.widget.TextView setupVip;
    private android.widget.TextView setupFace;
    private android.widget.TextView setupTuichu;
    private String headPic;
    private String nickName;
    private int sex;
    private long birthday;
    private String phone;
    private String email;
    private int integral;
    private int whetherVip;
    private int whetherFaceId;
    private String signature;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_set_up;
    }

    @Override
    protected void initData() {
        presenter.getSetUp();
    }

    @Override
    protected void initView() {

        setupFanhui = (ImageView) findViewById(R.id.setup_fanhui);
        setupHeadpic = (ImageView) findViewById(R.id.setup_headpic);
        setupName = (TextView) findViewById(R.id.setup_name);
        setupSex = (TextView) findViewById(R.id.setup_sex);
        setupSignal = (TextView) findViewById(R.id.setup_signal);
        setupBirthday = (TextView) findViewById(R.id.setup_birthday);
        setupPhone = (TextView) findViewById(R.id.setup_phone);
        setupEmail = (TextView) findViewById(R.id.setup_email);
        setupJifen = (TextView) findViewById(R.id.setup_jifen);
        setupVip = (TextView) findViewById(R.id.setup_vip);
        setupFace = (TextView) findViewById(R.id.setup_face);
        setupTuichu = (TextView) findViewById(R.id.setup_tuichu);

        setupFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySetUpActivity.this, MainActivity.class);
                intent.putExtra("closeType", 1);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void success(Object o) {

        if(o instanceof MySetUpData){
            MySetUpResult result = ((MySetUpData) o).getResult();
            headPic = result.getHeadPic();
            nickName = result.getNickName();
            sex = result.getSex();
            signature = result.getSignature();
            birthday = result.getBirthday();
            phone = result.getPhone();
            email = result.getEmail();
            integral = result.getIntegral();
            whetherVip = result.getWhetherVip();
            whetherFaceId = result.getWhetherFaceId();

            RetrofitUtil instance = RetrofitUtil.getInstance();
            instance.getRoundphoto(headPic,setupHeadpic);
            setupName.setText(nickName);
            switch (sex){
                case 1:
                    setupSex.setText("男");
                    break;
                case 2:
                    setupSex.setText("女");
                    break;
            }
            setupSignal.setText(signature);
            String gettime = TimeformatUtil.gettime(birthday);
            setupBirthday.setText(gettime);
            setupPhone.setText(phone);
            setupEmail.setText(email);
            setupJifen.setText(integral+"");
            switch (whetherVip){
                case 1:
                    setupVip.setText("是");
                    break;
                case 2:
                    setupVip.setText("否");
                    break;
            }
            switch (whetherFaceId){
                case 1:
                    setupFace.setText("是");
                    break;
                case 2:
                    setupFace.setText("否");
                    break;
            }

        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
