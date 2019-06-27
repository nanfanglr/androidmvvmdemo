package com.rui.androidmvvmdemo.di.viewmodel;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.di.repository.ProductRepository;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.model.ProductDtlModel;
import com.rui.common.base.BaseListViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;

import javax.inject.Inject;

/**
 * Created by rui on 2019/2/12
 */
public class ProductDtlViewModel extends BaseListViewModel<ColorModel> {
    @Inject
    public ObservableInt headCurrentPos;
    @Inject
    public ObservableList<LocalMedia> headImgs;
    @Inject
    public ObservableField<String> productNum;
    /**
     * 商品id
     */
    public int prodId;
    @Inject
    public ObservableField<String> prodName;
    @Inject
    public ObservableDouble prodPrice;
    @Inject
    ProductRepository repository;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public ProductDtlViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    @Override
    public void getData(int loadRefresh) {
        addSubscribe(repository.getProdDtlOB(prodId, productNum.get())
                .compose(singleTransformer())
                .subscribe(productModelResultModel -> {
                    if (productModelResultModel.isSuccess()) {
                        ProductDtlModel obj = productModelResultModel.getObj();
                        if (obj != null) {
                            this.headImgs.addAll(obj.getImgsDT());
                            this.items.addAll(obj.getColors());
                            this.prodName.set(obj.getProd_NAME());
                            this.productNum.set(obj.getProd_NUM());
                            this.prodPrice.set(obj.getRack_RATE());
                        } else {
                            empty.call();
                        }
                    } else {
                        dataLoadingError.setValue(productModelResultModel.getMsg());
                    }
                }, new ExceptionConsumer(getApplication())));
    }
}
