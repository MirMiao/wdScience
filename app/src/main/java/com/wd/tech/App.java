package com.wd.tech;

import android.app.Application;
import android.content.Context;

import com.example.arclibrary.builder.AcrFaceManagerBuilder;
import com.wd.tech.wxapi.Wxutil;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 8:58
 * @classname :
 */
public class App extends Application {
    public   static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Wxutil.regToWx(this);
        initArcFace();
    }
    private void initArcFace() {
        new AcrFaceManagerBuilder().setContext(this)
                .setFreeSdkAppId(Constants.FREESDKAPPID)
                .setFdSdkKey(Constants.FDSDKKEY)
                .setFtSdkKey(Constants.FTSDKKEY)
                .setFrSdkKey(Constants.FRSDKKEY)
                .setLivenessAppId(Constants.LIVENESSAPPID)
                .setLivenessSdkKey(Constants.LIVENESSSDKKEY)
                .create();
    }

}
