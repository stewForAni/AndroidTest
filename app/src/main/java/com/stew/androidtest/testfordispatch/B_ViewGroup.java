package com.stew.androidtest.testfordispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;

/**
 * Created by stew on 2/28/22.
 * mail: stewforani@gmail.com
 */
public class B_ViewGroup extends LinearLayout {

    public B_ViewGroup(Context context) {
        super(context);
    }

    public B_ViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("B_ViewGroup", "dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("B_ViewGroup", "dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("B_ViewGroup", "dispatchTouchEvent_ACTION_UP");
                break;
        }

        boolean a = super.dispatchTouchEvent(ev);
        //Log.d("B_ViewGroup", "dispatchTouchEvent: " + a);

        //mFirstTouchTarget
        //getPri();

        return a;
    }

    private void getPri() {
        Field fieldPassword;
        try {
            fieldPassword = ViewGroup.class.getDeclaredField("mFirstTouchTarget");
            fieldPassword.setAccessible(true);
            Log.d("B_ViewGroup", "hook2---------: " + fieldPassword.get(this));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("B_ViewGroup", "onInterceptTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("B_ViewGroup", "onInterceptTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("B_ViewGroup", "onInterceptTouchEvent_ACTION_UP");
                break;
        }
        //return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("B_ViewGroup", "onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("B_ViewGroup", "onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("B_ViewGroup", "onTouchEvent_ACTION_UP");
                break;
        }
        //return super.onTouchEvent(event);
        return true;
    }
}
