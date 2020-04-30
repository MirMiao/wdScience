package com.wd.tech.view.messageactivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyFriendListadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.bean.messagebean.AnviteAddCrowdBean;
import com.wd.tech.bean.messagebean.BatchAnviteAddCrowdBean;
import com.wd.tech.bean.messagebean.FriendListBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.Eventfriend;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
//搜索人或群
public class SreachActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.mysearchview)
    Mysearchview mysearchview;
    @BindView(R.id.btn_quxiao)
    TextView btnQuxiao;
    @BindView(R.id.friendlist_RecyclerView)
    RecyclerView friendlistRecyclerView;
    @BindView(R.id.wudata)
    TextView wudata;
    private List<FriendListBean.ResultBean> friendlistresult;
    private MyFriendListadapter myFriendListadapter;
    private int groupIdscreach;
    private  List<Integer> friendid;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_sreach;
    }

    @Override
    protected void initData() {
        groupIdscreach = getIntent().getIntExtra("groupIdscreach", 0);

        mysearchview.setSetContext(new Mysearchview.setContext() {
         @Override
         public void onContent(String text) {
             presenter.getFriendListBeandata(text);
         }
     });
     mysearchview.setSetDelete(new Mysearchview.setDelete() {
         @Override
         public void ondelete(String text) {
            text="";
         }
     });
    }

    @Subscribe(threadMode =  ThreadMode.POSTING,sticky =  true)
    public  void  onEvent(Eventfriend eventfriend){
        friendid=new ArrayList<>();
        friendid.add(eventfriend.friendid);
        StringBuffer stringBuffer =new StringBuffer();
        for (int i = 0; i < friendid.size(); i++) {
           int integer= friendid.get(i);
            stringBuffer.append(integer);
        }
        presenter.getBatchAnviteAddCrowdBeandata(groupIdscreach,stringBuffer.toString());
    }
    @Override
    protected void initView() {
        wudata.setVisibility(View.GONE);
        friendlistRecyclerView.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn_quxiao)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void success(Object o) {
     if(o instanceof FriendListBean){
         friendlistresult = ((FriendListBean) o).getResult();
         if (friendlistresult==null){
             wudata.setVisibility(View.VISIBLE);
             friendlistRecyclerView.setVisibility(View.GONE);
         }
         else {
             wudata.setVisibility(View.GONE);
             friendlistRecyclerView.setVisibility(View.VISIBLE);
             myFriendListadapter = new MyFriendListadapter(friendlistresult,this);
             friendlistRecyclerView.setAdapter(myFriendListadapter);
             friendlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         }
         }
     if(o instanceof AnviteAddCrowdBean){
            Toast.makeText(App.context,((AnviteAddCrowdBean) o).getMessage(),Toast.LENGTH_LONG).show();
    }
        if(o instanceof BatchAnviteAddCrowdBean){
            Toast.makeText(App.context,((BatchAnviteAddCrowdBean) o).getMessage(),Toast.LENGTH_LONG).show();
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
