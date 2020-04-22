package com.wd.tech.api;

import com.wd.tech.entity.BannerEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 时间 :2020/4/22  6:42
 * 作者 :苗恒
 * 功能 :
 */
public interface IInformationApiService {
    @GET("techApi/information/v1/bannerShow")
    Observable<BannerEntity> getBannerData();   //展示banner数据
}
