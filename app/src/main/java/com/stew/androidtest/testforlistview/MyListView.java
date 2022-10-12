package com.stew.androidtest.testforlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by stew on 4/1/22.
 * mail: stewforani@gmail.com
 */
public class MyListView extends ListView {
    private static final String TAG = MyListView.class.getSimpleName();
    public MyListView(Context context) {
        super(context);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    int oldX;int oldY;boolean isVDrag = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                oldX = (int) ev.getX();
                oldY = (int) ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = nowX - oldX;
                int deltaY = nowY - oldY;
                if (!isVDrag && Math.abs(deltaX) > Math.abs(deltaY)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    isVDrag = true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                isVDrag = false;
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}


