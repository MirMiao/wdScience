package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdGroupAllUserMessageadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//群成员
public class CrowdMemberActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.btn_applyfriend)
    ImageView btnApplyfriend;
    @BindView(R.id.crowd_member_expandableListView)
    RecyclerView crowdMemberExpandableListView;
    private int groupIdmember;
    private MyCrowdGroupAllUserMessageadapter myCrowdGroupAllUserMessageadapter;
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_member;
    }

    @Override
    protected void initData() {
        groupIdmember = getIntent().getIntExtra("groupIdmember", 0);
        presenter.getCrowdGroupAllUserMessageBeandata(groupIdmember);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowdGroupAllUserMessageBean) {
            crowdgroupallusermessageresult = ((CrowdGroupAllUserMessageBean) o).getResult();
            myCrowdGroupAllUserMessageadapter = new MyCrowdGroupAllUserMessageadapter(crowdgroupallusermessageresult, this);
            crowdMemberExpandableListView.setAdapter(myCrowdGroupAllUserMessageadapter);
            crowdMemberExpandableListView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }



    @OnClick(R.id.btn_applyfriend)
    public void onViewClicked() {
        Intent intentmanage = new Intent(CrowdMemberActivity.this,    SreachActivity.class);
        startActivity(intentmanage);

    }
}
