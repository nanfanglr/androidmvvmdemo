package com.rui.androidmvvmdemo.ui.multiple_rvitem;

import android.support.annotation.NonNull;

import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.androidmvvmdemo.repository.ProductRepository;
import com.rui.common.base.BasePageViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.basemodel.ResultModel;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rui on 2019/2/12
 */
public class MultipleRvItemViewModel extends BasePageViewModel<MultipleRvItemModel> {
    /**
     * 数据处理类
     */
    @Inject
    ProductRepository repository;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public MultipleRvItemViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    @Override
    public Single<ResultModel<MultipleRvItemModel>> getDataOB() {
        return repository.getMultipleData();
    }


}
