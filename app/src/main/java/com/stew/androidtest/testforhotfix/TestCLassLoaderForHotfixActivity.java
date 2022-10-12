package com.stew.androidtest.testforhotfix;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by stew on 6/8/22.
 * mail: stewforani@gmail.com
 */
public class TestCLassLoaderForHotfixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);

        findViewById(R.id.button).setOnClickListener(v -> {

//            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/stewtest/new.jar");
//            DexClassLoader d = new DexClassLoader(
//                    file.getAbsolutePath(),
//                    getExternalCacheDir().getAbsolutePath(),
//                    null,
//                    getClassLoader());
//
//            try {
//                Class c = d.loadClass("com.stew.mytestlibrary.Stewabc");
//                Object obj =  c.newInstance();
//                Method method = c.getDeclaredMethod("go",new Class[0]);
//                Log.d("TAG", "onCreate: "+(String) method.invoke(obj,null));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
    }

}
