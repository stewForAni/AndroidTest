package com.stew.androidtest.testforviewscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * Created by stew on 3/28/22.
 * mail: stewforani@gmail.com
 */
public class MySmoothScrollView extends LinearLayout {
    private static final String TAG = MySmoothScrollView.class.getSimpleName();
    private Scroller scroller;

    public MySmoothScrollView(Context context) {
        super(context);
        init(context);
    }

    public MySmoothScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        Log.d(TAG, "init: ");
        scroller = new Scroller(context);
    }

    public void smoothScroll(int x, int y, int duration) {
        int sx = getScrollX();
        int sy = getScrollY();
        Log.d(TAG, "smoothScroll: sx:" + sx);
        Log.d(TAG, "smoothScroll: sy:" + sy);
        scroller.startScroll(sx, sy, x - sx, y - sy, duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll: " + scroller.getCurrX() + "/" + scroller.getCurrY());
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
