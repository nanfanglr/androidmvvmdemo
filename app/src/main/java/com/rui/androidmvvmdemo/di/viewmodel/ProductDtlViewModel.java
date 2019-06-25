package com.rui.androidmvvmdemo.di.viewmodel;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

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
    ObservableField<String> prodName;
    @Inject
    public ObservableField<String> productNum;
    @Inject
    ObservableDouble prodPrice;
    @Inject
    ProductRepository repository;
    /**
     * 商品id
     */
    public int prodId;

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
//                            this.imgsDT.addAll(obj.getImgsDT());
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
