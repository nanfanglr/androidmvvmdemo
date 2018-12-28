package com.rui.common.oss;

import com.rui.mvvm.network.basemodel.ResultModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rui on 2018/9/17
 */
public interface OssService {

    @GET("osssign")
    Single<ResultModel<OssModel>> getYunStoreInfo(
            @Query("dir") String dir
    );
}
