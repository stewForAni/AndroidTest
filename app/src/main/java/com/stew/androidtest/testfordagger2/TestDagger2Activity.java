package com.stew.androidtest.testfordagger2;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import javax.inject.Inject;

/**
 * Created by stew on 7/31/22.
 * mail: stewforani@gmail.com
 */
public class TestDagger2Activity extends AppCompatActivity {

//    @Inject
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
//        DaggerUserComponent.create().inject(this);
        Log.d("TestDagger2Activity", "onCreate: " + user.getName());
    }
}
