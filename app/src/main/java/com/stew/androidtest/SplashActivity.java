package com.stew.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.util.AppLogUtil;

/**
 * Created by stew on 2023/5/11.
 * mail: stewforani@gmail.com
 */
public class SplashActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);
      startActivity(new Intent(this,MainActivity.class));
      finish();

      //AppLogUtil.addLifeLog(getClass().getName());

      //Log.i("TAG", "onCreate: ");

   }

}
