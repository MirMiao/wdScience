package com.wd.tech.view.fragment;

import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;


public class MessageFragment extends BaseFragment {
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
