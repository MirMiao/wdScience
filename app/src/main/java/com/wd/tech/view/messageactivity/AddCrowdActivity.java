package com.wd.tech.view.messageactivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.bean.messagebean.QuitCrowdBean;
import com.wd.tech.bean.messagebean.UserExisisCrowdBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.OnClick;

//加别人的群
public class AddCrowdActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.crowd_head)
    ImageView crowdHead;
    @BindView(R.id.crowd_member_count)
    TextView crowdMemberCount;
    @BindView(R.id.btn_crowd_member)
    ImageView btnCrowdMember;
    @BindView(R.id.btn_crowd_infrom)
    ImageView btnCrowdInfrom;
    @BindView(R.id.btn_infrom)
    LinearLayout btnInfrom;
    @BindView(R.id.btn_crowd_chatrecord)
    LinearLayout btnCrowdChatrecord;
    @BindView(R.id.introduce_content)
    TextView introduceContent;
    @BindView(R.id.crowd_introduce)
    LinearLayout crowdIntroduce;
    @BindView(R.id.btn_applycrowd)
    Button btnApplycrowd;
    @BindView(R.id.btn_quitcrowd)
    Button btnQuitcrowd;
    private int groupId;
    private TextView group_name;
    private CrowGroupDetailMessageBean.ResultBean crowgroupdetailmessageresult;
    private PopupWindow popupWindow;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_add_crowd;
    }

    @Override
    protected void initData() {
        groupId = getIntent().getIntExtra("groupId", 0);
        presenter.getUserExisisCrowdBeandata(groupId);
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
        if (o instanceof UserExisisCrowdBean) {
            //是该群员
            if (((UserExisisCrowdBean) o).getFlag()==1){
                crowdIntroduce.setVisibility(View.GONE);
                btnApplycrowd.setVisibility(View.GONE);
                //群成员
                btnCrowdMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intentmember=new Intent(AddCrowdActivity.this,CrowdMemberActivity.class);
                        startActivity(intentmember);
                    }
                });
                //群通知
                btnInfrom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intentinfrom=new Intent(AddCrowdActivity.this,CrowdInformActivity.class);
                        startActivity(intentinfrom);
                    }
                });
                //查询聊天记录
                btnCrowdChatrecord.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                //退群
                btnQuitcrowd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showquitPopupWindow();
                    }


                });
            }
            else {
                btnCrowdMember.setVisibility(View.GONE);
                btnQuitcrowd.setVisibility(View.GONE);
                btnCrowdInfrom.setVisibility(View.GONE);
                btnCrowdChatrecord.setVisibility(View.GONE);
                btnInfrom.setVisibility(View.GONE);
                btnApplycrowd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intentapply=new Intent(AddCrowdActivity.this,ApplyAddCrowdActivity.class);
                        startActivity(intentapply);
                    }
                });
            }
        }
        if (o instanceof CrowGroupDetailMessageBean) {
            crowgroupdetailmessageresult = ((CrowGroupDetailMessageBean) o).getResult();
            RetrofitUtil.getInstance().getRectphoto(crowgroupdetailmessageresult.getGroupImage(),crowdHead);
            crowdMemberCount.setText("共"+crowgroupdetailmessageresult.getCurrentCount()+"人");
            introduceContent.setText(crowgroupdetailmessageresult.getDescription());
        }

        if (o instanceof QuitCrowdBean){
            Toast.makeText(App.context,((QuitCrowdBean) o).getMessage(),Toast.LENGTH_LONG).show();
        }
    }
      //退群
    private void showquitPopupWindow() {
        View contentView = LayoutInflater.from(AddCrowdActivity.this).inflate(R.layout.quit_crowd_item, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_yes = (Button) contentView.findViewById(R.id.btn_yes);
        Button btn_no = (Button) contentView.findViewById(R.id.btn_no);
        group_name = (TextView) contentView.findViewById(R.id.group_name);
        group_name.setText("您将退出"+crowgroupdetailmessageresult.getGroupName()+"群");
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddCrowdActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
    @Override
    public void failur(Throwable throwable) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_yes: {
                presenter.getQuitCrowdBeandata(groupId);
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
