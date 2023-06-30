package com.stew.androidtest.testforrv;

import android.os.Bundle;
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
    RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        MyAdapter adapter = new MyAdapter(this);
        rv.setAdapter(adapter);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setChangeDuration(2000);
        rv.setItemAnimator(defaultItemAnimator);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        adapter.setData(list);
        adapter.notifyDataSetChanged();

        Random r = new Random();
        findViewById(R.id.tx).setOnClickListener(view -> {
            list.set(0, r.nextInt(1000));
            adapter.notifyItemChanged(0);
        });
    }
}
