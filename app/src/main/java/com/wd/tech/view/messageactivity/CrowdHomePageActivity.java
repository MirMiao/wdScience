package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//自己的群

public class CrowdHomePageActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.crowd_background)
    ImageView crowdBackground;
    @BindView(R.id.crowd_head)
    ImageView crowdHead;
    @BindView(R.id.crowd_member_count)
    TextView crowdMemberCount;
    @BindView(R.id.btn_crowd_member)
    ImageView btnCrowdMember;
    @BindView(R.id.crowd_name)
    TextView crowdName;
    @BindView(R.id.btn_crowd_introduce)
    ImageView btnCrowdIntroduce;
    @BindView(R.id.btn_crowd_infrom)
    ImageView btnCrowdInfrom;
    @BindView(R.id.btn_crowd_manage)
    ImageView btnCrowdManage;
    @BindView(R.id.btn_quitcrowd)
    Button btnQuitcrowd;
    @BindView(R.id.btn_crowd_chatrecord)
    LinearLayout btnCrowdChatrecord;
    private CrowGroupDetailMessageBean.ResultBean crowgroupdetailmessageresult;
    private int groupId;
    private PopupWindow popupWindow;
    private TextView group_name;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_home_page;
    }

    @Override
    protected void initData() {
        groupId = getIntent().getIntExtra("groupId", 0);
        presenter.getCrowGroupDetailMessageBeandata(groupId);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowGroupDetailMessageBean) {
            crowgroupdetailmessageresult = ((CrowGroupDetailMessageBean) o).getResult();
            RetrofitUtil.getInstance().getRectphoto(crowgroupdetailmessageresult.getGroupImage(), crowdHead);
            crowdMemberCount.setText("共" + crowgroupdetailmessageresult.getCurrentCount() + "人");
            crowdName.setText(crowgroupdetailmessageresult.getGroupName() + "群");
            btnCrowdIntroduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentchatintroduce = new Intent(CrowdHomePageActivity.this, CrowdIntroduceActivity.class);
                    startActivity(intentchatintroduce);
                }
            });
            btnQuitcrowd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showquitPopupWindow();
                }


            });

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    //退群
    private void showquitPopupWindow() {
        View contentView = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.quit_crowd_item, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_yes = (Button) contentView.findViewById(R.id.btn_yes);
        Button btn_no = (Button) contentView.findViewById(R.id.btn_no);
        group_name = (TextView) contentView.findViewById(R.id.group_name);
        group_name.setText("您将解散" + crowgroupdetailmessageresult.getGroupName() + "群");
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @OnClick({R.id.btn_crowd_member, R.id.btn_crowd_infrom, R.id.btn_crowd_manage, R.id.btn_crowd_chatrecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_crowd_member:
                Intent intentmember = new Intent(CrowdHomePageActivity.this, CrowdMemberActivity.class);
                startActivity(intentmember);
                break;
            case R.id.btn_crowd_infrom:
                Intent intentinfrom = new Intent(CrowdHomePageActivity.this, CrowdInformActivity.class);
                startActivity(intentinfrom);
                break;
            case R.id.btn_crowd_manage:
                Intent intentmanage = new Intent(CrowdHomePageActivity.this, CrowdManageActivity.class);
                startActivity(intentmanage);
                break;
            case R.id.btn_crowd_chatrecord:

                break;

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_yes: {
                popupWindow.dismiss();
            }
            break;
            case R.id.btn_no: {
                popupWindow.dismiss();
            }
            break;
        }
    }

}
