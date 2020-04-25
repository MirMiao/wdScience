package com.wd.tech.view.main;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.myhomepageadapter.MyTieziRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beancommunity.MyPostData;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

/*
 * 帖子
 * */

public class MyTieziActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView tieziFanhui;
    private androidx.recyclerview.widget.RecyclerView tieziRecy;

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

    }

    @Override
    public void success(Object o) {
        if(o instanceof MyPostData){
            List<MyPostResult> result = ((MyPostData) o).getResult();

            MyTieziRecyAdapter myTieziRecyAdapter=new MyTieziRecyAdapter(result,this);
            tieziRecy.setAdapter(myTieziRecyAdapter);

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
