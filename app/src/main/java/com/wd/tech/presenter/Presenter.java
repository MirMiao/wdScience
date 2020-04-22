package com.wd.tech.presenter;

import com.wd.tech.base.mvp.BasePresenter;
import com.wd.tech.contract.IContract;
import com.wd.tech.model.Model;

/**
 * 时间 :2020/4/22  6:45
 * 作者 :苗恒
 * 功能 :
 */
public class Presenter extends BasePresenter<Model, IContract.IView> implements IContract.IPresenter {
    @Override
    protected Model initModel() {
        return new Model();
    }

    @Override
    public void getBannerData() {
        model.getBannerData(new IContract.IModel.ModelCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failur(Throwable throwable) {
                getView().failur(throwable);
            }
        });
    }
}
