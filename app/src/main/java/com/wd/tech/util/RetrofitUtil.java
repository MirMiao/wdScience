package com.wd.tech.util;

import com.wd.tech.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static RetrofitUtil mRetrofitUtil;

    private Retrofit retrofit;

    private RetrofitUtil() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        if (mRetrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (mRetrofitUtil == null) {
                    mRetrofitUtil = new RetrofitUtil();
                }
            }
        }
        return mRetrofitUtil;
    }

    //动态代理
    public <T>T creatService(Class<T> tClass){
        T t = retrofit.create(tClass);
        return t;
    }

}
