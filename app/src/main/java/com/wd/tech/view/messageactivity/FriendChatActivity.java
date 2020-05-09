package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyFriendChatDialogueRecordadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.FriendChatDialogueRecordBean;
import com.wd.tech.bean.messagebean.SendMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.EventMeassage;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//好友聊天
public class FriendChatActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.btn_friendchatsetting)
    ImageView btnFriendchatsetting;
    @BindView(R.id.friendchat_RecyclerView)
    RecyclerView friendchatRecyclerView;
    @BindView(R.id.friend_content)
    EditText friendContent;
    @BindView(R.id.btn_send)
    TextView btnSend;
    @BindView(R.id.friend_name)
    TextView friendName;
    private int userId;
    private String nickName;
    private MyFriendChatDialogueRecordadapter myFriendChatDialogueRecordadapter;
    private List<FriendChatDialogueRecordBean.ResultBean> FriendChatDialogueRecordresult;
    private String headPic;
    private String remark1;
    private String userName1;
    private String signature;
    private String headPic1;
     private int count =5;
    //在handler中接收Message
    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };
    //创建一个线程，通过线程发送延迟消息，实现三秒刷新数据的效果
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SendRequestWithOkhttp();       //发送请求
            handler.postDelayed(this, 1000);//延迟发送消息，实现3秒一次发送数据
        }
    };
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_friend_chat;
    }

    @Override
    protected void initData() {
        userId = getIntent().getIntExtra("userId", 0);
        nickName = getIntent().getStringExtra("nickName");
        userName1 = getIntent().getStringExtra("userName");
        remark1 = getIntent().getStringExtra("remark1");
        headPic = getIntent().getStringExtra("headPic");
        signature = getIntent().getStringExtra("signature");
        friendName.setText(nickName);
        runnable.run();
    }
    //定义一个发送json请求数据的代码
    public void SendRequestWithOkhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                    presenter.getFriendChatDialogueRecordBeandata(userId, 1, count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void initView() {
        friendContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
              //EditText监听 如果为空 发送不可用 不可发送空格信息
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(friendContent.getText())){
                    btnSend.setEnabled(true);//启用按钮
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.shapeb);
                    btnSend.setTextColor(Color.WHITE);
                    btnSend.setBackground(drawable);
                }
                else {
                    btnSend.setEnabled(false);//不启用按钮
                    Resources resources = getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.shapesend);
                    btnSend.setTextColor(Color.parseColor("#C1C1C1"));
                    btnSend.setBackground(drawable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void back(View view) {
        finish();
       handler.removeCallbacks(runnable);
    }

    @OnClick({R.id.btn_friendchatsetting, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_friendchatsetting:
                Intent intentcs = new Intent(FriendChatActivity.this, ChatSettingActivity.class);
                intentcs.putExtra("userId1", userId);
                intentcs.putExtra("nickName1", nickName);
                intentcs.putExtra("remark2", remark1);
                intentcs.putExtra("headPic", headPic);
                startActivity(intentcs);
                handler.removeCallbacks(runnable);
                break;
            case R.id.btn_send:
                String infrommain = friendContent.getText().toString().trim();
                if(!TextUtils.isEmpty(friendContent.getText())){
                    try {
                        //加密好友发送的信息
                        String content = RsaCoder.encryptByPublicKey(infrommain);
                        presenter.getSendMessageBeandata(userId, content);
                        EventMeassage eventMeassage = new EventMeassage();
                        eventMeassage.headpic = headPic1;
                        eventMeassage.content = content;
                        eventMeassage.name = nickName;
                        EventBus.getDefault().postSticky(eventMeassage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void success(Object o) {
        if (o instanceof FriendChatDialogueRecordBean) {
            FriendChatDialogueRecordresult = ((FriendChatDialogueRecordBean) o).getResult();
            for (int i = 0; i < FriendChatDialogueRecordresult.size(); i++) {
                if (FriendChatDialogueRecordresult.get(i).getDirection() == 2) {
                    headPic1 = FriendChatDialogueRecordresult.get(i).getHeadPic();
                }
            }
            myFriendChatDialogueRecordadapter = new MyFriendChatDialogueRecordadapter(FriendChatDialogueRecordresult, this);
            friendchatRecyclerView.setAdapter(myFriendChatDialogueRecordadapter);
            LinearLayoutManager layout=new LinearLayoutManager(this);
            layout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
            layout.setReverseLayout(true);//列表翻转
            friendchatRecyclerView.setLayoutManager(layout);

        }
        if (o instanceof SendMessageBean) {
            if (((SendMessageBean) o).getStatus().equals("0000")) {
                presenter.getFriendChatDialogueRecordBeandata(userId, 1, count);
                myFriendChatDialogueRecordadapter.notifyDataSetChanged();
                friendContent.setText("");
            }
            Log.d("sendmessage", "success: " + ((SendMessageBean) o).getMessage());
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

}
