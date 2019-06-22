package com.rui.androidmvvmdemo.di.module;


import com.rui.androidmvvmdemo.di.repository.UserInfoRepository;
import com.rui.androidmvvmdemo.netservice.NetService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 专门提供全局唯一对象，即：单例
 * Created by rui on 2019/2/13
 */
@Module
public final class SingletonModule {

    private SingletonModule() {
        throw new AssertionError();
    }

    @Provides
    @Singleton
    static NetService providesNetService(Retrofit retrofit) {
        return retrofit.create(NetService.class);
    }


    @Provides
    @Singleton
    static UserInfoRepository providesUserInfoRepository(NetService netService) {
        return new UserInfoRepository(netService);
    }


//    @Provides
//    @Singleton
//    static OssService providesOssService(Retrofit retrofit) {
//        return retrofit.create(OssService.class);
//    }


//    @Provides
//    @Singleton
//    static OssDataSource providesYunStoreDataSource(OssService service
//            , @ApplicationContext Context context) {
//        return new OssDataSource(service, context);
//    }


//    @Provides
//    @Singleton
//    static CommonRespository providesCommonRespository( ) {
//        return new CommonRespository();
//    }


}
