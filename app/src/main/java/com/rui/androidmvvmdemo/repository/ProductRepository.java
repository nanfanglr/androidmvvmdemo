package com.rui.androidmvvmdemo.repository;

import android.text.TextUtils;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.model.AddressModel;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.androidmvvmdemo.model.PriceModel;
import com.rui.androidmvvmdemo.model.ProductDtlModel;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.netservice.NetService;
import com.rui.common.constant.APPValue;
import com.rui.retrofit2.basemodel.ResultModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

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
     * Rxjava操作符示范接口组合处理
     *
     * @param memberId
     * @return
     */
    public Observable<AddressModel> getAddressModel(int memberId) {
        return service.getAddressModel(memberId)
                .flatMap(addressModelResultModel -> {
                    if (addressModelResultModel.isSuccess()) {
                        AddressModel addressModel = addressModelResultModel.getObj();
                        int addressId = addressModel.getAddressId();
                        return Observable.zip(
                                service.getInternational(addressId),
                                service.getLocal(addressId),
                                service.getPay(addressId),
                                (interNationalModelResultModel, localModelResultModel, payModelResultModel) -> {
                                    if (interNationalModelResultModel.isSuccess())
                                        addressModel.setInterNationalModel(interNationalModelResultModel.getObj());
                                    if (localModelResultModel.isSuccess())
                                        addressModel.setLocalModel(localModelResultModel.getObj());
                                    if (payModelResultModel.isSuccess())
                                        addressModel.setPayModel(payModelResultModel.getObj());
                                    return addressModel;
                                }
                        );
                    } else {
                        return Observable.error(new Throwable("接口访问失败"));
                    }
                })
                .flatMap(addressModel -> getPriceOB(addressModel))
                ;
    }

    public Observable<AddressModel> getPriceOB(AddressModel addressModel) {
        return service.getPrice(addressModel.getLocalModel().getLocalId()
                , addressModel.getInterNationalModel().getInternationalId()
                , addressModel.getPayModel().getPayId())
                .flatMap((Function<ResultModel<PriceModel>, ObservableSource<AddressModel>>) priceModelResultModel -> {
                    if (priceModelResultModel.isSuccess())
                        addressModel.setPriceModel(priceModelResultModel.getObj());
                    return Observable.just(addressModel);
                });
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

    public Single<ResultModel<ProductDtlModel>> getProdDtl(int prodId, String prodNum) {
        return Single.create(emitter -> {
            ResultModel<ProductDtlModel> resultModel = new ResultModel<>();
            resultModel.setSuccess(true);
            ProductDtlModel model = new ProductDtlModel();
            model.setProd_NAME("女长裤(铅笔裤)");
            model.setProd_NUM("B8VB1786");
            model.setRack_RATE(199);

            List<LocalMedia> list0 = new ArrayList<>();
            list0.add(getLocalMedia("https://ws1.sinaimg.cn/large/0065oQSqly1fuh5fsvlqcj30sg10onjk.jpg",
                    System.currentTimeMillis(), APPValue.NET_IMAGE));
            list0.add(getLocalMedia("https://ws1.sinaimg.cn/large/0065oQSqly1fuo54a6p0uj30sg0zdqnf.jpg",
                    System.currentTimeMillis(), APPValue.NET_IMAGE));
            list0.add(getLocalMedia("https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg",
                    System.currentTimeMillis(), APPValue.NET_IMAGE));
            model.setImgsDT(list0);

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


    public Single<ResultModel<MultipleRvItemModel>> getMultipleData() {
        return Single.create(emitter -> {
            ResultModel<MultipleRvItemModel> resultModel = new ResultModel<>();
            resultModel.setSuccess(true);
            ResultModel.PageData<MultipleRvItemModel> pageData = new ResultModel.PageData<>();
            List<MultipleRvItemModel> list = new ArrayList<>();

            // https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg
            // https://ww1.sinaimg.cn/large/0065oQSqly1ftzsj15hgvj30sg15hkbw.jpg
            // https://ws1.sinaimg.cn/large/0065oQSqly1fubd0blrbuj30ia0qp0yi.jpg
            // https://ws1.sinaimg.cn/large/0065oQSqly1fuh5fsvlqcj30sg10onjk.jpg
            // https://ws1.sinaimg.cn/large/0065oQSqly1fuo54a6p0uj30sg0zdqnf.jpg
            // https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg

            for (int i = 0; i < 2; i++) {
                list.add(new MultipleRvItemModel(MultipleRvItemModel.SINGLE_IMG, "https://ww1.sinaimg.cn/large/0065oQSqly1ftf1snjrjuj30se10r1kx.jpg"));
                list.add(new MultipleRvItemModel(MultipleRvItemModel.SINGLE_TEXT, "https://ww1.sinaimg.cn/large/0065oQSqly1ftzsj15hgvj30sg15hkbw.jpg"));
                list.add(new MultipleRvItemModel(MultipleRvItemModel.TEXT_IMG, "https://ws1.sinaimg.cn/large/0065oQSqly1fubd0blrbuj30ia0qp0yi.jpg"));
                list.add(new MultipleRvItemModel(MultipleRvItemModel.TEXT_IMG, "https://ws1.sinaimg.cn/large/0065oQSqly1fuh5fsvlqcj30sg10onjk.jpg"));
                list.add(new MultipleRvItemModel(MultipleRvItemModel.TEXT_IMG, "https://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg"));
            }


            pageData.setList(list);
            resultModel.setPageData(pageData);
            resultModel.setTotal(29);
            emitter.onSuccess(resultModel);

        });
    }

}
