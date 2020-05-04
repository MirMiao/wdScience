package com.wd.tech.util;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.api.Api;
import com.wd.tech.bean.informationentity.LoginEntity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class RetrofitUtil {

    private static RetrofitUtil mRetrofitUtil;

    private Retrofit retrofit;

    private RetrofitUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                if(!TextUtils.isEmpty(s)){
                    Logger.json(s);
                }
                else {
                    Logger.d(s);
                }
            }
        });
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                     Request request = chain.request();
                     Request.Builder builder = request.newBuilder();
                        int userid = SpUtil.getInt("userid");
                        String sesseion = SpUtil.getString("sesseion");
                        builder.addHeader("ak","0110010010000");
                        builder.addHeader("Content-Type","application/x-www-form-urlencoded");
                        builder.addHeader("userId",userid+"");
                        builder.addHeader("sessionId",sesseion);
                     Request newrequest=builder.build();
                     return chain.proceed(newrequest);
                    }
                })
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
    //圆形图片（头像）
    public  void  getRoundphoto(String path, ImageView imageView){
        Glide.with(App.context).load(path).error(R.mipmap.ic_launcher)
        .placeholder(R.mipmap.ic_launcher)
        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
        .into(imageView);
    }
    //圆角形图片
    public  void  getRectphoto(String path, ImageView imageView){
        Glide.with(App.context).load(path).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(imageView);
    }
    //动态代理
    public <T>T creatService(Class<T> tClass){
        T t = retrofit.create(tClass);
        return t;
    }

}
