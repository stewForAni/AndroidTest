package com.stew.androidtest.testforview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by stew on 2023/6/28.
 * mail: stewforani@gmail.com
 */
public class V3 extends View {
   public V3(Context context) {
      super(context);
   }

   public V3(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public V3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public V3(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
   }

   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      Log.d("TAG", "V3 onMeasure");
   }

   @Override
   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      super.onLayout(changed, l, t, r, b);
      Log.d("TAG", "V3 onLayout");
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      Log.d("TAG", "V3 onDraw");
   }
}
