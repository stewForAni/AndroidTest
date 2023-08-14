package com.stew.androidtest.testfornestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;

import com.stew.androidtest.R;

/**
 * Created by stew on 8/16/22.
 * mail: stewforani@gmail.com
 */
public class NSParentLayout2 extends LinearLayout implements NestedScrollingParent2 {

    private static final String TAG = NSParentLayout2.class.getSimpleName();
    private View topView;
    private View bottomView;
    private int topViewHeight;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final LinearLayout.LayoutParams params;

    public NSParentLayout2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "NSParentLayout: ");
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        setOrientation(VERTICAL);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
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

        params.height = getMeasuredHeight();
        bottomView.setLayoutParams(params);
        //上述两部必须设置，使RV高度=屏幕高度

        topViewHeight = topView.getMeasuredHeight()-300;
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
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onStartNestedScroll: type=" + type);
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "onNestedScrollAccepted: type=" + type);
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d(TAG, "onNestedPreScroll: getScrollY = " + getScrollY() + " dy" + dy + " type=" + type);
        boolean PARENT_DOWN = dy > 0 && getScrollY() < topViewHeight;
        //canScrollVertically(-1)负值检查向上滚动，正向检查向下滚动
        boolean PARENT_UP = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);
        if (PARENT_DOWN || PARENT_UP) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onNestedScroll   dxConsumed: "
                + dxConsumed + " dyConsumed:"
                + dyConsumed + " dxUnconsumed:"
                + dxUnconsumed + " dyUnconsumed:"
                + dyUnconsumed + " type" + type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        Log.d(TAG, "onStopNestedScroll: type=" + type);
        mNestedScrollingParentHelper.onStopNestedScroll(target, type);
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }
}