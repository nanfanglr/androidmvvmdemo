package com.rui.androidmvvmdemo.di.module;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.di.viewmodel.ProductImgFgViewModel;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.ui.fragment.ProductImgFragment;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.mvvm.dagger.modules.BaseFragmentModule;
import com.rui.mvvm.dagger.modules.LLModule;
import com.rui.mvvm.dagger.scopes.FragmentScope;
import com.rui.mvvm.dagger.scopes.ViewModelScope;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = {BaseFragmentModule.class, LLModule.class})
public abstract class ProductImgFgModule {

    @Provides
    static BaseRvAdapter<ProductModel> providesAdapter() {
        return new BaseRvAdapter(R.layout.item_product);
    }

    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @FragmentScope
    abstract Fragment fragment(ProductImgFragment meFragment);

    @Binds
    @IntoMap
    @ViewModelScope(ProductImgFgViewModel.class)
    abstract ViewModel bindViewModel(ProductImgFgViewModel productImgViewModel);
}
