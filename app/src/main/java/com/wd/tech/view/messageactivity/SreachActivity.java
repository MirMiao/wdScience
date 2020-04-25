package com.wd.tech.view.messageactivity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyFriendListadapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.bean.messagebean.FriendListBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.presenter.Presenter;

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
     mysearchview.setSetContext(new Mysearchview.setContext() {
         @Override
         public void onContent(String text) {
             presenter.getFriendListBeandata(text);
         }
     });
     mysearchview.setSetDelete(new Mysearchview.setDelete() {
         @Override
         public void ondelete(String text) {
             presenter.getFriendListBeandata(text);
         }
     });
    }

    @Override
    protected void initView() {
        wudata.setVisibility(View.GONE);
        friendlistRecyclerView.setVisibility(View.GONE);
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

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
