package com.stew.androidtest.testforlistview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.util.ArrayList;

/**
 * Created by stew on 4/1/22.
 * mail: stewforani@gmail.com
 */
public class TestListView2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview2);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + ".item view");
        }

        ListView listView = findViewById(R.id.ls);
        MyAdapter myAdapter = new MyAdapter();
        myAdapter.setData(list, this);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }
}
