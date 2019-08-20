package com.rui.androidmvvmdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.app.FragmentActivity;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.di.viewmodel.ProductDtlViewModel;
import com.rui.androidmvvmdemo.ui.activity.ProductDtlActivity;
import com.rui.mvvm.dagger.modules.BaseActivityModule;
import com.rui.mvvm.dagger.modules.ObservableModule;
import com.rui.mvvm.dagger.modules.LLModule;
import com.rui.mvvm.dagger.scopes.ActivityScope;
import com.rui.mvvm.dagger.scopes.ViewModelScope;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = {BaseActivityModule.class, ObservableModule.class, LLModule.class})
public abstract class ProductDtlModule {

    @Provides
    static VPOnListChangedCallback providesVPOnListChangedCallback() {
        return new VPOnListChangedCallback();
    }

    @Provides
    static ObservableList<LocalMedia> providesLocalMediaList() {
        return new ObservableArrayList<>();
    }

    @Binds
    @IntoMap
    @ViewModelScope(ProductDtlViewModel.class)
    abstract ViewModel bindViewModel(ProductDtlViewModel viewModel);

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(ProductDtlActivity activity);
}
