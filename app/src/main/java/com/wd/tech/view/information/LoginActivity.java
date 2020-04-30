package com.wd.tech.view.information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.util.SpUtil;
import com.wd.tech.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 时间 :2020/4/23  21:00
 * 作者 :苗恒
 * 功能 :
 */
public class LoginActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_reghtReg)
    TextView tvReghtReg;
    @BindView(R.id.bt_login)
    Button btLogin;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_log;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {
        if(o instanceof LoginEntity){
             if("0000".equals(((LoginEntity) o).getStatus())){
                 List<LoginEntity.ResultBean> list=new ArrayList<>();
                 Toast.makeText(this, ""+((LoginEntity) o).getMessage(), Toast.LENGTH_SHORT).show();
                 LoginEntity.ResultBean result = ((LoginEntity) o).getResult();
                 list.add(result);
                 int userId = result.getUserId();
                 String sessionId = result.getSessionId();
                 SpUtil.saveInt("userid",userId);
                 SpUtil.saveString("sesseion",sessionId);
                 SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                 SharedPreferences.Editor edit = sp.edit();
                 Gson gson = new Gson();
                 String s = gson.toJson(list);
                 edit.putString("userInfo",s).commit();
                 startActivity(new Intent(LoginActivity.this, MainActivity.class));
             }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }



    @OnClick({R.id.tv_reghtReg, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reghtReg:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                break;
            case R.id.bt_login:
                //获取输入的内容
                String phone = etPhone.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s = etPwd.getText().toString();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    String pwd = RsaCoder.encryptByPublicKey(s);
                    presenter.login(phone,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
