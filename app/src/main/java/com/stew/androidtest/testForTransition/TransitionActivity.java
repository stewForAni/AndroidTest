package com.stew.androidtest.testForTransition;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stew.androidtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stew on 2023/8/29.
 * mail: stewforani@gmail.com
 */
public class TransitionActivity extends AppCompatActivity {
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);

//        Fade fade = new Fade();
//        fade.setDuration(1000);
//        getWindow().setEnterTransition(fade);

        RecyclerView rv = findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 3;
            }
        });

        rv.setLayoutManager(gridLayoutManager);

        TransitionAdapter adapter = new TransitionAdapter(this);

        Intent intent = new Intent(this, DetailActivity.class);

        adapter.setListener(new ItemClickListener() {
            @Override
            public void ItemClick(View item, int position) {
                Log.d("TAG", "ItemClick: "+position);

                @SuppressWarnings("unchecked")
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        TransitionActivity.this,
                        new Pair<>(item.findViewById(R.id.avatar), DetailActivity.HEADER_IMAGE),
                        new Pair<>(item.findViewById(R.id.avatartitle), DetailActivity.HEADER_TITLE)
                );

                ActivityCompat.startActivity(TransitionActivity.this, intent, activityOptions.toBundle());
            }
        });

        rv.setAdapter(adapter);

        for (int i = 0; i < 50; i++) {
            list.add("Item"+i);
        }

        adapter.setData(list);
        adapter.notifyDataSetChanged();


    }
}
