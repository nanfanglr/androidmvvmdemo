package com.rui.androidmvvmdemo.di;


import com.rui.androidmvvmdemo.ui.edit_images.EditImagesActivity;
import com.rui.androidmvvmdemo.ui.edit_images.EditImagesModule;
import com.rui.androidmvvmdemo.ui.login.LoginActivity;
import com.rui.androidmvvmdemo.ui.login.LoginModule;
import com.rui.androidmvvmdemo.ui.main.activity.MainActivity;
import com.rui.androidmvvmdemo.ui.main.activity.MainModule;
import com.rui.androidmvvmdemo.ui.multiple_rvitem.MultipleRvItemActivity;
import com.rui.androidmvvmdemo.ui.multiple_rvitem.MultipleRvItemModule;
import com.rui.androidmvvmdemo.ui.product_dtl.ProductDtlActivity;
import com.rui.androidmvvmdemo.ui.product_dtl.ProductDtlModule;
import com.rui.mvvm.dagger.scopes.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * activity注入器，提供activity所需要的注入对象
 * 可以理解为：新activity都需要到这里注册,以便获取需要的注入对象
 * Created by rui on 2019/2/13.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivityActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ProductDtlModule.class)
    abstract ProductDtlActivity productDtlActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = EditImagesModule.class)
    abstract EditImagesActivity editImagesActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = MultipleRvItemModule.class)
    abstract MultipleRvItemActivity multipleRvItemActivityInjector();


}
