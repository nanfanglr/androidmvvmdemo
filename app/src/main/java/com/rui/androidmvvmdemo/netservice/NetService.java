package com.rui.androidmvvmdemo.netservice;

import com.rui.androidmvvmdemo.model.UserModel;
import com.rui.mvvm.network.basemodel.ResultModel;

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




}
