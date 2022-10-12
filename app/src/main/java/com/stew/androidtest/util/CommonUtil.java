package com.stew.androidtest.util;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


/**
 * Created by stew on 2019/3/20.
 * mail: stewforani@gmail.com
 */
public class CommonUtil {

    private static Application mContext = null;
    private static WindowManager windowManager = null;

    public static void init(Application context) {
        if (context == null) {
            return;
        }
        mContext = context;
        if (windowManager == null) {
            windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth() {
        if (windowManager == null) {
            return 0;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        if (windowManager == null) {
            return 0;
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

}
