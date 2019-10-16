package com.rui.androidmvvmdemo;

import com.rui.androidmvvmdemo.di.ApplicationComponent;
import com.rui.androidmvvmdemo.di.component.DaggerApplicationComponent;
import com.rui.mvvm.BaseApplication.BaseApplication;


/**
 * Created by rui on 2019/2/12
 */
public class App extends BaseApplication {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    protected void initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        applicationComponent.inject(this);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
