package com.stew.androidtest.testfornestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;

import com.stew.androidtest.R;

/**
 * Created by stew on 8/14/22.
 * mail: stewforani@gmail.com
 */
public class NSParentLayout extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = NSParentLayout.class.getSimpleName();
    private View topView;
    private View bottomView;
    private int topViewHeight;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;

    public NSParentLayout(Context context) {
        super(context);
    }

    public NSParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "NSParentLayout: ");
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate: ");
        topView = findViewById(R.id.top_view);
        bottomView = findViewById(R.id.bottom_view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        ViewGroup.LayoutParams params = bottomView.getLayoutParams();
        Log.d(TAG, "getMeasuredHeight: " + getMeasuredHeight() + " / bottomView" + params.height);
        params.height = getMeasuredHeight();
        bottomView.setLayoutParams(params);
        topViewHeight = topView.getMeasuredHeight();
    }

    @Override
    public void scrollTo(int x, int y) {

        if (y < 0) {
            y = 0;
        }

        if (y >= topViewHeight) {
            y = topViewHeight;
        }
        super.scrollTo(x, y);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll: ");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        Log.d(TAG, "onNestedScrollAccepted: ");
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        Log.d(TAG, "onNestedPreScroll: getScrollY = " + getScrollY());
        //topView刚好要消失
        boolean FLAG_TOP_ON = dy > 0 && getScrollY() < topViewHeight;
        //topView刚好要出现
        boolean FLAG_TOP_OFF = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);

        if (FLAG_TOP_ON || FLAG_TOP_OFF) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, "onNestedScroll      dxConsumed: "
                + dxConsumed + " dyConsumed:"
                + dyConsumed + " dxUnconsumed:"
                + dxUnconsumed + " dyUnconsumed:"
                + dyUnconsumed);
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        Log.d(TAG, "onNestedPreFling: ");
        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "onNestedFling: velocityY = " + velocityY + " consumed = " + consumed);
//        if (velocityY > 0) {
//            scrollBy(0, topViewHeight - getScrollY());
//        } else if (velocityY < 0) {
//            scrollBy(0, -getScrollY());
//        }

        return false;
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        Log.d(TAG, "onStopNestedScroll: ");
        mNestedScrollingParentHelper.onStopNestedScroll(target);
    }
}
