package com.rui.mvvm.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rui.mvvm.di.modules.BaseActivityModule;
import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by rui on 2018/10/29
 */
public abstract class BaseDaggerActivity<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseVMActivity<DB, VM>
        implements
        HasFragmentInjector
        , HasSupportFragmentInjector {

    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    protected FragmentManager fragmentManager;
    /**
     * 当前页面的ViewModel实例工厂
     */
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //这个输入要写在父类的oncreate前面，否则屏幕旋转后，
        // activity重建时会出现supportFragmentInjector() returned null的报错
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取ViewModel实例
     *
     * @param modelClass
     * @return
     */
    protected VM obtainViewModel(@NonNull Class<VM> modelClass) {
        return ViewModelProviders.of(this, viewModelFactory).get(modelClass);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

}
