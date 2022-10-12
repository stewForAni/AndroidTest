package com.stew.androidtest.testforhook;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

/**
 * Created by stew on 3/20/22.
 * mail: stewforani@gmail.com
 */
public class HookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        TextView tx = findViewById(R.id.tx);
        tx.setOnClickListener(v -> {
            Toast.makeText(HookActivity.this, "111", Toast.LENGTH_SHORT).show();
        });

        HookClickManager.hook(tx);

    }
}
