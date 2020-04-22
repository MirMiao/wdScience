package com.wd.tech.view.information;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.SerchAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.SerchInfoByKeyWordEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/22  19:49
 * 作者 :苗恒
 * 功能 :
 */
public class SerchActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_serch)
    ImageView ivSerch;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.rv_serch)
    RecyclerView rvSerch;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
      int i=1;
    private String name;
    List<SerchInfoByKeyWordEntity.ResultBean> list= new ArrayList<>();
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_serch;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        rvSerch.setLayoutManager(new LinearLayoutManager(this));
        smart.setEnableRefresh(true);
        smart.setEnableLoadMore(true);
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                i=1;
                presenter.serchByKeyWord(name,i,10);
             smart.finishRefresh();
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                i++;
                presenter.serchByKeyWord(name,i,10);
                 smart.finishLoadMore();
            }
        });
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivSerch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                  //获取输入的内容
                name = etName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(SerchActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                presenter.serchByKeyWord(name,1,10);
            }
        });
    }

    @Override
    public void success(Object o) {
        if(o instanceof SerchInfoByKeyWordEntity){
            if(((SerchInfoByKeyWordEntity) o).getResult().size()>0){
            list.addAll(((SerchInfoByKeyWordEntity) o).getResult());
            SerchAdapter serchAdapter = new SerchAdapter(SerchActivity.this, list);
            rvSerch.setAdapter(serchAdapter);}
            else{
                Toast.makeText(this, "没有找到响应的内容", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }

}
