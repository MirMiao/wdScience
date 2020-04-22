package com.wd.tech.view.messageactivity;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyAddHomeAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.myview.Mysearchview;
import com.wd.tech.util.UserEntroty;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

//添加人或群
public class AddActivity extends BaseActivity {


    @BindView(R.id.tabadd)
    TabLayout tabadd;
    @BindView(R.id.mysearchview)
    Mysearchview mysearchview;
    @BindView(R.id.vpadd)
    ViewPager vpadd;
    private MyAddHomeAdapter myAddHomeAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void initData() {
        myAddHomeAdapter = new MyAddHomeAdapter(getSupportFragmentManager());
        vpadd.setAdapter(myAddHomeAdapter);
        tabadd.setupWithViewPager(vpadd);
        vpadd.setOffscreenPageLimit(2);
        mysearchview.setSetContext(new Mysearchview.setContext() {
            @Override
            public void onContent(String text) {
                boolean  isphone = UserEntroty.isphone(text);
                if (isphone==true){
                    EventBus.getDefault().postSticky(text);
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

}
