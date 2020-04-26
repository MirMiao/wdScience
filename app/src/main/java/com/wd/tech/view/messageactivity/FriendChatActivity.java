package com.wd.tech.view.messageactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
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
        friendName.setText(userName1);
        presenter.getFriendChatDialogueRecordBeandata(userId, 1, count);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                count++;
                presenter.getFriendChatDialogueRecordBeandata(userId, 1, count);
                myFriendChatDialogueRecordadapter.loadmore(FriendChatDialogueRecordresult);
                refresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
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
                break;
            case R.id.btn_send:
                String infrommain = friendContent.getText().toString().trim();
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
            friendchatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
