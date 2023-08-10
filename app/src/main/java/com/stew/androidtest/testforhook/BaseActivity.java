package com.stew.androidtest.testforhook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;
import com.stew.androidtest.testforwxexit.TestWxAcExitActivity;

/**
 * Created by stew on 2023/4/17.
 * mail: stewforani@gmail.com
 */
class BaseActivity extends AppCompatActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      HookActivityManager.hook(this);
      //Log.d("TAG-2", "onCreate: "+this);
   }

}

