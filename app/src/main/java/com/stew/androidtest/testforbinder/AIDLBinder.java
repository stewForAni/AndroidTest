package com.stew.androidtest.testforbinder;

import android.os.RemoteException;
import android.util.Log;

import com.stew.androidtest.aidl.IGetGradeInterface;

/**
 * Created by stew on 3/22/22.
 * mail: stewforani@gmail.com
 */
public class AIDLBinder extends IGetGradeInterface.Stub {
    @Override
    public int getGrade(String name) throws RemoteException {
        Log.d("stew_binder_test", "AIDLBinder name = " + name);
        Log.d("stew_binder_test", "getGrade --- / " + Thread.currentThread().toString());

        return 7788;
    }
}
