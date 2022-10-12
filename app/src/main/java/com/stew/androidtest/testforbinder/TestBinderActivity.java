package com.stew.androidtest.testforbinder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.MainActivity;
import com.stew.androidtest.R;
import com.stew.androidtest.aidl.IGetGradeInterface;
import com.stew.androidtest.testfordispatch.TestDispatchActivity;
import com.stew.androidtest.testforhook.HookActivity;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class TestBinderActivity extends AppCompatActivity {

    private IBinder remoteBinder;
    private IGradeInterface binderProxy;
    private IGetGradeInterface aidlBinderProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);

        findViewById(R.id.tx_binder).setOnClickListener((v) -> {
            testBinder();
        });

        findViewById(R.id.tx_binder_proxy).setOnClickListener((v) -> {
            testBinderProxy();
        });

        findViewById(R.id.tx_aidl_binder_proxy).setOnClickListener((v) -> {
            testAIDLBinderProxy();
        });

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //拿到Server端的binder，直接使用bidner的transact方法，发送数据
    private void testBinder() {
        Intent intent = new Intent("android.intent.action.stew_server");
        intent.setPackage(getPackageName());
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("stew_binder_test", "onServiceConnected" + Thread.currentThread().getName());
            remoteBinder = service;
            getGrade(remoteBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("stew_binder_test", "onServiceDisconnected");
            remoteBinder = null;
        }
    };

    private void getGrade(IBinder remoteBinder) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        data.writeString("stew00");
        try {
            Log.d("stew_binder_test", "reply = " + reply.readString());
            remoteBinder.transact(999, data, reply, 0);
            Log.d("stew_binder_test", "reply = " + reply.readString() + "  /  " + Thread.currentThread().getName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //拿到Server端的binder，将发送数据的操作交给BinderProxy，BinderProxy内部执行transact
    //BinderProxy在当前进程，GradeBinder在Server端
    private void testBinderProxy() {
        Log.d("stew_binder_test", "testBinderProxy - - - - " + Thread.currentThread().toString());
        Intent intent = new Intent("android.intent.action.stew_proxy_server");
        intent.setPackage(getPackageName());
        bindService(intent, proxyConnection, BIND_AUTO_CREATE);
    }

    private final ServiceConnection proxyConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("stew_binder_test", "onServiceConnected-1");
            binderProxy = BinderProxy.asInterface(service);
            Log.d("stew_binder_test", "onServiceConnected-2" + binderProxy.getGrade("stew99"));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("stew_binder_test", "onServiceDisconnected");
            remoteBinder = null;
        }
    };

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //把所有操作都交给来AIDL自动生成的IGetGradeInterface来做
    private void testAIDLBinderProxy() {
        Log.d("stew_binder_test", "testAIDLBinderProxy / " + Thread.currentThread().toString());
        Intent intent = new Intent("android.intent.action.stew_aidl_proxy_server");
        intent.setPackage(getPackageName());
        bindService(intent, proxyAidlConnection, BIND_AUTO_CREATE);
    }

    private final ServiceConnection proxyAidlConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("stew_binder_test", "onServiceConnected-1 / " + Thread.currentThread().toString());
            aidlBinderProxy = IGetGradeInterface.Stub.asInterface(service);
            try {
                if (!aidlBinderProxy.asBinder().isBinderAlive()) return;
                Log.d("stew_binder_test", "onServiceConnected-2 / grade = " + aidlBinderProxy.getGrade("stew55"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("stew_binder_test", "onServiceDisconnected");
            remoteBinder = null;
        }
    };

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (connection != null) {
//            unbindService(connection);
//        }

//        if (proxyConnection != null) {
//            unbindService(proxyConnection);
//        }

        if (proxyAidlConnection != null) {
            unbindService(proxyAidlConnection);
        }
    }
}
