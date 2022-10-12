package com.stew.androidtest.testforhook;

import android.util.Log;
import android.view.View;

/**
 * Created by stew on 2/19/22.
 * mail: stewforani@gmail.com
 */
public class ProxyOnClickListener implements View.OnClickListener {

    View.OnClickListener listener;

    public ProxyOnClickListener(View.OnClickListener l) {
        this.listener = l;
    }

    @Override
    public void onClick(View v) {
        Log.d("ProxyOnClickListener", "this is hook");
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
