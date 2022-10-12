package com.stew.androidtest.testforactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;


public class Test2Activity extends AppCompatActivity {

    private static final String TAG = "Test2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        findViewById(R.id.tx2).setOnClickListener(v -> startActivity(new Intent(Test2Activity.this, Test3Activity.class)));

    }

}