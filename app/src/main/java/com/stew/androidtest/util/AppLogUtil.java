package com.stew.androidtest.util;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by stew on 2023/5/16.
 * mail: stewforani@gmail.com
 */
public class AppLogUtil {

    private static Application mContext;
    private static File logFile;
    private static Sink sink;
    private static BufferedSink bufferedSink;
    private static final ExecutorService logThread = Executors.newFixedThreadPool(1);

    public static void runOnWorkerThread(Runnable runnable) {
        logThread.execute(runnable);
    }

    public static void init(Application context) {
        mContext = context;
    }

    public static void addLifeLog(String data) {
        runOnWorkerThread(() -> {
            try {
                String path = mContext.getExternalFilesDir(null).toString() + "/logs/";
                if (logFile == null) {
                    logFile = new File(path, "lifelog.txt");
                }
                if (!logFile.exists()) {
                    File dir = new File(path);
                    if (dir.mkdirs()) {
                        if (logFile.createNewFile()) {
                            Log.d("addLifeLog", "logFile created");
                        }
                    }
                }
                sink = Okio.appendingSink(logFile);
                bufferedSink = Okio.buffer(sink);
                bufferedSink.writeUtf8(String.valueOf(System.currentTimeMillis()));
                bufferedSink.writeUtf8(" ----- ");
                bufferedSink.writeUtf8(data);
                bufferedSink.writeUtf8("\n");
                bufferedSink.flush();
                sink.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


}
