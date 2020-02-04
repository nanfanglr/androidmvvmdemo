package com.rui.androidmvvmdemo.ui.rvbinding;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.app.FragmentActivity;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.mvvm.di.modules.BaseActivityModule;
import com.rui.mvvm.di.modules.LLModule;
import com.rui.mvvm.di.modules.ObservableModule;
import com.rui.mvvm.di.scopes.ActivityScope;
import com.rui.mvvm.di.scopes.ViewModelScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVVMSimpleTemplate on 10/16/2019 13:36
 * ================================================
 */
@Module(includes = {BaseActivityModule.class, ObservableModule.class, LLModule.class})
public abstract class RvBindingModule {

    //  示例代码，根据需求修改
    @Provides
    static ObservableList<ProductModel> providesObservableList() {
        return new ObservableArrayList<>();
    }

    @Provides
    static BaseRvAdapter<ProductModel> providesAdapter() {
        return new BaseRvAdapter(R.layout.item_product);
    }

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(RvBindingActivity activity);

    @Binds
    @IntoMap
    @ViewModelScope(RvBindingViewModel.class)
    abstract ViewModel bindViewModel(RvBindingViewModel viewModel);

}