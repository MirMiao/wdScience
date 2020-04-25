package com.wd.tech.view.messageactivity;
import android.view.View;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
//群聊
public class CrowdChatActivity extends BaseActivity<Presenter> implements IContract.IView {
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_chat;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failur(Throwable throwable) {

    }
}
