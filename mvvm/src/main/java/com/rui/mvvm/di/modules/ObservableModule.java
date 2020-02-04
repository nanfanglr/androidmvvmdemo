package com.rui.mvvm.di.modules;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

import dagger.Module;
import dagger.Provides;

/**
 * 提供Observable的基础类对象
 * Created by rui on 2018/3/9.
 */
@Module
public class ObservableModule {

    @Provides
    public ObservableField<String> providesObservableFieldString() {
        return new ObservableField<String>();
    }

    @Provides
    public ObservableBoolean providesObservableBoolean() {
        return new ObservableBoolean();
    }

    @Provides
    public ObservableInt providesObservableInt() {
        return new ObservableInt();
    }

    @Provides
    public ObservableLong providesObservableLong() {
        return new ObservableLong();
    }

    @Provides
    public ObservableDouble providesObservableDouble() {
        return new ObservableDouble();
    }

    @Provides
    public ObservableFloat providesObservableFloat() {
        return new ObservableFloat();
    }

}
