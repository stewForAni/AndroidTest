package com.stew.androidtest.testfornestedscroll;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stew on 8/14/22.
 * mail: stewforani@gmail.com
 */
public class TestNestedScrollActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RvAdapter rvAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + ".item view");
        }

        recyclerView = findViewById(R.id.bottom_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        rvAdapter = new RvAdapter();
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setData(list);
        rvAdapter.notifyDataSetChanged();
    }

}
