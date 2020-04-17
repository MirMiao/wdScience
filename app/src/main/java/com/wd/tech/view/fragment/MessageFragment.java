package com.wd.tech.view.fragment;
import android.view.View;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.mvp.BasePresenter;
/**
 * 时间 :2020/4/17  13:56
 * 作者 :苗恒
 * 功能 :
 */
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
