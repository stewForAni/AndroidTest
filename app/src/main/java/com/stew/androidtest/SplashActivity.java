package com.stew.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by stew on 2023/5/11.
 * mail: stewforani@gmail.com
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void add() {
        //Log.d("TAG", "add");
//      methodVisitor.visitLdcInsn("TAG");
//      methodVisitor.visitLdcInsn("add");
//      methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
//      methodVisitor.visitInsn(POP);


//      long startTime = System.currentTimeMillis();
//      long endTime = System.currentTimeMillis();
//      long costTime = endTime-startTime;
//      String time = "W"+costTime +"ms";
//      Log.d("TAG",time);

        long a = System.currentTimeMillis();
        Log.d("TAG", "aaa" + a);

    }

}
