package com.rui.androidmvvmdemo.ui.rvbinding;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.repository.ProductRepository;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.retrofit2.apierror.ExceptionConsumer;
import com.rui.mvvm.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class RvBindingViewModel extends BaseViewModel {

    @Inject
    ProductRepository repository;

    @Inject
    public ObservableList<ProductModel> items;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public RvBindingViewModel(@NonNull BaseApplication application) {
        super(application);
    }


    public void getData(int loadRefresh) {
        //这个简单列表不处理分页的情况，因此上拉加载直接设置完成
        addSubscribe(repository.getProducts("T",  null, 1)
                .compose(singleTransformer())
                .subscribe(listResultModel -> {
                    if (listResultModel.isSuccess()) {
                        List<ProductModel> data = listResultModel.getPageData().getList();
                        items.clear();
                        items.addAll(data);
                    } else {
                        dataLoadingError.setValue(listResultModel.getMsg());
                    }
                }, new ExceptionConsumer(getApplication()))
        );
    }


//    示例代码，登录方法
//    public void login() {
//        addSubscribe(repository.loginOB(phone.get(), psw.get())
//                .compose(singleTransformer())
//                .subscribe(resultModel -> {
//                    if (resultModel.isSuccess()) {
//                     //处理登录成功逻辑
//
//                    } else {
//                        dataLoadingError.setValue(resultModel.getMsg());
//                    }
//                }, new ExceptionConsumer(getApplication()))
//        );
//    }

}
