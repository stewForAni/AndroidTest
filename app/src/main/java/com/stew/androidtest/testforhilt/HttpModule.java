package com.stew.androidtest.testforhilt;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * Created by stew on 8/1/22.
 * mail: stewforani@gmail.com
 */
@InstallIn(ApplicationComponent.class)
@Module
public class HttpModule {
    @Singleton
    @Provides
    public HttpTool getHttp(){
        return new HttpTool();
    }
}
