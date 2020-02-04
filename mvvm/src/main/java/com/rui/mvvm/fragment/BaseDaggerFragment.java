package com.rui.mvvm.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rui.mvvm.di.modules.BaseFragmentModule;
import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by rui on 2018/10/31
 */


public abstract class BaseDaggerFragment<DB extends ViewDataBinding, VM extends BaseViewModel> extends BaseLazyVMFragment<DB, VM>
        implements HasSupportFragmentInjector {

    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected FragmentManager childFragmentManager;
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //注入到当前fragment
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //注入到当前fragment
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidSupportInjection.inject(this);
        }
        super.onAttach(activity);
    }

    /**
     * 为嵌套的子fragment提供注入器
     *
     * @return
     */
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
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

}

