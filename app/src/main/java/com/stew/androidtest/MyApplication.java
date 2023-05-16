package com.stew.androidtest;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.stew.androidtest.util.AppLogUtil;
import com.stew.androidtest.util.CommonUtil;

//import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by stew on 3/19/22.
 * mail: stewforani@gmail.com
 */
//@HiltAndroidApp
public class MyApplication extends Application {

    private static MyApplication instance;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "onCreate: ");
        instance = this;
        CommonUtil.init(instance);
        AppLogUtil.init(instance);
    }


}
