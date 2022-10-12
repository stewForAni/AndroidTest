package com.stew.androidtest.testforhook;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by stew on 2/19/22.
 * mail: stewforani@gmail.com
 */
public class HookClickManager {

    public static void hook(View v) {
        try {
            Method method = View.class.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            Object listnerInfo = method.invoke(v);

            Class c = Class.forName("android.view.View$ListenerInfo");
            Field field = c.getDeclaredField("mOnClickListener");
            View.OnClickListener clickListener = (View.OnClickListener) field.get(listnerInfo);

            ProxyOnClickListener proxy = new ProxyOnClickListener(clickListener);
            field.set(listnerInfo, proxy);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
