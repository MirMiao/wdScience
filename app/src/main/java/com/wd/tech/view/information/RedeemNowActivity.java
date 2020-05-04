package com.wd.tech.view.information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.InfoPayByIntegralEntity;
import com.wd.tech.bean.informationentity.InformationInfosEntity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时间 :2020/5/3  16:15
 * 作者 :苗恒
 * 功能 :
 */
public class RedeemNowActivity extends BaseActivity<Presenter> implements IContract.IView {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.cb_aixin)
    CheckBox cbAixin;
    @BindView(R.id.tv_aixinNum)
    TextView tvAixinNum;
    @BindView(R.id.cb_shar)
    CheckBox cbShar;
    @BindView(R.id.tv_sharNum)
    TextView tvSharNum;
    @BindView(R.id.tv_suoxujifen)
    TextView tvSuoxujifen;
    @BindView(R.id.tv_myjifen)
    TextView tvMyjifen;
    @BindView(R.id.bt_rightNow)
    Button btRightNow;
    private List<LoginEntity.ResultBean> list;
    private int integralCost;
    private int id;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.acticity_redeemnow;
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        presenter.getInformationInfo(id);
        //判断用户是否
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
        btRightNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.infoPayByIntegral(list.get(0).getUserId(),list.get(0).getSessionId(), id,integralCost);
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {
        if (o instanceof InformationInfosEntity) {
            InformationInfosEntity.ResultBean result = ((InformationInfosEntity) o).getResult();
            Glide.with(this).load(result.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                    .into(ivImage);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long releaseTime = result.getReleaseTime();
            String format = simpleDateFormat.format(releaseTime);
            tvTitle.setText(result.getTitle());
            tvSummary.setText(result.getSummary());
            tvTime.setText(format);
            tvSource.setText(result.getSource());
            tvAixinNum.setText(result.getPraise() + "");
            tvSharNum.setText(result.getShare() + "");
            integralCost = result.getIntegralCost();
            tvSuoxujifen.setText(integralCost + "分");
        }else if(o instanceof InfoPayByIntegralEntity){
            Toast.makeText(this, ""+((InfoPayByIntegralEntity) o).getMessage(), Toast.LENGTH_SHORT).show();
            if("9999".equals(((InfoPayByIntegralEntity) o).getStatus())){
                Intent intent = new Intent(RedeemNowActivity.this, LoginActivity.class);
                startActivity(intent);
            }else if("0000".equals(((InfoPayByIntegralEntity) o).getStatus())){
                Intent intent = new Intent(RedeemNowActivity.this, InformationInfoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


}
