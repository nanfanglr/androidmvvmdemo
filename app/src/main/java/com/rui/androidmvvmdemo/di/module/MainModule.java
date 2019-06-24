package com.rui.androidmvvmdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;

import com.rui.androidmvvmdemo.di.viewmodel.MainViewModel;
import com.rui.androidmvvmdemo.ui.activity.MainActivity;
import com.rui.mvvm.dagger.modules.BaseActivityModule;
import com.rui.mvvm.dagger.scopes.ActivityScope;
import com.rui.mvvm.dagger.scopes.ViewModelScope;
import com.rui.mvvm.livedata.SingleLiveEvent;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = BaseActivityModule.class)
public abstract class MainModule {

    @Provides
    static SingleLiveEvent<Void> providesSingleLiveEventVoid() {
        return new SingleLiveEvent<Void>();
    }

    @Binds
    @IntoMap
    @ViewModelScope(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);


    @Binds
    @ActivityScope
    abstract FragmentActivity activity(MainActivity activity);
}
