package com.wd.tech.view.information;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

/**
 * 时间 :2020/4/22  19:49
 * 作者 :苗恒
 * 功能 :
 */
public class SerchActivity extends BaseActivity<Presenter> implements IContract.IView {
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_serch;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
