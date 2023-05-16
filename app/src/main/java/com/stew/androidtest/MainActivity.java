package com.stew.androidtest;

import android.app.Instrumentation;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.testforactivity.Test1Activity;
import com.stew.androidtest.testforaidl.TestAIDLActivity;
import com.stew.androidtest.testforasm.TestAsmActivity;
import com.stew.androidtest.testforbinder.TestBinderActivity;
import com.stew.androidtest.testforcontentprovider.TestProvActivity;
import com.stew.androidtest.testfordagger2.TestDagger2Activity;
import com.stew.androidtest.testforhilt.TestHiltActivity;
import com.stew.androidtest.testforhook.HookActivity;
import com.stew.androidtest.testfordispatch.TestDispatchActivity;
import com.stew.androidtest.testforlistview.TestListView2Activity;
import com.stew.androidtest.testforlistview.TestListViewActivity;
import com.stew.androidtest.testfornestedscroll.TestNestedScrollActivity;
import com.stew.androidtest.testforokio.TestOkioActivity;
import com.stew.androidtest.testforremoteview.TestAppWidgetActivity;
import com.stew.androidtest.testforremoteview.TestNotifyActivity;
import com.stew.androidtest.testforrxjava.TestRxjavaActivity;
import com.stew.androidtest.testforserialize.TestParcelableActivity;
import com.stew.androidtest.testforserialize.TestSerializeActivity;
import com.stew.androidtest.testforstorage.TestStorageActivity;
import com.stew.androidtest.testforviewmeasure.TestCustomViewActivity;
import com.stew.androidtest.testforviewmeasure.TestViewMeasureActivity;
import com.stew.androidtest.testforviewscroll.TestViewScrollActivity;
import com.stew.androidtest.testforwindow.TestWindowActivity;

import com.stew.androidtest.testforhotfix.TestCLassLoaderForHotfixActivity;
import com.stew.androidtest.testforwxexit.TestWxAcExitActivity;
import com.stew.androidtest.util.AppLogUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AlertDialog alertDialog;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        findViewById(R.id.tx_activity).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, Test1Activity.class)));

        findViewById(R.id.tx).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, HookActivity.class)));

        findViewById(R.id.tx_dispatch).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestDispatchActivity.class)));

        findViewById(R.id.tx_serial).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestSerializeActivity.class)));

        findViewById(R.id.tx_parcel).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestParcelableActivity.class)));

        findViewById(R.id.tx_binder).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestBinderActivity.class)));

        findViewById(R.id.tx_aidl).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestAIDLActivity.class)));

        findViewById(R.id.tx_provider).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestProvActivity.class)));

        findViewById(R.id.tx_scroll).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestViewScrollActivity.class)));

        findViewById(R.id.tx_listView).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestListViewActivity.class)));

        findViewById(R.id.tx_scrollView).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestListView2Activity.class)));

        findViewById(R.id.tx_measure).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestViewMeasureActivity.class)));

        findViewById(R.id.tx_cv).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestCustomViewActivity.class)));

        findViewById(R.id.tx_notify).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestNotifyActivity.class)));

        findViewById(R.id.tx_widget).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestAppWidgetActivity.class)));

        findViewById(R.id.tx_window).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestWindowActivity.class)));

        findViewById(R.id.tx_Rxjava).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestRxjavaActivity.class)));

        findViewById(R.id.tx_hotfix).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestCLassLoaderForHotfixActivity.class)));

        findViewById(R.id.tx_okio).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestOkioActivity.class)));

        findViewById(R.id.tx_d2).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestDagger2Activity.class)));

        findViewById(R.id.tx_hilt).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestHiltActivity.class)));

        findViewById(R.id.tx_nested).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestNestedScrollActivity.class)));

        findViewById(R.id.tx_storage).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestStorageActivity.class)));

        findViewById(R.id.tx_wx_exit).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestWxAcExitActivity.class)));

        findViewById(R.id.tx_asm).setOnClickListener((v) -> startActivity(new Intent(MainActivity.this, TestAsmActivity.class)));


//        //内
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Log.d("test file", "getDataDir: " + getDataDir());
//        }
//
//        Log.d("test file", "getFilesDir: " + getFilesDir());
//
//        //外私
//        Log.d("test file", "getExternalFilesDir: " + getExternalFilesDir(null));
//
//        File externalFilesDir = getDataDir();
//        File file = new File(externalFilesDir, "stew.txt");
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(65);
//            outputStream.flush();
//            outputStream.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


        //-------------------------//-------------------------//-------------------------//-------------------------
//        if (alertDialog == null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            final View customLayout = getLayoutInflater().inflate(R.layout.layout_loading_dialog, null);
//            builder.setView(customLayout);
//            alertDialog = builder.create();
//            if (alertDialog.getWindow() == null) {
//                return;
//            }
//            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }
//        alertDialog.show();

//        getWindow().getDecorView().findViewById(android.R.id.content).setVisibility(View.INVISIBLE);
//        new Thread(() -> {
//            try {
//                Thread.sleep(2000);
//                runOnUiThread(() -> {
//                    alertDialog.cancel();
//                    getWindow().getDecorView().findViewById(android.R.id.content).setVisibility(View.VISIBLE);
//                });
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();

        AppLogUtil.addLifeLog("########" + getClass().getName());

    }
}
