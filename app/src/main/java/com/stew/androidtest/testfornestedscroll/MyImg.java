package com.stew.androidtest.testfornestedscroll;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by stew on 2023/3/15.
 * mail: stewforani@gmail.com
 */
public class MyImg extends androidx.appcompat.widget.AppCompatImageView {

   private static final String TAG = MyImg.class.getName();

   public MyImg(@NonNull Context context) {
      super(context);
   }

   public MyImg(@NonNull Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public MyImg(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      Log.d(TAG, "onMeasure: ");
   }

   @Override
   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      Log.d(TAG, "onLayout: ");
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      Log.d(TAG, "onDraw: ");
   }
}
