package com.rui.mvvm.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rui.mvvm.dagger.modules.BaseActivityModule;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 为加载fragment抽出来的，此activity不需要获取数据显示因此没有MVP类
 * Created by rui on 2018/3/9.
 */
public abstract class BaseDaggerActivity<DB extends ViewDataBinding>
        extends BaseAppCompatActivity<DB>
        implements
        HasFragmentInjector
        , HasSupportFragmentInjector {

    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    protected FragmentManager fragmentManager;

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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }
}
