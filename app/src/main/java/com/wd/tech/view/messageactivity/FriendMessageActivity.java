package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.ExisisMyFriendBean;
import com.wd.tech.bean.messagebean.FriendMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.TimeformatUtil;

import butterknife.BindView;

//好友信息
public class FriendMessageActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.user_head)
    ImageView userHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_scroce)
    TextView userScroce;
    @BindView(R.id.user_vippic)
    ImageView userVippic;
    @BindView(R.id.user_remarkName)
    TextView userRemarkName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_email)
    TextView userEmail;
    @BindView(R.id.btn_sendmessage)
    Button btnSendmessage;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.user_birthday)
    TextView userBirthday;
    @BindView(R.id.btn_addfriend)
    Button btnAddfriend;
    private int friendUid;
    private FriendMessageBean.ResultBean friendmessageresult;
    private String remark;
    private int souserId;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_friend_message;
    }

    @Override
    protected void initData() {
        //接收传值
        friendUid = getIntent().getIntExtra("friendUid", -1);
        souserId = getIntent().getIntExtra("souserId", -1);
        remark = getIntent().getStringExtra("remark");
        if (friendUid!=-1){
            presenter.getFriendMessagedata(friendUid);
            presenter.getExisisMyFrienddata(friendUid);
        }
        if (souserId!=-1){
            presenter.getFriendMessagedata(souserId);
            presenter.getExisisMyFrienddata(souserId);
        }


    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {
        if (o instanceof FriendMessageBean) {
            friendmessageresult = ((FriendMessageBean) o).getResult();
            RetrofitUtil.getInstance().getRoundphoto(friendmessageresult.getHeadPic(), userHead);
            userName.setText(friendmessageresult.getNickName());
            userScroce.setText(friendmessageresult.getIntegral() + "积分");
            //如果为二则不是vip
            if (friendmessageresult.getWhetherVip() == 2) {
                userVippic.setVisibility(View.GONE);
            } else {
                //获取系统图片
                Resources resources = getResources();
                //找的图片设置图片
                Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.vip);
                userVippic.setImageBitmap(bitmap);
            }
            userRemarkName.setText(friendmessageresult.getSignature());
            userPhone.setText(friendmessageresult.getPhone());
            userEmail.setText(friendmessageresult.getEmail());
            userBirthday.setText(TimeformatUtil.gettimeyy(friendmessageresult.getBirthday()));
            int sex = friendmessageresult.getSex();
            if (sex == 1) {
                userSex.setText("男");
            } else {
                userSex.setText("女");
            }
        }
        if (o instanceof ExisisMyFriendBean) {
            int flag = ((ExisisMyFriendBean) o).getFlag();
            if (flag==1) {
                btnAddfriend.setVisibility(View.GONE);
                btnSendmessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentfc = new Intent(FriendMessageActivity.this, FriendChatActivity.class);
                        int userId = friendmessageresult.getUserId();
                        String nickName = friendmessageresult.getNickName();
                        String userName = friendmessageresult.getUserName();
                        String headPic = friendmessageresult.getHeadPic();
                        String signature = friendmessageresult.getSignature();
                        intentfc.putExtra("userId", userId);
                        intentfc.putExtra("nickName", nickName);
                        intentfc.putExtra("userName", userName);
                        intentfc.putExtra("remark1", remark);
                        intentfc.putExtra("headPic", headPic);
                        intentfc.putExtra("signature", signature);
                        startActivity(intentfc);
                    }
                });
            } if(flag==2) {
                btnSendmessage.setVisibility(View.GONE);
                btnAddfriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentaf = new Intent(FriendMessageActivity.this, AddFriendActivity.class);
                        int userId = friendmessageresult.getUserId();
                        String nickName = friendmessageresult.getNickName();
                        String userName = friendmessageresult.getUserName();
                        String headPic = friendmessageresult.getHeadPic();
                        String signature = friendmessageresult.getSignature();
                        intentaf.putExtra("userIdaf", userId);
                        intentaf.putExtra("nickNameaf", nickName);
                        intentaf.putExtra("userNameaf", userName);
                        intentaf.putExtra("remarkaf", remark);
                        intentaf.putExtra("headPicaf", headPic);
                        intentaf.putExtra("signature", signature);
                        startActivity(intentaf);
                    }
                });
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    public void back(View view) {
        finish();
    }
}
