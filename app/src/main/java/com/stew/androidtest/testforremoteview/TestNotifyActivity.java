package com.stew.androidtest.testforremoteview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.stew.androidtest.R;

/**
 * Created by stew on 4/9/22.
 * mail: stewforani@gmail.com
 */
public class TestNotifyActivity extends AppCompatActivity {

    private static final String TAG = TestNotifyActivity.class.getSimpleName() + "~~";

    String CHANNEL_ID = "c-id";
    String CHANNEL_NAME = "c-name";
    NotificationManager manager;
    Notification notification;

    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_notify);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        findViewById(R.id.b1).setOnClickListener(v -> {
            no1();
        });

        findViewById(R.id.b2).setOnClickListener(v -> {
            no2();
        });

        findViewById(R.id.b3).setOnClickListener(v -> {

            no3();

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    update();
                    Thread.sleep(2000);
                    update1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });


    }

    private void update1() {
        builder.setContentText("download:complete")
                .setProgress(0,0,false);
        manager.notify(102,builder.build());
    }

    private void update() {
        builder.setContentText("download:50%")
                .setProgress(100,50,false);
        manager.notify(102,builder.build());
    }

    private void no3() {
        Intent intent = new Intent(this, TestNotifyActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Android 8.0 以上需包添加渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME
                    , NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            channel.setVibrationPattern(new long[]{0});
            manager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Stew")
                .setContentText("download:0%")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setProgress(100,0,false);

        manager.notify(102, builder.build());
    }

    private void no2() {
        Intent intent = new Intent(this, TestNotifyActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Android 8.0 以上需包添加渠道

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME
                    , NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 100, 100});
            manager.createNotificationChannel(channel);
        }

        RemoteViews r = new RemoteViews(getPackageName(), R.layout.stewnotify);
        r.setOnClickPendingIntent(R.id.image, pi);
        r.setTextViewText(R.id.time, String.valueOf(System.currentTimeMillis()));

        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)  //设置小图标  只能使用alpha图层的图片进行设置
                .setContent(r)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();


        manager.notify(100, notification);
    }


    int id=200;
    private void no1() {
        Intent intent = new Intent(this, TestNotifyActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Android 8.0 以上需包添加渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME
                    , NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
        }
        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Stew")
                .setContentText("1234567890")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher,"Add",pi)
                .build();
        manager.notify(id, notification);
        id++;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.deleteNotificationChannel(CHANNEL_ID);
        }
        manager.cancel(100);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");
    }
}
