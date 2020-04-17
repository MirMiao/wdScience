package com.wd.tech.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.base.mvp.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P presenter;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(bindLayoutid(), container, false);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        //初始化ButterKnife
        mBind = ButterKnife.bind(this, inflate);

        initView(inflate);
        
        return inflate;
    }

    protected abstract int bindLayoutid();

    protected abstract void initView(View inflate);

    protected abstract P initPresenter();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁布局
        if (presenter != null) {
            presenter.detachView();
        }

        //解绑ButterKnife
        if (mBind != null) {
            mBind.unbind();
        }

    }
}
