package com.wd.tech.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.bean.messagebean.WxloginBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.SpUtil;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.InformationFragment;
import com.wd.tech.view.information.LoginActivity;
import com.wd.tech.view.fragment.MessageFragment;
import com.wd.tech.view.main.MyAllInfoCollectionActivity;
import com.wd.tech.view.main.MyFollowUserActivity;
import com.wd.tech.view.main.MyQianDaoActivity;
import com.wd.tech.view.main.MySetUpActivity;
import com.wd.tech.view.main.MySysNoticeActivity;
import com.wd.tech.view.main.MyTieziActivity;
import com.wd.tech.view.main.MyUserIntegralActivity;
import com.wd.tech.view.main.MyUserTaskActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<Presenter> implements IContract.IView {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.draw)
    DrawerLayout draw;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_toLogin)
    ImageView ivToLogin;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.ll4)
    LinearLayout ll4;
    @BindView(R.id.ll5)
    LinearLayout ll5;
    @BindView(R.id.ll6)
    LinearLayout ll6;
    @BindView(R.id.ll7)
    LinearLayout ll7;
    @BindView(R.id.ll8)
    LinearLayout ll8;
    @BindView(R.id.ll9)
    LinearLayout ll9;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.home_headpic)
    ImageView homeHeadpic;
    @BindView(R.id.home_name)
    TextView homeName;
    @BindView(R.id.home_qianming)
    TextView homeQianming;
    @BindView(R.id.home_vip)
    ImageView homeVip;
    @BindView(R.id.home_qiandao)
    ImageView homeQiandao;
    @BindView(R.id.qu_shouchang)
    ImageView quShouchang;
    @BindView(R.id.qu_guanzhu)
    ImageView quGuanzhu;
    @BindView(R.id.qu_tiezi)
    ImageView quTiezi;
    @BindView(R.id.qu_tongzhi)
    ImageView quTongzhi;
    @BindView(R.id.qu_jifen)
    ImageView quJifen;
    @BindView(R.id.qu_renwu)
    ImageView quRenwu;
    @BindView(R.id.qu_shezhi)
    ImageView quShezhi;

    private String string;
   private List<LoginEntity.ResultBean> list;
   private List<WxloginBean.ResultBean> listwx;
    private Resources resources;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        try {
            //看是否已经登录
            SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
            String userInfo = sp.getString("userInfo", "");
            Gson gson = new Gson();
            Type type = new TypeToken<List<LoginEntity.ResultBean>>() {
            }.getType();
            list= gson.fromJson(userInfo, type);
            Type typewx = new TypeToken<List<WxloginBean.ResultBean>>() {
            }.getType();
            listwx = gson.fromJson(userInfo, typewx);
            if (list != null||listwx!=null) {
                llUser.setBackgroundColor(Color.WHITE);
                ivToLogin.setVisibility(View.GONE);
                tvLogin.setVisibility(View.GONE);
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.VISIBLE);
                ll3.setVisibility(View.VISIBLE);
                ll4.setVisibility(View.VISIBLE);
                ll5.setVisibility(View.VISIBLE);
                ll6.setVisibility(View.VISIBLE);
                ll7.setVisibility(View.VISIBLE);
                ll8.setVisibility(View.VISIBLE);
                ll9.setVisibility(View.VISIBLE);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list!=null){
            listwx.removeAll(listwx);
            for (int i = 0; i < list.size(); i++) {
                RetrofitUtil.getInstance().getRoundphoto(list.get(i).getHeadPic(),homeHeadpic);
                homeName.setText(list.get(i).getNickName());
                homeQianming.setText(list.get(i).getSignature());
                if (list.get(i).getWhetherVip()==1){
                    resources = getResources();
                    homeVip.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.vip));
                }
            }

        }
        if (listwx!=null) {
            list.removeAll(list);
            for (int i = 0; i < listwx.size(); i++) {
                RetrofitUtil.getInstance().getRoundphoto(listwx.get(i).getHeadPic(), homeHeadpic);
                homeName.setText(listwx.get(i).getNickName());
                homeQianming.setText(listwx.get(i).getUserName());
                if (listwx.get(i).getWhetherVip() == 1) {
                    resources = getResources();
                    homeVip.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.vip));
                }
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        homeHeadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        draw.setScrimColor(Color.TRANSPARENT);
        View mContentView = draw.getChildAt(0);
        draw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mContentView.setTranslationX(drawerView.getMeasuredWidth() * (slideOffset));
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        List<Fragment> list = new ArrayList<>();
        list.add(new InformationFragment());
        list.add(new MessageFragment());
        list.add(new CommunityFragment());
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(R.color.colorGray);
        rb3.setTextColor(R.color.colorGray);
        rb1.setText("资讯");
        rb2.setText("信息");
        rb3.setText("社交");
        resetRadioButtonImage(R.drawable.common_tab_community_s_hdpi, rb1);
        resetRadioButtonImage(R.drawable.common_tab_message_n_hdpi, rb2);
        resetRadioButtonImage(R.drawable.common_tab_community_n_hdpi, rb3);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_1:
                        vp.setCurrentItem(0);
                        rb1.setTextColor(Color.BLACK);
                        rb2.setTextColor(R.color.colorGray);
                        rb3.setTextColor(R.color.colorGray);
                        resetRadioButtonImage(R.drawable.common_tab_community_s_hdpi, rb1);
                        resetRadioButtonImage(R.drawable.common_tab_message_n_hdpi, rb2);
                        resetRadioButtonImage(R.drawable.common_tab_community_n_hdpi, rb3);
                        break;
                    case R.id.rb_2:
                        vp.setCurrentItem(1);
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(R.color.colorGray);
                        rb1.setTextColor(R.color.colorGray);
                        resetRadioButtonImage(R.drawable.common_tab_community_n_hdpi, rb1);
                        resetRadioButtonImage(R.drawable.common_tab_message_s_hdpi, rb2);
                        resetRadioButtonImage(R.drawable.common_tab_community_n_hdpi, rb3);
                        break;
                    case R.id.rb_3:
                        vp.setCurrentItem(2);
                        rb3.setTextColor(Color.BLACK);
                        rb2.setTextColor(R.color.colorGray);
                        rb1.setTextColor(R.color.colorGray);
                        resetRadioButtonImage(R.drawable.common_tab_community_n_hdpi, rb1);
                        resetRadioButtonImage(R.drawable.common_tab_message_n_hdpi, rb2);
                        resetRadioButtonImage(R.drawable.common_tab_community_s_hdpi, rb3);
                        break;
                }
            }
        });
    }

    private void resetRadioButtonImage(int drawableId, RadioButton radioButton) {
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(drawableId);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 40, 40);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, drawable_news, null, null);
    }


    @Override
    public void success(Object o) {

    }

    @Override
    public void failur(Throwable throwable) {

    }


    @OnClick(R.id.iv_toLogin)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }


    @OnClick({R.id.home_vip, R.id.home_qiandao, R.id.qu_shouchang, R.id.qu_guanzhu, R.id.qu_tiezi, R.id.qu_tongzhi, R.id.qu_jifen, R.id.qu_renwu, R.id.qu_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_vip:
                break;
            case R.id.home_qiandao:
                Intent intent8=new Intent(MainActivity.this, MyQianDaoActivity.class);
                startActivity(intent8);
                break;
            case R.id.qu_shouchang:
                Intent intent=new Intent(MainActivity.this, MyAllInfoCollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.qu_guanzhu:
                Intent intent1=new Intent(MainActivity.this, MyFollowUserActivity.class);
                startActivity(intent1);
                break;
            case R.id.qu_tiezi:
                Intent intent3=new Intent(MainActivity.this, MyTieziActivity.class);
                startActivity(intent3);
                break;
            case R.id.qu_tongzhi:
                Intent intent4=new Intent(MainActivity.this, MySysNoticeActivity.class);
                startActivity(intent4);
                break;
            case R.id.qu_jifen:
                Intent intent5=new Intent(MainActivity.this, MyUserIntegralActivity.class);
                startActivity(intent5);
                break;
            case R.id.qu_renwu:
                Intent intent7=new Intent(MainActivity.this, MyUserTaskActivity.class);
                startActivity(intent7);
                break;
            case R.id.qu_shezhi:
                Intent intent6=new Intent(MainActivity.this, MySetUpActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
