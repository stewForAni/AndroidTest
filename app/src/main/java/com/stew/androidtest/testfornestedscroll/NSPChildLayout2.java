package com.stew.androidtest.testfornestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChildHelper;

/**
 * Created by stew on 2023/8/8.
 * mail: stewforani@gmail.com
 */
public class NSPChildLayout2 extends FrameLayout implements NestedScrollingChild2 {

   public NSPChildLayout2(Context context) {
      super(context);
   }

   public NSPChildLayout2(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public NSPChildLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);

      //设置当前子View是否支持嵌套滑动，如果是false，父View无法响应嵌套滑动
      setNestedScrollingEnabled(true);




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
   public boolean startNestedScroll(int axes) {
      return getScrollingChildHelper().startNestedScroll(axes);
   }

   @Override
   public boolean startNestedScroll(int axes, int type) {
      return getScrollingChildHelper().startNestedScroll(axes, type);
   }

   @Override
   public void stopNestedScroll() {
      getScrollingChildHelper().stopNestedScroll();
   }

   @Override
   public void stopNestedScroll(int type) {
      getScrollingChildHelper().stopNestedScroll(type);
   }

   @Override
   public boolean hasNestedScrollingParent() {
      return getScrollingChildHelper().hasNestedScrollingParent();
   }

   @Override
   public boolean hasNestedScrollingParent(int type) {
      return getScrollingChildHelper().hasNestedScrollingParent(type);
   }

   @Override
   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                       int dyUnconsumed, int[] offsetInWindow) {
      return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
              dxUnconsumed, dyUnconsumed, offsetInWindow);
   }

   @Override
   public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                       int dyUnconsumed, int[] offsetInWindow, int type) {
      return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed,
              dxUnconsumed, dyUnconsumed, offsetInWindow, type);
   }

   @Override
   public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
      return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
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
