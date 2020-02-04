package com.rui.androidmvvmdemo.ui.main.activity;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.rui.androidmvvmdemo.ui.main.fragment.ProductImgFgModule;
import com.rui.androidmvvmdemo.ui.main.fragment.ProductImgFragment;
import com.rui.mvvm.di.modules.BaseActivityModule;
import com.rui.mvvm.di.modules.ObservableModule;
import com.rui.mvvm.di.scopes.ActivityScope;
import com.rui.mvvm.di.scopes.FragmentScope;
import com.rui.mvvm.di.scopes.ViewModelScope;
import com.rui.mvvm.livedata.SingleLiveEvent;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = {BaseActivityModule.class, ObservableModule.class})
public abstract class MainModule {

    @Provides
    static ObservableList<Fragment> providesObservableList() {
        return new ObservableArrayList<Fragment>();
    }

    @Provides
    static Subject<String> providesSubject() {
        return PublishSubject.create();
    }

    @Provides
    static SingleLiveEvent<Void> providesSingleLiveEventVoid() {
        return new SingleLiveEvent<Void>();
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = ProductImgFgModule.class)
    abstract ProductImgFragment productImageFragmentInjector();


    @Binds
    @IntoMap
    @ViewModelScope(MainViewModel.class)
    abstract ViewModel bindViewModel(MainViewModel mainViewModel);

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(MainActivity activity);
}
