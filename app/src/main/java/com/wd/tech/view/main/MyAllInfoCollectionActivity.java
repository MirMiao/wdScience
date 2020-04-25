package com.wd.tech.view.main;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;

import com.wd.tech.adapter.myhomepageadapter.MyAllRecyAdapter;

import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MyAllData;
import com.wd.tech.bean.beanMyHomePage.MyAllResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

/*
*
* 先commit一下
* 拉去一下：有冲突要解决冲突，可能在commit一下
* push一下
*
* */

/*
 * 收藏
 * */


public class MyAllInfoCollectionActivity extends BaseActivity<Presenter> implements IContract.IView {

    private android.widget.ImageView allFenhui;
    private android.widget.ImageView allShanchu;
    private androidx.recyclerview.widget.RecyclerView allRecy;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_all_info_collection;
    }

    @Override
    protected void initData() {
        presenter.getMyHomepageAll(1,10);
    }

    @Override
    protected void initView() {

        allFenhui = (ImageView) findViewById(R.id.all_fenhui);
        allShanchu = (ImageView) findViewById(R.id.all_shanchu);
        allRecy = (RecyclerView) findViewById(R.id.all_recy);

        allRecy.setLayoutManager(new LinearLayoutManager(this));

        allFenhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        allShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void success(Object o) {
        if(o instanceof MyAllData){
            List<MyAllResult> result = ((MyAllData) o).getResult();


    MyAllRecyAdapter myAllRecyAdapter=new MyAllRecyAdapter(result,this);
            allRecy.setAdapter(myAllRecyAdapter);

        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
