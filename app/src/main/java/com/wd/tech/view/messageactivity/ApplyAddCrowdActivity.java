package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.ApplyAddCrowdBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//申请加群
public class ApplyAddCrowdActivity extends BaseActivity<Presenter> implements IContract.IView {


    @BindView(R.id.btn_send)
    TextView btnSend;
    @BindView(R.id.text_content)
    EditText textContent;
    private int groupIdintentapply;
    private String trim;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_apply_add_crowd;
    }

    @Override
    protected void initData() {
        groupIdintentapply = getIntent().getIntExtra("groupIdintentapply", 0);

    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof ApplyAddCrowdBean) {
            Toast.makeText(App.context, ((ApplyAddCrowdBean) o).getMessage(), Toast.LENGTH_LONG).show();
            if (((ApplyAddCrowdBean) o).getStatus().equals("0000")) {
                finish();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }



    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        trim = textContent.getText().toString().trim();
        if (trim!=null){
            presenter.getApplyAddCrowdBeandata(groupIdintentapply,trim);
        }
    }
}
