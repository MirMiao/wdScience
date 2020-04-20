package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.bean.AlterFriendRemarkBean;
import com.wd.tech.bean.DeleteFriendChatRrecordBean;
import com.wd.tech.bean.FriendChatRrecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//聊天设置
public class ChatSettingActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.friend_head)
    ImageView friendHead;
    @BindView(R.id.friend_name)
    TextView friendName;
    @BindView(R.id.friend_remark)
    TextView friendRemark;
    @BindView(R.id.friend_gruoping)
    LinearLayout friendGruoping;
    @BindView(R.id.friend_chatmessage)
    LinearLayout friendChatmessage;
    @BindView(R.id.friend_deletechatmessage)
    LinearLayout friendDeletechatmessage;
    @BindView(R.id.btn_deletefriend)
    Button btnDeletefriend;
    private int userId;
    private String friendhead;
    private String nickName;
    private String userName;
    private PopupWindow updatepopupWindow;
    private EditText update_friendmessage;
    private String remark2;
     private TextView delete_friendmessage,delete_message;
    private List<FriendChatRrecordBean.ResultBean> result;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_chat_setting;
    }

    @Override
    protected void initData() {
        userId = getIntent().getIntExtra("userId1", 0);
        friendhead = getIntent().getStringExtra("headPic");
        nickName = getIntent().getStringExtra("nickName1");
        remark2 = getIntent().getStringExtra("remark2");
        friendName.setText(nickName);
        friendRemark.setText(remark2);
        RetrofitUtil.getInstance().getRoundphoto(friendhead,friendHead);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }


    @OnClick({R.id.friend_remark, R.id.friend_gruoping, R.id.friend_chatmessage, R.id.friend_deletechatmessage, R.id.btn_deletefriend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.friend_remark:
                showremarkPopupWindow();
                break;
            case R.id.friend_gruoping:
                Intent intent=new Intent(ChatSettingActivity.this,SelectGroupingActivity.class);
                startActivity(intent);
                break;
            case R.id.friend_chatmessage:
                Intent intentfcr=new Intent(ChatSettingActivity.this,FriendchatRecordActivity.class);
                intentfcr.putExtra("userId2",userId);
                startActivity(intentfcr);
                break;
            case R.id.friend_deletechatmessage:
                showdeletechatPopupWindow();
                break;
            case R.id.btn_deletefriend:
                showdeletefriendPopupWindow();
                break;
        }
    }
   //删除好友
    private void showdeletefriendPopupWindow() {
        View   contentView=    LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.delete_friend_item,null);
        updatepopupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        updatepopupWindow.setContentView(contentView);
        updatepopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        delete_friendmessage= (TextView)contentView.findViewById(R.id.delete_friendmessage);
        delete_friendmessage.setText("将联系人“"+nickName+"”删除，同时删除与该联系人的聊天记录");
        Button btn_delete = (Button)contentView.findViewById(R.id.btn_delete);
        Button btn_no = (Button)contentView.findViewById(R.id.btn_no);
        btn_delete.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.fragment_message, null);
        updatepopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //删除好友聊天记录
    private void showdeletechatPopupWindow() {
        View   contentView=    LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.delete_friend_chat_record_item,null);
        updatepopupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        updatepopupWindow.setContentView(contentView);
        updatepopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        delete_message= (TextView)contentView.findViewById(R.id.delete_message);
        delete_message.setText("将清空此群聊与此好友的聊天记录");
        Button btn_Yes = (Button)contentView.findViewById(R.id.btn_Yes);
        Button btn_nochat = (Button)contentView.findViewById(R.id.btn_nochat);
        btn_Yes.setOnClickListener(this);
        btn_nochat.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.fragment_message, null);
        updatepopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //修改好友昵称
    private void showremarkPopupWindow() {
    View   contentView=    LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.update_friend_remark,null);
    updatepopupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
    updatepopupWindow.setContentView(contentView);
    updatepopupWindow.setBackgroundDrawable(new ColorDrawable(0));
    //设置各个控件的点击响应
        update_friendmessage= (EditText)contentView.findViewById(R.id.update_friendmessage);
        Button btn_update = (Button)contentView.findViewById(R.id.btn_update);
        Button btn_updateno = (Button)contentView.findViewById(R.id.btn_updateno);
        btn_update.setOnClickListener(this);
        btn_updateno.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(ChatSettingActivity.this).inflate(R.layout.fragment_message, null);
        updatepopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void success(Object o) {
        if(o instanceof FriendChatRrecordBean){
            result = ((FriendChatRrecordBean) o).getResult();
        }
        if (result!=null){
            if(o instanceof DeleteFriendChatRrecordBean){
                Toast.makeText(App.context,((DeleteFriendChatRrecordBean) o).getMessage(),Toast.LENGTH_LONG).show();
            }
            if(o instanceof AlterFriendRemarkBean){
                Toast.makeText(App.context,((AlterFriendRemarkBean) o).getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_update:{
             String  remark =   update_friendmessage.getText().toString().trim();
             presenter.getAlterFriendRemarkBeandata(userId,remark);
                friendRemark.setText(remark);
                updatepopupWindow.dismiss();
            }
            break;
            case R.id.btn_updateno:{
                updatepopupWindow.dismiss();
            }
            break;
            case R.id.btn_Yes:{
                presenter.getDeleteFriendChatRrecordBeandata(userId);
                updatepopupWindow.dismiss();
            }
            break;
            case R.id.btn_delete:{
                presenter.getDeleteFriendChatRrecordBeandata(userId);
                presenter.getDeleteFriendBeandata(userId);
                updatepopupWindow.dismiss();
            }
            break;
            case R.id.btn_nochat:{
                updatepopupWindow.dismiss();
            }
            break;
            case R.id.btn_no:{
                updatepopupWindow.dismiss();
            }
            break;
        }
    }
}
