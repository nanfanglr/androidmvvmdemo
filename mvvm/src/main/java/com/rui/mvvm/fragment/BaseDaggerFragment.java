package com.rui.mvvm.fragment;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rui.mvvm.dagger.modules.BaseFragmentModule;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by rui on 2017/10/31.
 */
public abstract class BaseDaggerFragment<DB extends ViewDataBinding> extends BaseFragment<DB>
        implements HasSupportFragmentInjector {

    @Inject
    @Named(BaseFragmentModule.CHILD_FRAGMENT_MANAGER)
    protected FragmentManager childFragmentManager;

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
}
