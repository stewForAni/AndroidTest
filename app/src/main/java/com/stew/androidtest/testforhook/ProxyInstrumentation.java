package com.stew.androidtest.testforhook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by stew on 2/21/22.
 * mail: stewforani@gmail.com
 */
public class ProxyInstrumentation extends Instrumentation {
    private Instrumentation instrumentation;
    public ProxyInstrumentation(Instrumentation i) {
        this.instrumentation = i;
    }
    @SuppressLint("DiscouragedPrivateApi")
    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        Log.d("ProxyInstrumentation", "Proxy Instrumentation success !");
        try {
            Class<?> c = Class.forName("android.app.Instrumentation");
            Method execStartActivity = c.getDeclaredMethod(
                    "execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            return (ActivityResult) execStartActivity.invoke(instrumentation, who, contextThread, token, target, intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
