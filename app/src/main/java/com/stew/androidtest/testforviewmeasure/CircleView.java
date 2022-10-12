package com.stew.androidtest.testforviewmeasure;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.stew.androidtest.R;

/**
 * Created by stew on 4/8/22.
 * mail: stewforani@gmail.com
 */
public class CircleView extends View {

    private static final String TAG = CircleView.class.getSimpleName();
    Paint paint1;
    Paint paint2;
    int color;
    float stroke;
    float w;
    float h;
    Rect r;
    RectF rf;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        color = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        stroke = a.getDimension(R.styleable.CircleView_circle_stroke, 0);
        a.recycle();

        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint1.setColor(color);
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(stroke);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wm = MeasureSpec.getMode(widthMeasureSpec);
        int ws = MeasureSpec.getSize(widthMeasureSpec);
        int hm = MeasureSpec.getMode(heightMeasureSpec);
        int hs = MeasureSpec.getSize(heightMeasureSpec);
        if (wm == MeasureSpec.AT_MOST && hm == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (wm == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, hs);
        } else if (hm == MeasureSpec.AT_MOST) {
            setMeasuredDimension(ws, 300);
        } else {
            setMeasuredDimension(ws, hs);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        w = getWidth() - getPaddingLeft() - getPaddingRight();
        h = getHeight() - getPaddingTop() - getPaddingBottom();

        int radius = (int) (Math.min(w, h) / 2);
        canvas.drawCircle(w / 2 + getPaddingLeft(), h / 2 + getPaddingTop(), radius, paint1);

        if (stroke != 0) {
            canvas.drawRect(0, 0, w, h, paint2);
            canvas.drawArc(0, 0, w, h, 0, 360, false, paint2);
        }

    }

}
