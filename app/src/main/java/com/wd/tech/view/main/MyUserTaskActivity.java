package com.wd.tech.view.main;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.myhomepageadapter.MyUserTaskRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MyUserTaskData;
import com.wd.tech.bean.beanMyHomePage.MyUserTaskResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

//我的任务

public class MyUserTaskActivity extends BaseActivity<Presenter> implements IContract.IView {
    private android.widget.ImageView usertaskFanhui;
    private androidx.recyclerview.widget.RecyclerView usertaskRecy;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_user_task;
    }

    @Override
    protected void initData() {

        presenter.getUserTask();

    }

    @Override
    protected void initView() {

        usertaskFanhui = (ImageView) findViewById(R.id.usertask_fanhui);
        usertaskRecy = (RecyclerView) findViewById(R.id.usertask_recy);

        usertaskFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usertaskRecy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(Object o) {
        if(o instanceof MyUserTaskData){
            List<MyUserTaskResult> result = ((MyUserTaskData) o).getResult();
            MyUserTaskRecyAdapter myUserTaskRecyAdapter=new MyUserTaskRecyAdapter(result,this);
            usertaskRecy.setAdapter(myUserTaskRecyAdapter);
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }
}
