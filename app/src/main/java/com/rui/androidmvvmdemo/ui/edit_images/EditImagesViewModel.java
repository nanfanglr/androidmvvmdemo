package com.rui.androidmvvmdemo.ui.edit_images;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.common.base.BaseListViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.retrofit2.basemodel.ResultModel;

import javax.inject.Inject;

import io.reactivex.Single;

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

    @Override
    protected Single<ResultModel<LocalMedia>> getDataOB() {
        return null;
    }

    public void deleteItem(int pos) {
        items.remove(pos);
        isShowSave.set(true);
    }
}
