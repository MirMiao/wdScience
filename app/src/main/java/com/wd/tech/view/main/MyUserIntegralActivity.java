package com.wd.tech.view.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.myhomepageadapter.MyIntegralRecordRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralData;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralRecordData;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralRecordResult;
import com.wd.tech.bean.beanMyHomePage.MyUserIntegralResult;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

/*
 * 积分
 * */

public class MyUserIntegralActivity extends BaseActivity<Presenter> implements IContract.IView {

    private TextView integralFen;
    private RecyclerView integralRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user_integral);
        initView();
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_user_integral;
    }

    @Override
    protected void initData() {
        presenter.getUserIntegral();
        presenter.getUserIntegralRecord(1, 5);
    }

    @Override
    protected void initView() {

        integralFen = (TextView) findViewById(R.id.integral_fen);
        integralRecy = (RecyclerView) findViewById(R.id.integral_recy);
        integralRecy.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(Object o) {

        if(o instanceof MyUserIntegralData){
            MyUserIntegralResult result = ((MyUserIntegralData) o).getResult();
            int amount = result.getAmount();
            integralFen.setText(amount+"");
        }

        if(o instanceof MyUserIntegralRecordData){

            List<MyUserIntegralRecordResult> result = ((MyUserIntegralRecordData) o).getResult();
            MyIntegralRecordRecyAdapter myIntegralRecordRecyAdapter=new MyIntegralRecordRecyAdapter(result,this);
            integralRecy.setAdapter(myIntegralRecordRecyAdapter);

        }

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
