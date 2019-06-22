package com.rui.androidmvvmdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v4.app.FragmentActivity;

import com.rui.androidmvvmdemo.di.viewmodel.LoginViewModel;
import com.rui.androidmvvmdemo.ui.activity.LoginActivity;
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
public abstract class LoginModule {

    @Provides
    static SingleLiveEvent<Void> providesSingleLiveEventBoolean() {
        return new SingleLiveEvent<>();
    }

    @Provides
    static ObservableInt providesObservableInt() {
        return new ObservableInt();
    }

    @Provides
    static ObservableField<String> providesObservableFieldString() {
        return new ObservableField<String>();
    }


    @Binds
    @ActivityScope
    abstract FragmentActivity activity(LoginActivity activity);

    @Binds
    @IntoMap
    @ViewModelScope(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);
}
