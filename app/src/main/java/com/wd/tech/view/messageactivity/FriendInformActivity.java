package com.wd.tech.view.messageactivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyUserFriendInfromRecordadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.CheckFriendApplyBean;
import com.wd.tech.bean.messagebean.UserFriendInfromRecordBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventstuast;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.TimeformatUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
//好友通知
public class FriendInformActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.checkfriendapply_RecyclerView)
    RecyclerView checkfriendapplyRecyclerView;
    @BindView(R.id.time2)
    TextView time2;
    @BindView(R.id.userfriendinfrom_RecyclerView)
    RecyclerView userfriendinfromRecyclerView;
    private int noticeId;
    private MyUserFriendInfromRecordadapter myUserFriendInfromRecordadapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_friend_inform;
    }


    @Override
    protected void initData() {
        noticeId = getIntent().getIntExtra("noticeId", 0);
        presenter.getUserFriendInfromRecorddata(1,5);
    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public  void onEvent(Eventstuast eventstuast){
        presenter.getCheckFriendApplydata(noticeId,eventstuast.id);
        myUserFriendInfromRecordadapter.notifyDataSetChanged();
    }
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof UserFriendInfromRecordBean) {
            List<UserFriendInfromRecordBean.ResultBean> userfriendinfromrecordresult =((UserFriendInfromRecordBean) o).getResult();
            myUserFriendInfromRecordadapter = new MyUserFriendInfromRecordadapter(userfriendinfromrecordresult,this);
            for (int i = 0; i < userfriendinfromrecordresult.size(); i++) {
                if (userfriendinfromrecordresult.get(i).getStatus()==1){
                    time.setText(TimeformatUtil.gettime(userfriendinfromrecordresult.get(i).getNoticeTime()));
                    checkfriendapplyRecyclerView.setAdapter(myUserFriendInfromRecordadapter);
                    checkfriendapplyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                }else {
                    time2.setText(TimeformatUtil.gettime(userfriendinfromrecordresult.get(i).getNoticeTime()));
                    userfriendinfromRecyclerView.setAdapter(myUserFriendInfromRecordadapter);
                    userfriendinfromRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                }
            }

        }

        if (o instanceof CheckFriendApplyBean) {
            if (((CheckFriendApplyBean) o).getStatus().equals("0000")){
                Toast.makeText(App.context,((CheckFriendApplyBean) o).getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
