package com.stew.androidtest.testForTransition;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.stew.androidtest.R;


/**
 * Created by stew on 2023/8/30.
 * mail: stewforani@gmail.com
 */
public class DetailActivity extends AppCompatActivity {

   public static final String HEADER_IMAGE = "header-image";
   public static final String HEADER_TITLE = "header-title";

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_detail);

      ImageView img = findViewById(R.id.img);
      TextView tv = findViewById(R.id.title);

      ViewCompat.setTransitionName(img, HEADER_IMAGE);
      ViewCompat.setTransitionName(tv, HEADER_TITLE);
   }

}
