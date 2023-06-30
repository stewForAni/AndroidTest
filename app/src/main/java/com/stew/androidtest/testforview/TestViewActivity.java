package com.stew.androidtest.testforview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 2023/6/28.
 * mail: stewforani@gmail.com
 */
public class TestViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        View v =findViewById(R.id.v1);
        RelativeLayout r = findViewById(R.id.re1);
        findViewById(R.id.btn1).setOnClickListener(view -> {
            v.requestLayout();
        });

        findViewById(R.id.btn2).setOnClickListener(view -> {
            v.setBackgroundColor(Color.RED);
            v.invalidate();
        });
    }
}
