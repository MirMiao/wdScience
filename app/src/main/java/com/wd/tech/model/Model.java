package com.wd.tech.model;

import com.wd.tech.api.IInformationApiService;
import com.wd.tech.contract.IContract;
import com.wd.tech.entity.BannerEntity;
import com.wd.tech.util.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 时间 :2020/4/22  6:43
 * 作者 :苗恒
 * 功能 :
 */
public class Model implements IContract.IModel {
    //展示banner列表
    @Override
    public void getBannerData(ModelCallBack modelCallBack) {
        RetrofitUtil.getInstance().creatService(IInformationApiService.class)
                .getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerEntity>() {
                    @Override
                    public void accept(BannerEntity bannerEntity) throws Exception {
                    modelCallBack.success(bannerEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        modelCallBack.failur(throwable);
                    }
                });
    }
}
