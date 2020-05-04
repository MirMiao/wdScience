package com.wd.tech;

import android.app.Application;
import android.content.Context;

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
    }
}
