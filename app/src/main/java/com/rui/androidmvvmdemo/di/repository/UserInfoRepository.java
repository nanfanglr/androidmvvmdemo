package com.rui.androidmvvmdemo.di.repository;


import com.rui.androidmvvmdemo.model.UserModel;
import com.rui.androidmvvmdemo.netservice.NetService;
import com.rui.mvvm.network.basemodel.ResultModel;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 统一管理登录及用户信息的类（可以理解为保存登录状态和用户信息数据源），全局单例
 * 这个类存储用户信息，以及是否登录状态判断
 * Created by 0200030 on 2018/1/27.
 */
public class UserInfoRepository {

    private final NetService netService;
    /**
     * 用户信息
     */
    private UserModel userInfo;
    /**
     * 更新用户信息的数据源
     * 在需要更新用户新的页面，注入UserInfoRepository，获取到该对象后即可更新数据
     */
    private Subject<UserModel> subject;

    public UserInfoRepository(NetService netService) {
        subject = PublishSubject.create();
        this.netService = netService;
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUserInfo(UserModel user) {
        userInfo = user;
        subject.onNext(user);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    public Single<UserModel> getUserInfoOB() {
        return userInfo != null ? Single.just(userInfo) : Single.error(new Throwable("当前用户信息不存在"));
    }

    /**
     *
     * @param userKey
     * @param userPswd
     * @return
     */
    public Single<ResultModel<UserModel>> getLoginOB(String userKey, String userPswd) {
        return Single.create(emitter -> {
            ResultModel<UserModel> resultModel = new ResultModel<>();
            resultModel.setSuccess(true);
            UserModel userModel = new UserModel();
            userModel.setApiAutoToken("1234156s4dfas4d6fasd546546as4d6f5a4s6");
            userModel.setName("Jay");
            userModel.setId(1);
            resultModel.setObj(userModel);
            emitter.onSuccess(resultModel);
        });
//        return netService.login(userKey, userPswd);
    }

}
