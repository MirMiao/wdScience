package com.wd.tech.view.information;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.RegEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 时间 :2020/4/23  21:18
 * 作者 :苗恒
 * 功能 :
 */
public class RegActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.tv_rightLogin)
    TextView tvRightLogin;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {
      if(o instanceof RegEntity){
          Toast.makeText(this, ""+((RegEntity) o).getMessage(), Toast.LENGTH_SHORT).show();
          if("0000".equals(((RegEntity) o).getStatus())){
              finish();
          }
      }
    }

    @Override
    public void failur(Throwable throwable) {

    }



    @OnClick({R.id.tv_rightLogin, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rightLogin:
                startActivity(new Intent(RegActivity.this, LoginActivity.class));
                break;
            case R.id.bt_register:
                String name = tvName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = tvPhone.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String s = tvPwd.getText().toString();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    String pwd = RsaCoder.encryptByPublicKey(s);
                    presenter.reg(name,phone,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
