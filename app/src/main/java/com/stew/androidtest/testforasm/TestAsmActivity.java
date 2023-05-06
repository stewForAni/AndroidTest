package com.stew.androidtest.testforasm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.stew.androidtest.R;


/**
 * Created by stew on 2023/5/5.
 * mail: stewforani@gmail.com
 */
public class TestAsmActivity  extends AppCompatActivity {

    private static final String TAG = TestAsmActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asm);
    }

}