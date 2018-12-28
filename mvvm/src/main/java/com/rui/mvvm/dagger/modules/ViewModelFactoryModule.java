package com.rui.mvvm.dagger.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.rui.mvvm.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Created by rui on 2018/10/29.
 * Dagger ViewModelFactoryModule
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
