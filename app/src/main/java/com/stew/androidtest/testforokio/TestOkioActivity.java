package com.stew.androidtest.testforokio;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stew.androidtest.R;

import java.io.File;
import java.nio.charset.StandardCharsets;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by stew on 7/6/22.
 * mail: stewforani@gmail.com
 */
public class TestOkioActivity extends AppCompatActivity {

    private static final String TAG = TestOkioActivity.class.getSimpleName();

    private String pathName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/okio_test/";
    private final String fileName = pathName + "okio_file.txt";
    private File file;
    private Sink sink;
    private BufferedSink bufferedSink;
    private Source source;
    private BufferedSource bufferedSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okio);
        createFile();
        readFromFile();
    }

    private void readFromFile() {
        if (file.exists()) {
            try {
                source = Okio.source(file);
                bufferedSource = Okio.buffer(source);
                String data = bufferedSource.readString(StandardCharsets.UTF_8);
                source.close();
                Log.d(TAG, "readFromFile: " + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile() {
        File f = new File(pathName);
        if (!f.exists()) {
            f.mkdirs();
        }
        writeData(new File(fileName));
    }

    private void writeData(File file) {
        Log.d(TAG, "writeData: ");
        try {
            sink = Okio.appendingSink(file);
            bufferedSink = Okio.buffer(sink);
            bufferedSink.writeUtf8("stew1");
            bufferedSink.writeUtf8("\n");
            bufferedSink.writeUtf8("stew2");
            bufferedSink.flush();
            sink.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
