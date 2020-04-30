package com.wd.tech.view.information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.adapter.informationadapter.InformationInfoAdapter;
import com.wd.tech.adapter.informationadapter.PingLunAdapter;
import com.wd.tech.adapter.informationadapter.TuiJianAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.AddInfoCommentEntity;
import com.wd.tech.bean.informationentity.FindAllPingLunEntity;
import com.wd.tech.bean.informationentity.InformationInfosEntity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/4/27  9:39
 * 作者 :苗恒
 * 功能 :
 */
public class InformationInfoActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.rv_informationInfo)
    RecyclerView rvInformationInfo;
    @BindView(R.id.flowView)
    FlowView flowView;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvTuijian;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.rv_pinglun)
    RecyclerView rvPinglun;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_pinglun)
    EditText etPinglun;
    @BindView(R.id.mLinearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.et_PingLuns)
    EditText etPingLuns;
    @BindView(R.id.tv_fabiao)
    TextView tvFabiao;
    @BindView(R.id.mRelativeLayout)
    RelativeLayout mRelativeLayout;
    private List<LoginEntity.ResultBean> list;
    private int id;


    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_informationinfo;
    }

    @Override
    protected void initData() {

        //接收id
        id = getIntent().getIntExtra("id", 0);
        Log.d("xxx", id + "");
        presenter.getInformationInfo(id);
        //根据id获取评论
        presenter.getAllPingLun(id, 1, 5);
        tvFabiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //获取输入的内容
                String tv_pinglun = etPingLuns.getText().toString();
                if(TextUtils.isEmpty(tv_pinglun)){
                    Toast.makeText(InformationInfoActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (list != null) {
                    presenter.addInforComment(list.get(0).getUserId(),list.get(0).getSessionId(),tv_pinglun, id);
                }else{
                    Toast.makeText(InformationInfoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InformationInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void initView() {
        try {
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            String userInfo = sp.getString("userInfo", "");
            Gson gson = new Gson();
            Type type = new TypeToken<List<LoginEntity.ResultBean>>() {
            }.getType();
            list = gson.fromJson(userInfo, type);

        }catch (Exception e){
            e.printStackTrace();
        }
        etPinglun.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    //获取焦点
                    mLinearLayout.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    etPingLuns.setFocusable(true);
                    etPingLuns.setFocusableInTouchMode(true);
                }
            }
        });
        etPingLuns.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                  if(b){
                      //获取焦点

                  }else{
                      //失去焦点
                      mLinearLayout.setVisibility(View.VISIBLE);
                      mRelativeLayout.setVisibility(View.GONE);
                  }
            }
        });

        rvTuijian.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvPinglun.setLayoutManager(new LinearLayoutManager(this));
        rvInformationInfo.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void success(Object o) {
        if (o instanceof InformationInfosEntity) {
            if (((InformationInfosEntity) o).getResult() != null) {
                InformationInfoAdapter informationInfoAdapter = new InformationInfoAdapter(InformationInfoActivity.this, ((InformationInfosEntity) o).getResult());
                rvInformationInfo.setAdapter(informationInfoAdapter);
                List<InformationInfosEntity.ResultBean.PlateBean> plate = ((InformationInfosEntity) o).getResult().getPlate();
                if (plate != null) {
                    flowView.addTextView(plate);
                } else {
                    flowView.setVisibility(View.GONE);
                }
                List<InformationInfosEntity.ResultBean.InformationListBean> informationList = ((InformationInfosEntity) o).getResult().getInformationList();
                if (informationList != null) {
                    TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(InformationInfoActivity.this, informationList);
                    rvTuijian.setAdapter(tuiJianAdapter);
                    tuiJianAdapter.setTuiJianOnclick(new TuiJianAdapter.TuiJianOnclick() {
                        @Override
                        public void onClick(int id) {
                            presenter.getInformationInfo(id);
                        }
                    });
                } else {
                    tvTuijian.setVisibility(View.GONE);
                    rvTuijian.setVisibility(View.GONE);
                }

            }
        } else if (o instanceof FindAllPingLunEntity) {
            PingLunAdapter  pingLunAdapter = new PingLunAdapter(InformationInfoActivity.this, ((FindAllPingLunEntity) o).getResult());
            rvPinglun.setAdapter(pingLunAdapter);

        }else if(o instanceof AddInfoCommentEntity){
            Toast.makeText(this, ""+((AddInfoCommentEntity) o).getMessage(), Toast.LENGTH_SHORT).show();
            if("0000".equals(((AddInfoCommentEntity) o).getStatus())){
                mLinearLayout.setVisibility(View.VISIBLE);
                mRelativeLayout.setVisibility(View.GONE);
                //刷新一个评论的适配器
                presenter.getAllPingLun(id, 1, 5);
            }
        }
    }
    @Override
    public void failur(Throwable throwable) {

    }

}
