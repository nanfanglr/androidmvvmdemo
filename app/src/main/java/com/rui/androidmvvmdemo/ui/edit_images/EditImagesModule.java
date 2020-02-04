package com.rui.androidmvvmdemo.ui.edit_images;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;

import com.rui.mvvm.di.modules.BaseActivityModule;
import com.rui.mvvm.di.modules.ObservableModule;
import com.rui.mvvm.di.scopes.ActivityScope;
import com.rui.mvvm.di.scopes.ViewModelScope;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.rui.toolkit.DisplayUtils;
import com.rui.viewkit.GridSpacingItemDecoration;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by rui on 2019/2/12
 */
@Module(includes = {BaseActivityModule.class, ObservableModule.class})
public abstract class EditImagesModule {

    @Provides
    static VPOnListChangedCallback providesVPOnListChangedCallback() {
        return new VPOnListChangedCallback();
    }

    @Provides
    static GridLayoutManager providesLayoutManager(Context context) {
        return new GridLayoutManager(context, 4);
    }

    @Provides
    static GridSpacingItemDecoration providesItemDecoration(Context context) {
        return new GridSpacingItemDecoration(4, DisplayUtils.dip2px(context, 10), false);
    }

    @Provides
    static RvOnListChangedCallback providesRvOnListChangedCallback() {
        return new RvOnListChangedCallback();
    }

    @Binds
    @IntoMap
    @ViewModelScope(EditImagesViewModel.class)
    abstract ViewModel bindViewModel(EditImagesViewModel viewModel);

    @Binds
    @ActivityScope
    abstract FragmentActivity activity(EditImagesActivity activity);
}
