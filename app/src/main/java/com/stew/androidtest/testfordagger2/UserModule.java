package com.stew.androidtest.testfordagger2;

//import dagger.Module;
//import dagger.Provides;

/**
 * Created by stew on 7/31/22.
 * mail: stewforani@gmail.com
 */
//@Module
public class UserModule {
//    @Provides
    public User provideUser(){
        return new User();
    }
}
