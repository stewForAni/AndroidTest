package com.stew.androidtest.testforaidl;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 9/24/22.
 * mail: stewforani@gmail.com
 */
public class TestAIDLActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        findViewById(R.id.textView).setOnClickListener(v -> {

        });
    }
}
