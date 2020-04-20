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
import com.wd.tech.adapter.MyFriendChatDialogueRecordadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.FriendChatDialogueRecordBean;
import com.wd.tech.bean.SendMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

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
    private String userName;
    private String headPic;
    private String remark1;
    private String userName1;

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
        friendName.setText(userName1);
        presenter.getFriendChatDialogueRecordBeandata(userId, 1, 10);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getFriendChatDialogueRecordBeandata(userId, 1, 10);
                myFriendChatDialogueRecordadapter.update(FriendChatDialogueRecordresult);
                friendContent.setText("");
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
                    intentcs.putExtra("userId1",userId);
                    intentcs.putExtra("nickName1",nickName);
                    intentcs.putExtra("remark2",remark1);
                    intentcs.putExtra("headPic",headPic);
                startActivity(intentcs);
                break;
            case R.id.btn_send:
                String infrommain = friendContent.getText().toString().trim();
                if (!"".equals(infrommain)) {
                    try {
                        //加密好友发送的信息
                        String content = RsaCoder.encryptByPublicKey(infrommain);
                        presenter.getSendMessageBeandata(userId, content);
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
            myFriendChatDialogueRecordadapter = new MyFriendChatDialogueRecordadapter(FriendChatDialogueRecordresult, this);
            friendchatRecyclerView.setAdapter(myFriendChatDialogueRecordadapter);
            friendchatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (o instanceof SendMessageBean) {
            Log.d("sendmessage", "success: " + ((SendMessageBean) o).getMessage());
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

}
