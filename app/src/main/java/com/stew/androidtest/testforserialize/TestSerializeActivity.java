package com.stew.androidtest.testforserialize;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;
import com.stew.androidtest.util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class TestSerializeActivity extends AppCompatActivity {


    private static final String TAG = TestSerializeActivity.class.getSimpleName();
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialize);

        Car car = getIntent().getParcelableExtra("car");
        Log.d(TAG, "onCreate: 1"+car.name);
        Log.d(TAG, "onCreate: 2"+car.brand);
        Log.d(TAG, "onCreate: 3"+car.type);

        findViewById(R.id.tx_serialize).setOnClickListener(v -> serialize());
        findViewById(R.id.tx_deserialize).setOnClickListener(v -> deserialize());

    }

    private void deserialize() {
        File file = new File(Constants.APP_ROOT_PATH + "/testclassloader/" + "User.txt");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User user = (User) objectInputStream.readObject();
            inputStream.close();
            Log.d(TAG, "deserialize: " + user.name);
            Log.d(TAG, "deserialize: " + user.age);
            Log.d(TAG, "deserialize: " + user.sex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.d(TAG, "deserialize: finally");
        }
    }


    private void serialize() {
        User user = new User();
        user.name = "stew";
        user.age = "3000";
        user.sex = "male";

        try {
            initFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(user);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFile() {

        String filePath = Constants.APP_ROOT_PATH + "/testclassloader/";
        File dir = new File(filePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (file == null) {
            file = new File(filePath, "User.txt");
        }

    }
}
