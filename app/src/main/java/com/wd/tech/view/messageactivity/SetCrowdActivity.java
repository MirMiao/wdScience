package com.wd.tech.view.messageactivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.SetCrowdBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import butterknife.BindView;
import butterknife.OnClick;
//创建群
public class SetCrowdActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.crowd_head)
    ImageView crowdHead;
    @BindView(R.id.crowd_name)
    EditText crowdName;
    @BindView(R.id.crowd_name_count)
    TextView crowdNameCount;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_left_num)
    TextView tvLeftNum;
    @BindView(R.id.btn_setcrowd)
    Button btnSetcrowd;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
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
        crowdName.addTextChangedListener(watchercname);
        etContent.addTextChangedListener(watchercontent);
    }
    public void back(View view) {
        finish();
    }
    @OnClick( R.id.btn_setcrowd)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setcrowd:
                if(crowdName!=null&&etContent!=null){
                    String name=crowdName.getText().toString().trim();
                    String content=crowdNameCount.getText().toString().trim();
                    presenter.getSetCrowdBeandata(name,content);
                }else {
                    Toast.makeText(App.context,"群名或群介绍不能为空",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //群名
    TextWatcher watchercname = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if (s.length() > 20&&s.length()<2) {
                s.delete(20, s.length());
            }
            int num = 20 - s.length();
            crowdNameCount.setText(num+"个汉字");
        }
    };
  //群介绍
    TextWatcher watchercontent = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if (s.length() > 100) {
                s.delete(100, s.length());
            }
            int num = 100 - s.length();
            tvLeftNum.setText(num+"字以内");
        }
    };

    @Override
    public void success(Object o) {
        if(o instanceof SetCrowdBean){
            Toast.makeText(App.context,((SetCrowdBean) o).getMessage(),Toast.LENGTH_LONG).show();
        }
        if (((SetCrowdBean) o).getStatus().equals("0000")){
            finish();
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
