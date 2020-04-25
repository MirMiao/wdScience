package com.wd.tech.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wd.tech.App;


public class SpUtil {

    public static String sp_name="confit";

    public static void saveString(String key,String vaule){
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,vaule);
        edit.commit();
    }

    public static void saveInt(String key,int vaule){
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key,vaule);
        edit.commit();
    }

    public static String getString(String key){
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, "");
        return string;
    }

    public static int getInt(String key){
        SharedPreferences sharedPreferences = App.context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        int anInt = sharedPreferences.getInt(key, -1);
        return anInt;
    }

}
