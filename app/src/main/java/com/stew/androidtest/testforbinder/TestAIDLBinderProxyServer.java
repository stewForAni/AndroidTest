package com.stew.androidtest.testforbinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.stew.androidtest.aidl.IGetGradeInterface;

/**
 * Created by stew on 3/22/22.
 * mail: stewforani@gmail.com
 */
public class TestAIDLBinderProxyServer extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("stew_binder_test", "onBind: " + Thread.currentThread().toString());
        return new AIDLBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("stew_binder_test", "onUnbind: ");
        return super.onUnbind(intent);
    }
}
