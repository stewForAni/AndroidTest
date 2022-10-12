package com.stew.androidtest.testforlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by stew on 4/1/22.
 * mail: stewforani@gmail.com
 */
public class MyHSV extends HorizontalScrollView {
    public MyHSV(Context context) {
        super(context);
    }

    public MyHSV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int oldX;
    int oldY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int nowX = (int) ev.getX();
        int nowY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                oldX = (int) ev.getX();
                oldY = (int) ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = nowX - oldX;
                int deltaY = nowY - oldY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return true;
                }
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

}
