package com.stew.androidtest.testforbinder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by stew on 3/21/22.
 * mail: stewforani@gmail.com
 */
public class BinderProxy implements IGradeInterface {

    private final IBinder mBinder;

    private BinderProxy(IBinder binder) {
        this.mBinder = binder;
    }

    @Override
    public String getGrade(String name) {

        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeString(name);
        String grade = "";
        try {
            Log.d("stew_binder_test", "getGrade: - - - -" + Thread.currentThread().toString());
            mBinder.transact(999, data, reply, 0);
            grade = reply.readString();
            Log.d("stew_binder_test", "getGrade: " + grade);
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            data.recycle();
            reply.recycle();
        }

        return grade;
    }

    public static IGradeInterface asInterface(IBinder binder) {
        if (binder instanceof IGradeInterface) {
            Log.d("stew_binder_test", "binder is IGradeInterface");
            return (IGradeInterface) binder;
        } else {
            Log.d("stew_binder_test", "binder is not IGradeInterface");
            return new BinderProxy(binder);
        }
    }


}
