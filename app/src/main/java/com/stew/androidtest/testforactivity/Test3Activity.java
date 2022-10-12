package com.stew.androidtest.testforactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;


public class Test3Activity extends AppCompatActivity {

    private static final String TAG = "Test3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        findViewById(R.id.tx3).setOnClickListener(v -> {
                    Intent intent = new Intent(Test3Activity.this, Test1Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

        );

    }

}