package com.wd.tech.view.fragment;

import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.MyMessageAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 时间 :2020/4/17  13:56
 * 作者 :苗恒
 * 功能 :
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.tabmessage)
    TabLayout tabmessage;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.vpmessage)
    ViewPager vpmessage;
    private MyMessageAdapter myMessageAdapter;

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View inflate) {
        myMessageAdapter = new MyMessageAdapter(getChildFragmentManager());
        tabmessage.setupWithViewPager(vpmessage);
        vpmessage.setAdapter(myMessageAdapter);
        vpmessage.setOffscreenPageLimit(2);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
    }
}
