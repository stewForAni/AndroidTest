package com.stew.androidtest.testforwindow;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import static android.view.Window.ID_ANDROID_CONTENT;

/**
 * Created by stew on 4/14/22.
 * mail: stewforani@gmail.com
 */
public class TestWindowActivity extends AppCompatActivity {

    private static final String TAG = TestWindowActivity.class.getSimpleName();

    int x2;
    int y2;
    TextView t;
    TextView t2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_window);

        t = new TextView(this);
        t.setText("cool stew");
        t.setWidth(300);
        t.setHeight(100);
        t.setBackgroundColor(Color.BLUE);

        t2 = new TextView(this);
        t2.setText("gogogogogog");
        t2.setWidth(300);
        t2.setHeight(100);
        t2.setBackgroundColor(Color.BLUE);

        WindowManager.LayoutParams p = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);

        p.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            p.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }

        p.gravity = Gravity.START | Gravity.TOP;
        p.x = 0;
        p.y = 0;
        getWindowManager().addView(t, p);

        p.x = 0;
        p.y = 100;
        getWindowManager().addView(t2, p);


        Dialog dialog = new Dialog(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        }
        dialog.setTitle("11111");
        dialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.d(TAG, "onContentChanged: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewGroup contentParent = (ViewGroup) findViewById(ID_ANDROID_CONTENT);
        Log.d(TAG, "onResume: " + contentParent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.d(TAG, "onCreate: 1" + findViewById(R.id.tx9).getWindowId());
            Log.d(TAG, "onCreate: 2" + findViewById(R.id.tx10).getWindowId());
            Log.d(TAG, "onCreate: 3" + t.getWindowId());
            Log.d(TAG, "onCreate: 4" + t2.getWindowId());
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
