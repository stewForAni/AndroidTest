package com.stew.androidtest.testfordispatch;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 2/28/22.
 * mail: stewforani@gmail.com
 */
public class TestDispatchActivity extends AppCompatActivity {

    private static final String TAG = TestDispatchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_test_dispatch);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TestDispatchActivity", "dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestDispatchActivity", "dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestDispatchActivity", "dispatchTouchEvent_ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("TestDispatchActivity","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TestDispatchActivity","onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TestDispatchActivity","onTouchEvent_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
