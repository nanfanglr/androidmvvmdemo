package com.rui.androidmvvmdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;

import com.rui.androidmvvmdemo.di.viewmodel.MultipleRvItemViewModel;
import com.rui.androidmvvmdemo.ui.activity.MultipleRvItemActivity;
import com.rui.mvvm.dagger.modules.BaseActivityModule;
import com.rui.mvvm.dagger.scopes.ActivityScope;
import com.rui.mvvm.dagger.scopes.ViewModelScope;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = BaseActivityModule.class)
public abstract class MultipleRvItemModule {

    @Provides
    static ObservableField<String> providesObservableFieldString() {
        return new ObservableField<String>();
    }

    @Provides
    static ObservableBoolean providesObservableBoolean() {
        return new ObservableBoolean();
    }

    @Provides
    static ObservableInt providesObservableInt() {
        return new ObservableInt();
    }

    @Provides
    static ObservableDouble providesObservableDouble() {
        return new ObservableDouble();
    }

    @Provides
    static GridLayoutManager providesLayoutManager(Context context) {
        return new GridLayoutManager(context, 4);
    }

    @Provides
    static RvOnListChangedCallback providesRvOnListChangedCallback() {
        return new RvOnListChangedCallback();
    }

    @Binds
    @IntoMap
    @ViewModelScope(MultipleRvItemViewModel.class)
    abstract ViewModel bindViewModel(MultipleRvItemViewModel viewModel);

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(MultipleRvItemActivity activity);
}
