package com.rui.androidmvvmdemo.di.viewmodel;

import android.support.annotation.NonNull;

import com.rui.common.base.BasePageViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.basemodel.ResultModel;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rui on 2019/2/12
 */
public class ProductImgFgViewModel extends BasePageViewModel {


    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public ProductImgFgViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    @Override
    public Single<ResultModel> getDataOB() {
        return null;
    }
}
