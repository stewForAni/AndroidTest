package com.stew.androidtest.testforlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by stew on 4/2/22.
 * mail: stewforani@gmail.com
 */
public class MySV extends ScrollView {
    private static final String TAG = MySV.class.getSimpleName();

    public MySV(Context context) {
        super(context);
    }

    public MySV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent_ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    // out
    int oldY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int nowY = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "onInterceptTouchEvent_ACTION_DOWN: ");
                oldY = (int) ev.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "onInterceptTouchEvent_ACTION_UP: ");
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "onInterceptTouchEvent_ACTION_MOVE: ");
                if (ifBottom()) {
                    Log.d(TAG, "1");
                    if ((nowY - oldY) > 0 && (isTop() == 0)) {
                        return super.onInterceptTouchEvent(ev);
                    }
                    return false;
                }
                Log.d(TAG, "2");
                break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent_ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent_ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent_ACTION_UP");
                break;
        }
        boolean a = super.onTouchEvent(ev);
        Log.d(TAG, "onTouchEvent_Result" + a);
        return a;
    }

    public boolean ifBottom() {
        int a = getChildAt(0).getHeight();
        int b = getScrollY();
        int c = getHeight();
        Log.d(TAG, "a:" + a + " / b:" + b + " / c:" + c);
        return a == b + c;
    }

    public int isTop() {
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        ListView listView = (ListView) linearLayout.getChildAt(1);
        View view = listView.getChildAt(0);
        return view.getTop();
    }


}
