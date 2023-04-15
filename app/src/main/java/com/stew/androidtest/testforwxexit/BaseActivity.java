package com.stew.androidtest.testforwxexit;

import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 2023/2/26.
 * mail: stewforani@gmail.com
 */
public class BaseActivity extends AppCompatActivity {

    private float x = 0;
    private float y = 0;
    private int scrollX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                Log.d("BaseActivity", "onTouchEvent: x=" + x + " y=" + y);
                break;
            case MotionEvent.ACTION_MOVE:
                float mX = event.getX();
                scrollX = (int) (mX-x);
                getWindow().getDecorView().scrollTo(-scrollX,0);
                break;
            case MotionEvent.ACTION_UP:
                if(scrollX>300){
                    finish();
                }else {
                    getWindow().getDecorView().scrollTo(0,0);
                }
                break;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.no, R.anim.slide_out);
    }
}
