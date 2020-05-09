package com.wd.tech.view.main;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.Event;
import com.wd.tech.R;
import com.wd.tech.adapter.myhomepageadapter.MyTieziRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beancommunity.MyPostData;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.http.POST;

/*
 * 帖子
 * */

public class MyTieziActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView tieziFanhui;
    private androidx.recyclerview.widget.RecyclerView tieziRecy;
    private int id;
    private MyTieziRecyAdapter myTieziRecyAdapter;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_tiezi;
    }

    @Override
    protected void initData() {
        presenter.getMyPostdata(1,5);
    }

    @Override
    protected void initView() {

        tieziFanhui = (ImageView) findViewById(R.id.tiezi_fanhui);
        tieziRecy = (RecyclerView) findViewById(R.id.tiezi_recy);

        tieziRecy.setLayoutManager(new LinearLayoutManager(this));

        tieziFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky =true)
    public void onEvent(Event event){
        id = event.tieziid;
        presenter.getDeletedata(id);
        Log.d("shan",id+"");
        myTieziRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void success(Object o) {
        if(o instanceof MyPostData){
            List<MyPostResult> result = ((MyPostData) o).getResult();
            myTieziRecyAdapter = new MyTieziRecyAdapter(result,this);
            tieziRecy.setAdapter(myTieziRecyAdapter);
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
