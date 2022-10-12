package com.stew.androidtest.testforbinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by stew on 3/21/22.
 * mail: stewforani@gmail.com
 */
public class TestBinderServer extends Service {

    private final Binder binder = new Binder() {
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            if (code == 999) {
                Log.d("stew_binder_test", "onTransact: " + Thread.currentThread().getName());
                if (reply != null) {
                    reply.writeString("100");
                }
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("stew_binder_test", "onBind" + Thread.currentThread().getName());
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("stew_binder_test", "onUnbind");
        return super.onUnbind(intent);
    }
}
