package com.stew.androidtest.testforremoteview;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.stew.androidtest.R;

import java.util.Objects;

/**
 * Created by stew on 4/11/22.
 * mail: stewforani@gmail.com
 */
public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    public static final String a = "com.stew.androidtest.action.CLICK";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        sendBroadcast(new Intent(a));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
