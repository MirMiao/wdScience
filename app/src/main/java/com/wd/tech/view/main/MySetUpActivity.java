package com.wd.tech.view.main;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;

public class MySetUpActivity extends BaseActivity<Presenter> implements IContract.IView {
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_my_set_up;
    }

    @Override
    protected void initData() {
        presenter.getSetUp();
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
