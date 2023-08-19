package com.stew.androidtest.testfornestedscroll;

import static androidx.core.view.ViewCompat.TYPE_NON_TOUCH;
import static androidx.core.view.ViewCompat.TYPE_TOUCH;
import static androidx.customview.widget.ViewDragHelper.INVALID_POINTER;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by stew on 2023/8/8.
 * mail: stewforani@gmail.com
 */
public class NSChildLayout3 extends ViewGroup implements NestedScrollingChild2, NestedScrollingChild3 {

    private int lastX = -1;
    private int lastY = -1;
    private final int[] consumed = new int[2];
    private final int[] offset = new int[2];
    private final int[] mNestedOffsets = new int[2];

    private int mScrollPointerId = INVALID_POINTER;
    private VelocityTracker vt;
    private int mMinFlingVelocity;
    private int mMaxFlingVelocity;

    private FlingTool flingTool;

    public NSChildLayout3(Context context) {
        super(context);
    }

    public NSChildLayout3(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置当前子View是否支持嵌套滑动，如果是false，父View无法响应嵌套滑动
        setNestedScrollingEnabled(true);
        ViewConfiguration vc = ViewConfiguration.get(context);
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();//默认50
        mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();//默认8000


        flingTool = new FlingTool(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //处理fling
        if (vt == null) {
            vt = VelocityTracker.obtain();
        }

        boolean eventAddedToVelocityTracker = false;
        final int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {
            mNestedOffsets[0] = mNestedOffsets[1] = 0;
        }
        final MotionEvent vtev = MotionEvent.obtain(event);
        vtev.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollPointerId = event.getPointerId(0);
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
                    // Updated the nested offsets
                    mNestedOffsets[0] += offset[0];
                    mNestedOffsets[1] += offset[1];
                }
                dispatchNestedScroll(0, 0, dx, dy, null, TYPE_TOUCH);
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                vt.addMovement(vtev);
                eventAddedToVelocityTracker = true;
                vt.computeCurrentVelocity(1000, mMaxFlingVelocity);
                //int xvt = (int) -vt.getXVelocity(mScrollPointerId);
                int yvt = (int) -vt.getYVelocity(mScrollPointerId);
                fling(0, yvt);
                reSetScroll();

                break;
            case MotionEvent.ACTION_CANCEL:
                reSetScroll();
                break;
        }

        if (!eventAddedToVelocityTracker) {
            vt.addMovement(vtev);
        }
        vtev.recycle();

        return true;
    }

    private void reSetScroll() {
        lastX = -1;
        lastY = -1;
        if (vt != null) {
            vt.clear();
        }
        stopNestedScroll(TYPE_TOUCH);
    }

    private void fling(int xvt, int yvt) {
        if (Math.abs(xvt) < mMinFlingVelocity) {
            xvt = 0;
        }
        if (Math.abs(yvt) < mMinFlingVelocity) {
            yvt = 0;
        }

        if (xvt == 0 && yvt == 0) {
            return;
        }

        startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_NON_TOUCH);
        xvt = Math.max(-mMaxFlingVelocity, Math.min(xvt, mMaxFlingVelocity));
        yvt = Math.max(-mMaxFlingVelocity, Math.min(yvt, mMaxFlingVelocity));
        flingTool.fling(xvt, yvt);

    }

    static final Interpolator sQuinticInterpolator = t -> {
        t -= 1.0f;
        return t * t * t * t * t + 1.0f;
    };

    private final int[] mReusableIntPair = new int[2];


    private class FlingTool implements Runnable {

        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        Interpolator mInterpolator = sQuinticInterpolator;

        // When set to true, postOnAnimation callbacks are delayed until the run method completes
        private boolean mEatRunOnAnimationRequest = false;

        // Tracks if postAnimationCallback should be re-attached when it is done
        private boolean mReSchedulePostAnimationCallback = false;


        FlingTool(Context context) {
            mOverScroller = new OverScroller(context, sQuinticInterpolator);
        }

        void fling(int xvt, int yvt) {
            mLastFlingX = mLastFlingY = 0;
            if (mInterpolator != sQuinticInterpolator) {
                mInterpolator = sQuinticInterpolator;
                mOverScroller = new OverScroller(getContext(), sQuinticInterpolator);
            }
            mOverScroller.fling(0, 0, xvt, yvt, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }


        void postOnAnimation() {
            if (mEatRunOnAnimationRequest) {
                mReSchedulePostAnimationCallback = true;
            } else {
                internalPostOnAnimation();
            }
        }

        private void internalPostOnAnimation() {
            removeCallbacks(this);
            ViewCompat.postOnAnimation(NSChildLayout3.this, this);
        }

        @Override
        public void run() {

            mReSchedulePostAnimationCallback = false;
            mEatRunOnAnimationRequest = true;

            final OverScroller scroller = mOverScroller;
            if (scroller.computeScrollOffset()) {
                final int x = scroller.getCurrX();
                final int y = scroller.getCurrY();
                int unconsumedX = x - mLastFlingX;
                int unconsumedY = y - mLastFlingY;
                mLastFlingX = x;
                mLastFlingY = y;
                int consumedX = 0;
                int consumedY = 0;

                // Nested Pre Scroll
                mReusableIntPair[0] = 0;
                mReusableIntPair[1] = 0;
                if (dispatchNestedPreScroll(unconsumedX, unconsumedY, mReusableIntPair, null,
                        TYPE_NON_TOUCH)) {
                    unconsumedX -= mReusableIntPair[0];
                    unconsumedY -= mReusableIntPair[1];
                }

                mReusableIntPair[0] = 0;
                mReusableIntPair[1] = 0;
                dispatchNestedScroll(consumedX, consumedY, unconsumedX, unconsumedY, null,
                        TYPE_NON_TOUCH, mReusableIntPair);

                postOnAnimation();

            }

            mEatRunOnAnimationRequest = false;
            if (mReSchedulePostAnimationCallback) {
                internalPostOnAnimation();
            } else {
                stopNestedScroll(TYPE_NON_TOUCH);
            }

        }
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
    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @NonNull int[] consumed) {
        getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow, type, consumed);
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
