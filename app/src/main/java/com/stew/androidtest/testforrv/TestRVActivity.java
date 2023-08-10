package com.stew.androidtest.testforrv;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by stew on 2023/6/29.
 * mail: stewforani@gmail.com
 */
public class TestRVActivity extends AppCompatActivity {

    private static final String TAG = TestRVActivity.class.getSimpleName();
    RecyclerView rv;
    List<InstallEntity> list = new ArrayList<>();
    MyAdapter adapter = new MyAdapter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        adapter.setListener(position -> {
            Log.d(TAG, "ItemClick: " + position);
            download(position);
        });


        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setChangeDuration(2000);
        rv.setItemAnimator(defaultItemAnimator);


        for (int i = 0; i < 50; i++) {
            InstallEntity entity = new InstallEntity();
            entity.id = i;
            entity.progress = "Install";
            list.add(entity);
        }

        adapter.setData(list);
        adapter.notifyDataSetChanged();

    }

    private void download(int position) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                list.get(position).progress = String.valueOf(i);
                runOnUiThread(() -> {
                            adapter.notifyItemChanged(position, "set-progress");
                        }
                );
            }
        });
        t.start();
    }
}
