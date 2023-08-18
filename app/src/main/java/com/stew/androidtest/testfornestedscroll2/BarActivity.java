package com.stew.androidtest.testfornestedscroll2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarActivity extends AppCompatActivity {

    private static final int DATA_COUNT_INTERVAL = 10;
    private static final int DATA_COUNT_MAX = 300;

    private BarChart mBarChart;
    private TextView mBtnStart;

    private TextView mTvDataCount;
    private TextView mBtnReset;
    private SeekBar mSbDataCount;

    private Random mRandom;

    private int mDataCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        mBarChart = findViewById(R.id.bar_chart);
        mBtnStart = findViewById(R.id.btn_start);

        mTvDataCount = findViewById(R.id.tv_data_count);
        mBtnReset = findViewById(R.id.btn_reset);
        mSbDataCount = findViewById(R.id.sb_data_count);

        mRandom = new Random();

        setDataCountText(100);

        mSbDataCount.setMax(DATA_COUNT_MAX / DATA_COUNT_INTERVAL);
        mSbDataCount.setProgress(mDataCount / DATA_COUNT_INTERVAL);
        mSbDataCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setDataCountText(progress * 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBarChart.setBarInfoList(createBarInfo());
            }
        });

        mBarChart.setBarInfoList(createBarInfo());

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBarChart.start();
            }
        });

    }

    private void setDataCountText(int count) {
        mDataCount = count;
        mTvDataCount.setText(String.format(getString(R.string.data_count), count));
    }

    private List<BarChart.BarInfo> createBarInfo() {
        List<BarChart.BarInfo> data = new ArrayList<>();

        for (int i = 1; i <= mDataCount; ++i) {
            data.add(new BarChart.BarInfo(i + "日", mRandom.nextFloat()));
        }

        return data;
    }

}
