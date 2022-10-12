package com.stew.androidtest.testforlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by stew on 3/31/22.
 * mail: stewforani@gmail.com
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


//--------------------------------------------------------------------------------------------


    //outer Intercept
//    int oldX;
//    int oldY;
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int nowX = (int) ev.getX();
//        int nowY = (int) ev.getY();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                oldX = (int) ev.getX();
//                oldY = (int) ev.getY();
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                int deltaX = nowX - oldX;
//                int deltaY = nowY - oldY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    return true;
//                }
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                break;
//            }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }




//--------------------------------------------------------------------------------------------


    //inner Intercept

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            super.onInterceptTouchEvent(ev);
            return false;
        }
        return true;
    }
}



