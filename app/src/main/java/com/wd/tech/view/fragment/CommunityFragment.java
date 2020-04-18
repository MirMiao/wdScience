package com.wd.tech.view.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;


public class CommunityFragment extends BaseFragment {



    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_community;
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
