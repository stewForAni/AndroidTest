package com.stew.androidtest.testforcrash;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Created by stew on 2023/11/13.
 * mail: stewforani@gmail.com
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static CrashHandler handler;

    public static CrashHandler getInstance() {
        if (handler == null) {
            synchronized (CrashHandler.class) {
                if (handler == null) {
                    handler = new CrashHandler();
                }
            }
        }
        return handler;
    }

    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.d("CrashHandler", "uncaughtException: ---- "+t.getName()+" / "+ e.getMessage());
    }
}
