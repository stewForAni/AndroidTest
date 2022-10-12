package com.stew.androidtest.util;

import android.os.Environment;

import com.stew.androidtest.MyApplication;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class Constants {
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + MyApplication.getInstance().getPackageName();

}
