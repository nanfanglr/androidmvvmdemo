package com.rui.androidmvvmdemo.ui.login;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.FragmentActivity;

import com.rui.mvvm.dagger.modules.BaseActivityModule;
import com.rui.mvvm.dagger.modules.ObservableModule;
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
@Module(includes = {BaseActivityModule.class, ObservableModule.class})
public abstract class LoginModule {

    @Provides
    static SingleLiveEvent<Void> providesSingleLiveEventBoolean() {
        return new SingleLiveEvent<>();
    }

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(LoginActivity activity);

    @Binds
    @IntoMap
    @ViewModelScope(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);
}
