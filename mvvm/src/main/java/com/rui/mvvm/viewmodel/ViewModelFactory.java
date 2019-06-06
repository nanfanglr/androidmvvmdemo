package com.rui.mvvm.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by rui on 2018/10/29.
 * MVVM ViewModelFactory
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    /**
     * ViewModel的Map集合，这个Map是通过dagger注解@IntoMap去生成的
     */
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    /**
     * 构造方法
     *
     * @param creators 这个参数是在DaggerApplicationComponent中有生成，通过module @IntoMap ViewModel去生成的
     */
    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
