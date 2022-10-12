package com.stew.androidtest.testfordagger2;

import dagger.Component;

/**
 * Created by stew on 7/31/22.
 * mail: stewforani@gmail.com
 */
//@Component(modules = UserModule.class)
public interface UserComponent {
    void inject(TestDagger2Activity activity);
}
