package com.rui.mvvm.di.modules;

import android.content.Context;

import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.di.scopes.ApplicationContext;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 全局application级别单例
 * Created by rui on 2018/3/9.
 */
@Module
public final class ApplicationModule {

    private ApplicationModule() {
        throw new AssertionError();
    }


    @Singleton
    @Provides
    @Named("debug")
    static Boolean provideIsDebug(BaseApplication app) {
        return app.isDebug();
    }

//    @Provides
//    @Singleton
//    static PropertiesManager providesPropertyManager(AssetManager assetManager, BaseApplication app) {
//        return new PropertiesManager(assetManager, app.isDebug());
//    }


//    @Provides
//    @Singleton
//    static StethoTool providesStetho(App application) {
//        return new StethoToolImpl(application.getApplicationContext());
//    }


//    @Provides
//    @Singleton
//    static ImageLoader providesImageLoader(App application) {
//        return new ImageLoader(application.getApplicationContext());
//    }

    @Provides
    @ApplicationContext
    static Context provideApplicationContext(BaseApplication app) {
        return app.getApplicationContext();
    }

}
