package com.rui.androidmvvmdemo.di;

import com.rui.androidmvvmdemo.App;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.di.modules.AndroidModule;
import com.rui.mvvm.di.modules.ApplicationModule;
import com.rui.mvvm.di.modules.ViewModelFactoryModule;
import com.rui.retrofit2.di.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 这里全局ApplicationComponent注入
 * modules进行细分方便其他项目复用，AndroidModule，RetrofitModule，ViewModelFactoryModule
 * Created by rui on 2019/2/12
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        RetrofitModule.class,
        AndroidModule.class,
        ActivityBindingModule.class,
        ViewModelFactoryModule.class,
        SingletonModule.class
})
public interface ApplicationComponent {

    void inject(App application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(BaseApplication app);

        //这里可以修改提供application的形式 App,BaseApplication,Application
        //这样就可以方便的对其进行转换,以便在BaseApplication类库操作
        ApplicationComponent build();
    }

}
