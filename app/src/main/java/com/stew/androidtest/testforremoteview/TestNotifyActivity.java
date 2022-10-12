package com.stew.androidtest.testforremoteview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = TestNotifyActivity.class.getSimpleName()+"~~";

    String CHANNEL_ID = "c-id";
    String CHANNEL_NAME = "c-name";
    NotificationManager manager;
    Notification notification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        setContentView(R.layout.activity_notify);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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

        RemoteViews r = new RemoteViews(getPackageName(),R.layout.stewnotify);
        r.setOnClickPendingIntent(R.id.image,pi);
        r.setTextViewText(R.id.time,String.valueOf(System.currentTimeMillis()));

        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setWhen(System.currentTimeMillis())  //设置时间
                .setSmallIcon(R.mipmap.ic_launcher)  //设置小图标  只能使用alpha图层的图片进行设置
                .setContent(r)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();


        manager.notify(100, notification);
        manager.notify(100, notification);
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
