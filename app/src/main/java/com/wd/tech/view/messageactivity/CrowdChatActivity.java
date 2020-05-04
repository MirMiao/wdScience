package com.wd.tech.view.messageactivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.wd.tech.adapter.infromation.MyCrowdChatContentadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CrowdChatContentBean;
import com.wd.tech.bean.messagebean.SendCrowdMessageBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.EventMeassage;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//群聊
public class CrowdChatActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.btn_crowdmessage)
    ImageView btnCrowdmessage;
    @BindView(R.id.crowd_content)
    EditText crowdContent;
    @BindView(R.id.btn_send)
    TextView btnSend;
    @BindView(R.id.crowdchat_RecyclerView)
    RecyclerView crowdchatRecyclerView;
    @BindView(R.id.groupname)
    TextView groupname;
    private MyCrowdChatContentadapter myCrowdChatContentadapter;
    private List<CrowdChatContentBean.ResultBean> result;
    private int groupIdcc;
    private String headPic;
    private String groupname2;
    private String nickName;
    private String crowdhead;
   private  int count =5;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_chat;
    }

    @Override
    protected void initData() {
        groupIdcc = getIntent().getIntExtra("groupIdcc", 0);
        groupname2 = getIntent().getStringExtra("groupname");
        crowdhead = getIntent().getStringExtra("crowdhead");
        groupname.setText(groupname2);
        presenter.getCrowdChatContentBeandata(groupIdcc, 1, count);
    }

    @Override
    protected void initView() {
        crowdContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(crowdContent.getText())){
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
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowdChatContentBean) {
            result = ((CrowdChatContentBean) o).getResult();
            myCrowdChatContentadapter = new MyCrowdChatContentadapter(result, this);
            crowdchatRecyclerView.setAdapter(myCrowdChatContentadapter);
            LinearLayoutManager layout=new LinearLayoutManager(this);
            layout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
            layout.setReverseLayout(true);//列表翻转
            crowdchatRecyclerView.setLayoutManager(layout);
        }
        if (o instanceof SendCrowdMessageBean) {
            if (((SendCrowdMessageBean) o).getStatus().equals("0000")) {
                presenter.getCrowdChatContentBeandata(groupIdcc, 1, count);
                 myCrowdChatContentadapter.notifyDataSetChanged();
                crowdContent.setText("");
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @OnClick(R.id.btn_send)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String infrommain = crowdContent.getText().toString().trim();
                if(!TextUtils.isEmpty(crowdContent.getText())) {
                    try {
                        //加密好友发送的信息
                        String content = RsaCoder.encryptByPublicKey(infrommain);
                        presenter.getSendCrowdMessageBeandata(groupIdcc, content);
                        for (int i = 0; i < result.size(); i++) {
                            EventMeassage eventMeassage = new EventMeassage();
                            eventMeassage.headpic = crowdhead;
                            eventMeassage.content = infrommain;
                            eventMeassage.name = groupname2;
                            EventBus.getDefault().postSticky(eventMeassage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
        }
    }

}
