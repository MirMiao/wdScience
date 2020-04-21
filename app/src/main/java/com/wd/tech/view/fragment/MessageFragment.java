package com.wd.tech.view.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;


import butterknife.BindView;
import butterknife.OnClick;


/**
 * 时间 :2020/4/17  13:56
 * 作者 :苗恒
 * 功能 :
 */
public class MessageFragment extends BaseFragment{

    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
}
