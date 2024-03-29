package com.stew.androidtest.testfornestedscroll;

import static androidx.core.view.ViewCompat.TYPE_TOUCH;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;

/**
 * Created by stew on 2023/8/8.
 * mail: stewforani@gmail.com
 */
public class NSChildLayout2 extends LinearLayout implements NestedScrollingChild2 {

    private int lastX = -1;
    private int lastY = -1;
    private int[] consumed = new int[2];
    private int[] offset = new int[2];

    private VelocityTracker vt;
    private int mMinFlingVelocity;
    private int mMaxFlingVelocity;

    private boolean canFling;
    private Scroller mScroller;

    private int lastFlingX;
    private int lastFlingY;
    private final int[] flingConsumed = new int[2];

    public NSChildLayout2(Context context) {
        super(context);
    }

    public NSChildLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置当前子View是否支持嵌套滑动，如果是false，父View无法响应嵌套滑动
        setNestedScrollingEnabled(true);
        setOrientation(VERTICAL);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();//默认50
        mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();//默认8000
        mScroller = new Scroller(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        endFling();

        //处理fling
        if (vt == null) {
            vt = VelocityTracker.obtain();
        }
        vt.addMovement(event);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, TYPE_TOUCH);
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getRawX();
                int currentY = (int) event.getRawY();
                int dx = lastX - currentX;
                int dy = lastY - currentY;
                if (dispatchNestedPreScroll(dx, dy, consumed, offset, TYPE_TOUCH)) {
                    dx -= consumed[0];
                    dy -= consumed[1];
                }
                dispatchNestedScroll(0, 0, dx, dy, null, TYPE_TOUCH);
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                stopNestedScroll(TYPE_TOUCH);
                lastX = -1;
                lastY = -1;

                vt.computeCurrentVelocity(1000, mMaxFlingVelocity);
                int xvt = (int) vt.getXVelocity();
                int yvt = (int) vt.getYVelocity();
                fling(xvt, yvt);
                if (vt != null) {
                    vt.clear();
                }
                break;
        }
        return true;
    }

    private boolean fling(int xvt, int yvt) {
        if (Math.abs(xvt) < mMinFlingVelocity) {
            xvt = 0;
        }
        if (Math.abs(yvt) < mMinFlingVelocity) {
            yvt = 0;
        }

        if (xvt == 0 && yvt == 0) {
            return false;
        }

        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_NON_TOUCH);
//        xvt = Math.max(-mMaxFlingVelocity, Math.min(xvt, mMaxFlingVelocity));
//        yvt = Math.max(-mMaxFlingVelocity, Math.min(yvt, mMaxFlingVelocity));

        canFling = true;
        mScroller.fling(0, 0, xvt, yvt, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        postInvalidate();

        return true;
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset() && canFling) {
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();
            int dx = lastFlingX - x;
            int dy = lastFlingY - y;
            lastFlingX = x;
            lastFlingY = y;
            flingConsumed[0]=0;
            flingConsumed[1]=0;
            if (dispatchNestedPreScroll(dx, dy, flingConsumed, null, ViewCompat.TYPE_NON_TOUCH)) {
                //计算父控件消耗后，剩下的距离
                dx -= flingConsumed[0];
                dy -= flingConsumed[1];
            }

            dispatchNestedScroll(0, 0, 0, dy, null, ViewCompat.TYPE_NON_TOUCH);

            //这里重绘是为了触发computeScroll方法，达到重复执行dispatchNestedPreScroll方法的目的
            postInvalidate();
        } else {
            stopNestedScroll(ViewCompat.TYPE_NON_TOUCH);
            endFling();
        }
    }

    private void endFling() {
        canFling = false;
        lastFlingX = 0;
        lastFlingY = 0;
    }

    // NestedScrollingChild

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        getScrollingChildHelper().setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return getScrollingChildHelper().startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        getScrollingChildHelper().stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return getScrollingChildHelper().hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow, int type) {
        return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow,
                                           int type) {
        return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow,
                type);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
    }

    private NestedScrollingChildHelper mScrollingChildHelper;

    private NestedScrollingChildHelper getScrollingChildHelper() {
        if (mScrollingChildHelper == null) {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper;
    }
}
