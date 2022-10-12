package com.stew.androidtest.testforremoteview;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.stew.androidtest.R;

import java.util.Objects;

/**
 * Created by stew on 4/10/22.
 * mail: stewforani@gmail.com
 */
public class ExampleAppWidgetProvider extends AppWidgetProvider {

    private static final String TAG = ExampleAppWidgetProvider.class.getSimpleName();
    String a = "com.stew.androidtest.action.CLICK";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (Objects.equals(intent.getAction(), a)) {
            Log.d(TAG, "onReceive: inner");
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate: ");

        for (int appWidgetId : appWidgetIds) {
            //创建一个远程view，绑定我们要操控的widget布局文件
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.stewwidget);
            Intent intentClick = new Intent();
            //这个必须要设置，不然点击效果会无效
            intentClick.setClass(context,ExampleAppWidgetProvider.class);
            intentClick.setAction(a);
            intentClick.putExtra("id",appWidgetId);
            //PendingIntent表示的是一种即将发生的意图，区别于Intent它不是立即会发生的
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,0,intentClick,PendingIntent.FLAG_UPDATE_CURRENT);
            //为布局文件中的按钮设置点击监听
            remoteViews.setOnClickPendingIntent(R.id.text1111,pendingIntent);
            remoteViews.setTextViewText(R.id.text1111,"Cool Stew");
            //告诉AppWidgetManager对当前应用程序小部件执行更新
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }
    }
}
