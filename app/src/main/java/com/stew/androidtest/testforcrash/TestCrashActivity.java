package com.stew.androidtest.testforcrash;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 2023/11/13.
 * mail: stewforani@gmail.com
 */
public class TestCrashActivity extends AppCompatActivity {

    private static final String TAG = "TestCrashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        TextView tx = findViewById(R.id.tx);
        tx.setText(0);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                throw new NullPointerException("###");
//            }
//        }).start();

    }

}