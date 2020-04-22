package com.wd.tech.view.messageactivity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyFriendchatRecordadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.FriendChatRrecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;

//好友聊天记录
public class FriendchatRecordActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.friend_chatRecyclerView)
    RecyclerView friendChatRecyclerView;
    private int userId2;
    private MyFriendchatRecordadapter myFriendchatRecordadapter;
    private List<FriendChatRrecordBean.ResultBean> result;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_friendchat_record;
    }

    @Override
    protected void initData() {
        userId2 = getIntent().getIntExtra("userId2", 0);
        presenter.getFriendChatRrecordBeandata(userId2,1,5);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if(o instanceof FriendChatRrecordBean){
            result = ((FriendChatRrecordBean) o).getResult();
            myFriendchatRecordadapter = new MyFriendchatRecordadapter(result,this);
            friendChatRecyclerView.setAdapter(myFriendchatRecordadapter);
            friendChatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
