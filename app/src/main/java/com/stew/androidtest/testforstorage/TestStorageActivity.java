package com.stew.androidtest.testforstorage;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.io.BufferedOutputStream;

/**
 * Created by stew on 2023/2/24.
 * mail: stewforani@gmail.com
 */
public class TestStorageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {


        //        Log.d(TAG, "onCreate: ---------");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            Log.d(TAG, "onCreate: "+ Environment.isExternalStorageLegacy());
//            Log.d(TAG, "onCreate: ---------");
//        }

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            Log.d(TAG, "onCreate:~~~~~~~~~ "+Environment.isExternalStorageLegacy());
//        }

//        String path = Environment.getExternalStorageDirectory().getPath()+"/"+getPackageName();
//        Log.d(TAG, "onCreate: ------1-----"+path);
//        File dir = new File(path);
//        File f = new File(path + "/stew.txt");// 创建文件
//
//        if (!dir.exists()) {// 目录存在返回false
//
//            dir.mkdirs();// 创建一个目录
//            Log.d(TAG, "onCreate: ------2-----"+dir.getPath());
//        }
//        if (!f.exists()) {// 文件存在返回false
//            Log.d(TAG, "onCreate: ------3-----");
//            try {
//                f.createNewFile();//创建文件
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        File f = new File(getExternalFilesDir("oooooo") + "/ppp.txt");// 创建文件
//
//        if (!f.exists()) {// 文件存在返回false
//            Log.d(TAG, "onCreate: ------2-----");
//            try {
//                f.createNewFile();//创建文件
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


//        * Images
//        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        * Audio
//        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        * Video
//        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//        * Download
//        MediaStore.Downloads.EXTERNAL_CONTENT_URI
//        * Documents
//        //Documents 稍微有些特殊，需要通过 Files 获取
//        MediaStore.Files.getContentUri("external")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/" + getPackageName());
            contentValues.put(MediaStore.Downloads.DISPLAY_NAME, "hi.txt");
            Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(getContentResolver().openOutputStream(uri));
                bufferedOutputStream.write("stew is so cool".getBytes());
                bufferedOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
