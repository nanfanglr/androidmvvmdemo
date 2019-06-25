package com.rui.androidmvvmdemo.di.repository;

import android.text.TextUtils;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.model.ProductDtlModel;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.netservice.NetService;
import com.rui.common.constant.APPValue;
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
                if (TextUtils.isEmpty(keyWord) && TextUtils.equals("T", dataType)) {
                    model.setImg_URL("https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg");
                } else if (TextUtils.equals("T", dataType)) {
                    model.setImg_URL("https://ww1.sinaimg.cn/large/0065oQSqly1ftzsj15hgvj30sg15hkbw.jpg");
                }
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

    public Single<ResultModel<ProductDtlModel>> getProdDtlOB(int prodId, String prodNum) {
        return Single.create(emitter -> {
            ResultModel<ProductDtlModel> resultModel = new ResultModel<>();
            resultModel.setSuccess(true);
            ProductDtlModel model = new ProductDtlModel();
            model.setProd_NAME("女长裤(铅笔裤)");
            model.setProd_NUM("B8VB1786");
            model.setRack_RATE(199);
            List<ColorModel> list = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                ColorModel color = new ColorModel();
                color.setColor_NAME("紫色");
                color.clImgUrl.set("https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg");
                color.localCLImgs.add(getLocalMedia("https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg",
                        System.currentTimeMillis(), APPValue.NET_IMAGE));

                List<LocalMedia> list1 = new ArrayList<>();
                list1.add(getLocalMedia("https://ws1.sinaimg.cn/large/0065oQSqly1fuh5fsvlqcj30sg10onjk.jpg",
                        System.currentTimeMillis(), APPValue.NET_IMAGE));
                list1.add(getLocalMedia("https://ws1.sinaimg.cn/large/0065oQSqly1fuo54a6p0uj30sg0zdqnf.jpg",
                        System.currentTimeMillis(), APPValue.NET_IMAGE));
                list1.add(getLocalMedia("https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg",
                        System.currentTimeMillis(), APPValue.NET_IMAGE));
                color.localZSImgs.addAll(list1);

                list.add(color);
            }
            model.setColors(list);
            resultModel.setObj(model);
            emitter.onSuccess(resultModel);
        });
//        return homeService.getProductDtl(prodId > 0 ? prodId + "" : null, prodNum);
    }

    /**
     * 产生一个localMedia对象
     *
     * @param url
     * @param timeStamp
     * @param mimeType
     * @return
     */
    private LocalMedia getLocalMedia(String url, long timeStamp, int mimeType) {
        LocalMedia localMediaDT = new LocalMedia();
        localMediaDT.setCutPath(url);
        localMediaDT.setPath(url);
        localMediaDT.setCompressPath(url);
        localMediaDT.setDuration(timeStamp); //这个是用来表示图片是否已经更新的
        localMediaDT.setMimeType(mimeType);
        return localMediaDT;
    }

//    public Single<ResultModel> saveProdDtlOB(long id, String imgs) {
//        return homeService.saveProductImgs(id, imgs);
//    }


}
