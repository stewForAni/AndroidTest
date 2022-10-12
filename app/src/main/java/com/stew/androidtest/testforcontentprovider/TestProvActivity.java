package com.stew.androidtest.testforcontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;


/**
 * Created by stew on 3/25/22.
 * mail: stewforani@gmail.com
 */
public class TestProvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prov);

        Uri uri = Uri.parse("content://com.stew.androidtest2.provider/my_user");

        Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (cursor.moveToNext()) {
            Log.d("stew_prov_test", String.valueOf(cursor.getInt(0)));
            Log.d("stew_prov_test", cursor.getString(1));
        }

        cursor.close();

    }
}
