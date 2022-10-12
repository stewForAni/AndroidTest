package com.stew.androidtest.testforviewmeasure;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 4/7/22.
 * mail: stewforani@gmail.com
 */
public class TestViewMeasureActivity extends AppCompatActivity {

    private static final String TAG = "view-measure";

    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);


        view = findViewById(R.id.view);
        getSize("onCreate");
        view.post(new Runnable() {
            @Override
            public void run() {
                getSize("onCreate post");
            }
        });

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TestViewMeasureActivity.this.getSize("onGlobalLayout");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSize("onStart");
        view.post(new Runnable() {
            @Override
            public void run() {
                getSize("onStart post");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSize("onResume");
        view.post(new Runnable() {
            @Override
            public void run() {
                getSize("onResume post");
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getSize("onWindowFocusChanged");
        }
    }

    private void getSize(String s) {
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        Log.d(TAG, s + " / " + w + " / " + h);
    }

}
