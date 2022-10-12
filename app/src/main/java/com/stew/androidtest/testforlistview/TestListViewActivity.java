package com.stew.androidtest.testforlistview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.util.ArrayList;

/**
 * Created by stew on 3/31/22.
 * mail: stewforani@gmail.com
 */
public class TestListViewActivity extends AppCompatActivity {

    MyViewPager viewPager;
    MyPagerAdapter pagerAdapter;
    ArrayList<View> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        View view1 = LayoutInflater.from(this).inflate(R.layout.pager_item, null, false);
        View view2 = LayoutInflater.from(this).inflate(R.layout.pager_item, null, false);
        View view3 = LayoutInflater.from(this).inflate(R.layout.pager_item, null, false);
        data = new ArrayList<>();
        data.add(view1);
        data.add(view2);
        data.add(view3);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MyPagerAdapter();
        pagerAdapter.setData(data);
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();

        initListView();

    }

    private void initListView() {
        for (View v : data) {

            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(i + ".item view");
            }

            ListView listView = v.findViewById(R.id.list_view);
            MyAdapter myAdapter = new MyAdapter();
            myAdapter.setData(list, this);
            listView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
    }

}
