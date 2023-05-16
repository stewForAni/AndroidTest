package com.stew.androidtest.testforactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;
import com.stew.androidtest.util.AppLogUtil;

public class Test1Activity extends AppCompatActivity {

    private static final String TAG = "Test1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        findViewById(R.id.tx1).setOnClickListener(v -> startActivity(new Intent(Test1Activity.this, Test2Activity.class)));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}