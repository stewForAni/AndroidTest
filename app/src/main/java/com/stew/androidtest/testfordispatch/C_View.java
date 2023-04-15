package com.stew.androidtest.testfordispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by stew on 2/28/22.
 * mail: stewforani@gmail.com
 */
public class C_View extends View {

    public C_View(Context context) {
        super(context);
    }

    public C_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("C_View","dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("C_View","dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("C_View","dispatchTouchEvent_ACTION_UP");
                break;
        }

        boolean a = super.dispatchTouchEvent(event);
        //Log.d("C_View", "dispatchTouchEvent: " + a);
        return a;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("C_View","onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("C_View","onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("C_View","onTouchEvent_ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
