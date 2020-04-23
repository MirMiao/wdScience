package com.wd.tech.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.InformationFragment;
import com.wd.tech.view.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

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
    private String string;

    @Override
    protected BasePresenter initPresenter() {
        return null;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
