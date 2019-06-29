package com.rui.androidmvvmdemo.di.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.common.base.BaseListViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;

import javax.inject.Inject;

/**
 * Created by rui on 2019/2/12
 */
public class EditImagesViewModel extends BaseListViewModel<LocalMedia> {

    @Inject
    public ObservableInt currentPostion;
    @Inject
    public ObservableInt rvItemPosition;
    @Inject
    public ObservableBoolean isShowSave;


    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public EditImagesViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    @Override
    public void getData(int loadRefresh) {

    }

    public void deleteItem(int pos) {
        items.remove(pos);
        isShowSave.set(true);
    }
}
