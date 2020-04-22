package com.wd.tech.view.activity;

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
public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
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

    @Override
    protected void initView() {
        List<Fragment> list=new ArrayList<>();
        list.add(new InformationFragment());
        list.add(new MessageFragment());
        list.add(new CommunityFragment());
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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



        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioGroup.check(radioGroup.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
           radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup radioGroup, int i) {
                   switch (i){
                       case R.id.rb_information:
                           viewpager.setCurrentItem(0);
                           break;
                           case R.id.rb_message:
                               viewpager.setCurrentItem(1);
                           break;
                           case R.id.rb_community:
                               viewpager.setCurrentItem(2);
                           break;
                   }
               }
           });
    }

}
