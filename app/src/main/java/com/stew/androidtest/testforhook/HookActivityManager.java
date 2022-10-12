package com.stew.androidtest.testforhook;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;

/**
 * Created by stew on 2/21/22.
 * mail: stewforani@gmail.com
 */
public class HookActivityManager {
    public static void hook(Activity activity) {
        try {
            Field mInstrumentation = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activity);
            ProxyInstrumentation proxy = new ProxyInstrumentation(instrumentation);
            mInstrumentation.set(activity, proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
