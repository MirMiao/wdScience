package com.wd.tech.view.fragment;

import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.IContract;
import com.wd.tech.base.mvp.Presenter;


public class CommunityFragment extends BaseFragment<Presenter> implements IContract.IView {
    @Override
    protected int bindLayoutid() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View inflate) {

    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {
        presenter.ShequList(1,10);
    }

    @Override
    public void seccess(Object object) {

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
