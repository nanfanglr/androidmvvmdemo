package com.rui.androidmvvmdemo.ui.product_dtl;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.model.ProductDtlModel;
import com.rui.androidmvvmdemo.repository.ProductRepository;
import com.rui.common.base.BaseListViewModel;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;
import com.rui.mvvm.network.basemodel.ResultModel;
import com.souyute.toolkit.ToastUtils;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rui on 2019/2/12
 */
public class ProductDtlViewModel extends BaseListViewModel<ColorModel> {
    @Inject
    public ObservableInt rvClickPos;
    @Inject
    public ObservableInt headCurrentPos;
    @Inject
    public ObservableList<LocalMedia> headImgs;
    @Inject
    public ObservableField<String> productNum;
    @Inject
    public ObservableBoolean isShowCommit;
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
        addSubscribe(repository.getProdDtl(prodId, productNum.get())
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

    @Override
    protected Single<ResultModel<ColorModel>> getDataOB() {
        return null;
    }

    public void saveProductImg() {
        ToastUtils.showToast(getApplication(), "上传图片");
    }
}
