package com.rui.androidmvvmdemo.di.viewmodel;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rui.androidmvvmdemo.di.repository.ProductRepository;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.common.base.BasePageViewModel;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.basemodel.ResultModel;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rui on 2019/2/12
 */
public class ProductImgFgViewModel extends BasePageViewModel {
    /**
     * 数据处理类
     */
    @Inject
    ProductRepository repository;
    /**
     * 数据加载的类型
     */
    private String dataType;
    /**
     * 搜索关键字
     */
    private String searchKeyWord;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public ProductImgFgViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    @Override
    public Single<ResultModel<ProductModel>> getDataOB() {
        return repository.getProducts(dataType, TextUtils.isEmpty(searchKeyWord) ? null : searchKeyWord, page);
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 关键字搜索
     *
     * @param searchKeyWord
     */
    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
        getData(APPValue.LOAD_FIRST);
    }


}
