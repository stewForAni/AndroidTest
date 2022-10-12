package com.stew.androidtest.testforserialize;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class TestParcelableActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parcel);

        Car car = new Car();
        car.name = "stew";
        car.brand = "Benz";
        car.type = "S600";

        findViewById(R.id.tx_parcel).setOnClickListener(v -> {
                    Intent intent = new Intent(this, TestSerializeActivity.class);
                    intent.putExtra("car", car);
                    startActivity(intent);
                }
        );

    }
}
