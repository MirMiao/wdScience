package com.wd.tech.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.InformationFragment;
import com.wd.tech.view.fragment.LoginActivity;
import com.wd.tech.view.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private String string;


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
   /*     try {
            string = RsaCoder.encryptByPublicKey("密码");
            Log.i("ddd", "initData: "+string);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
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
}
