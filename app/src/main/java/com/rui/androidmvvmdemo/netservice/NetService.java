package com.rui.androidmvvmdemo.netservice;

import com.rui.androidmvvmdemo.model.AddressModel;
import com.rui.androidmvvmdemo.model.InterNationalModel;
import com.rui.androidmvvmdemo.model.LocalModel;
import com.rui.androidmvvmdemo.model.PayModel;
import com.rui.androidmvvmdemo.model.PriceModel;
import com.rui.androidmvvmdemo.model.UserModel;
import com.rui.mvvm.network.basemodel.ResultModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * retrofit这里定义接口
 * Created by rui on 2019/2/12
 */
public interface NetService {

    /**
     * pos用户登陆
     *
     * @param userKey  手机号
     * @param userPswd 密码
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Single<ResultModel<UserModel>> login(
            @Field("userKey") String userKey,
            @Field("userPswd") String userPswd);

//    /**
//     * @param size    每页的条数
//     * @param page    页码
//     * @param keyword 搜索关键字：sku编码，内部码，国标码，
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("pos/prods")
//    Single<ResultModel<ProductModel>> searchProds(
//            @Field("size") int size,
//            @Field("page") int page,
//            @Field("keyword") String keyword);

    /**
     * 门店商品搜索
     *
     * @param size    每页的条数
     * @param page    页码
     * @param keyword 搜索关键字：sku编码，内部码，国标码，
     * @return
     */
    @GET("pos/prods")
    Single<ResultModel> searchProds(
            @Query("size") int size,
            @Query("page") int page,
            @Query("keyword") String keyword);

    /**
     * 示范接口
     *
     * @param memberId
     * @return
     */
    @GET("pos/test")
    Observable<ResultModel<AddressModel>> getAddressModel(int memberId);

    /**
     * 示范接口
     * @param addressId
     * @return
     */
    @GET("pos/test")
    Observable<ResultModel<LocalModel>> getLocal(int addressId);

    /**
     * 示范接口
     * @param addressId
     * @return
     */
    @GET("pos/test")
    Observable<ResultModel<InterNationalModel>> getInternational(int addressId);

    /**
     * 示范接口
     * @param addressId
     * @return
     */
    @GET("pos/test")
    Observable<ResultModel<PayModel>> getPay(int addressId);

    /**
     * 示范接口
     * @param localId
     * @param internationalId
     * @param payId
     * @return
     */
    @GET("pos/test")
    Observable<ResultModel<PriceModel>> getPrice(int localId, int internationalId, int payId);

}
