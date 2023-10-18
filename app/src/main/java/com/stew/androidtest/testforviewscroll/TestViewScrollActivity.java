package com.stew.androidtest.testforviewscroll;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;
import com.stew.androidtest.util.CommonUtil;

/**
 * Created by stew on 3/28/22.
 * mail: stewforani@gmail.com
 */
public class TestViewScrollActivity extends AppCompatActivity {
    private static final String TAG = TestViewScrollActivity.class.getSimpleName();
    private View view1;
    private View view2;
    private MySmoothScrollView view3;
    private float oldX;
    private float oldY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        Log.d(TAG, "onCreate: textView isClickable = "+findViewById(R.id.text3).isClickable());
        Log.d(TAG, "onCreate: button isClickable = "+findViewById(R.id.btn1).isClickable());

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) getWindow().getDecorView()).getChildAt(0);

        Log.d(TAG, "onCreate: content = "+viewGroup.getChildAt(0));
        Log.d(TAG, "onCreate: content = "+viewGroup.getChildAt(1));

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);

        Button btn =findViewById(R.id.btn1);
        View stew = findViewById(R.id.stew);

        btn.setOnClickListener(v -> {
            Log.d(TAG, "onClick: 3333");
            //stew.scrollTo(-30,-30);
            view3.smoothScroll(-300,-300,4000);
        });

        view3.setOnClickListener(v -> {
            Log.d(TAG, "onClick: 3333");
            view3.smoothScroll(-300,-300,4000);
        });

        findViewById(R.id.text1).setOnClickListener(v -> {
            Log.d(TAG, "scrollTo");
            view1.scrollTo(-20, -20);
        });

        findViewById(R.id.text2).setOnClickListener(v -> {
            Log.d(TAG, "scrollBy");
            view1.scrollBy(20, 20);
        });

        findViewById(R.id.text3).setOnClickListener(v -> {
            Log.d(TAG, "animation");
            ObjectAnimator.ofFloat(v, "translationY", 0, 100).setDuration(2000).start();
        });


        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float nowX = event.getRawX();
                float nowY = event.getRawY();
                Log.d(TAG, "onTouch: nowX = " + nowX + " nowY = " + nowY);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {
                        break;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        view2.setLeft((int) nowX - CommonUtil.dp2px(100));
                        view2.setTop((int) nowY - CommonUtil.dp2px(100));
                        view2.setRight((int) nowX + CommonUtil.dp2px(100));
                        view2.setBottom((int) nowY + CommonUtil.dp2px(100));
                    }

                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }

                }
                return true;
            }
        });

    }

}
