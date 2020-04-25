package com.wd.tech.view.messageactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AlterCrowdGroupIntroBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//群介绍
public class CrowdIntroduceActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.btn_finish)
    TextView btnFinish;
    @BindView(R.id.crowd_content)
    EditText crowdContent;
    private int groupId1;
    private String trim;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_introduce;
    }

    @Override
    protected void initData() {
        groupId1 = getIntent().getIntExtra("groupId1", 0);

    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
         if(o instanceof AlterCrowdGroupIntroBean){
             Toast.makeText(App.context,((AlterCrowdGroupIntroBean) o).getMessage(),Toast.LENGTH_LONG).show();
         }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick(R.id.btn_finish)
    public void onViewClicked() {
        trim = crowdContent.getText().toString().trim();
        if(trim!=null){
            presenter.getAlterCrowdGroupIntroBeandata(groupId1,trim );
        }
    }
}
