package com.stew.androidtest.testforhilt;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by stew on 8/1/22.
 * mail: stewforani@gmail.com
 */

@AndroidEntryPoint
public class TestHiltActivity extends AppCompatActivity {

    @Inject
    HttpTool httpTool1;

    @Inject
    HttpTool httpTool2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilt);
        Log.d("TestHiltActivity", "onCreate: "+httpTool1);
        Log.d("TestHiltActivity", "onCreate: "+httpTool2);
    }
}
