package com.rui.androidmvvmdemo.di.repository;

import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.netservice.NetService;
import com.rui.mvvm.network.basemodel.ResultModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by rui on 2018/11/16
 */
public class ProductRepository {

    private final NetService service;

    @Inject
    public ProductRepository(NetService service) {
        this.service = service;
    }

    /**
     * 获取商品列表数据
     *
     * @param dataType
     * @param keyWord
     */
    public Single<ResultModel<ProductModel>> getProducts(String dataType, String keyWord, int page) {
        return Single.create(emitter -> {
            ResultModel<ProductModel> resultModel = new ResultModel<>();
            resultModel.setSuccess(true);
            ResultModel.PageData<ProductModel> pageData = new ResultModel.PageData<>();
            List<ProductModel> list = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                ProductModel model = new ProductModel();
                model.setProd_NUM("prod_NAME" + page + i);
                model.setProd_NAME("女长裤(铅笔裤)" + page + i);
                model.setRack_RATE(100 + i);
                model.setImg_URL("https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg");
                list.add(model);
            }
            pageData.setList(list);
            resultModel.setPageData(pageData);
            resultModel.setTotal(29);
            emitter.onSuccess(resultModel);
        });
//        return homeService.getProducts(dataType, TextUtils.isEmpty(keyWord) ? null : keyWord
//                , page, ResultModel.PAGE_LIMIT);
    }


}
