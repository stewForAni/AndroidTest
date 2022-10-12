package com.stew.androidtest.testforbinder;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by stew on 3/21/22.
 * mail: stewforani@gmail.com
 */
public class GradeBinder extends Binder {

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        Log.d("stew_binder_test", "onTransact: - - - -" + Thread.currentThread().toString());
        if (code == 999) {
            String name = data.readString();
            Log.d("stew_binder_test", "onTransact: Parcel 1 data = " + name);
            if (reply != null) {
                Log.d("stew_binder_test", "onTransact 2");
                if (name != null) {
                    Log.d("stew_binder_test", "onTransact 3");
                    reply.writeString("88.88");
                }
            }
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
