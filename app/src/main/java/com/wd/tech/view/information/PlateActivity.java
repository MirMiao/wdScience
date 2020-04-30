package com.wd.tech.view.information;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.PlateAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.FindAllInfoPlate;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/22  19:48
 * 作者 :苗恒
 * 功能 :
 */
public class PlateActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_plate;
    }

    @Override
    protected void initData() {
       presenter.getPlateData();
    }

    @Override
    protected void initView() {
         rvMenu.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public void success(Object o) {
        if(o instanceof FindAllInfoPlate){

            List<FindAllInfoPlate.ResultBean> result = ((FindAllInfoPlate) o).getResult();
            PlateAdapter plateAdapter = new PlateAdapter(PlateActivity.this, result);
            rvMenu.setAdapter(plateAdapter);
            plateAdapter.setPlateOnClick(new PlateAdapter.PlateOnClick() {
                @Override
                public void onClick(int id) {
                     //跳转到详情页面
                    Intent intent = new Intent(PlateActivity.this,PlateInformationActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


}
