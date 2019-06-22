package com.rui.androidmvvmdemo.di.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rui.androidmvvmdemo.di.repository.UserInfoRepository;
import com.rui.androidmvvmdemo.model.UserModel;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.livedata.SingleLiveEvent;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;
import com.rui.mvvm.network.networkconfig.PropertiesManager;
import com.rui.mvvm.viewmodel.BaseViewModel;
import com.souyute.toolkit.SharedPreferencesUtils;
import com.souyute.toolkit.ToastUtils;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by rui on 2019/2/12
 */
public class LoginViewModel extends BaseViewModel {

    /**
     * edittext输入值
     */
    //@Inject变量在这里注入赋值后于构造方法赋值
    public ObservableField<String> phone;
    /**
     * 将edittext光标的位置移动到最后
     */
    @Inject
    public ObservableInt phoneLength;
    /**
     * 登录密码
     */
    @Inject
    public ObservableField<String> psw;
    /**
     *
     */
    @Inject
    UserInfoRepository repository;
    /**
     * 去设置token
     */
    @Inject
    PropertiesManager propertiesManager;
    /**
     * 登录是否成功的事件
     */
    @Inject
    SingleLiveEvent<Void> loginSuccess;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public LoginViewModel(@NonNull BaseApplication application
            , ObservableField<String> phone) {
        super(application);

        this.phone = phone;

        String inputPhone = SharedPreferencesUtils.loadString(getApplication(), APPValue.SP_PHONE);

        if (!TextUtils.isEmpty(inputPhone)) phone.set(inputPhone);

        this.phone.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                //需要判断inputPhone是否为空 否则会出现输入第一个数无法选择到末尾的情况
                if (!TextUtils.isEmpty(inputPhone)) phoneLength.set(inputPhone.length());
            }
        });
    }

    public SingleLiveEvent<Void> getLoginSuccess() {
        return loginSuccess;
    }

    @SuppressLint("CheckResult")
    public void onClickListenerBinding(View view) {
        if (TextUtils.isEmpty(phone.get())) {
            ToastUtils.showToast(getApplication(), "请填写手机号码");
            return;
        } else if (TextUtils.isEmpty(psw.get())) {
            ToastUtils.showToast(getApplication(), "请输入登录密码");
            return;
        } else {
            InputMethodManager imm = (InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘)
        }
        addSubscribe(repository.getLoginOB(phone.get(), psw.get())
                .compose(singleTransformer())
                .subscribe(resultModel -> {
                    if (resultModel.isSuccess()) {
                        loginSuccess.call();
                        //保存用户登录名称
                        SharedPreferencesUtils.saveString(getApplication(), APPValue.SP_PHONE, phone.get());
                        UserModel user = resultModel.getObj();
                        repository.updateUserInfo(user);
                        propertiesManager.setApiAutoToken(user.getApiAutoToken());
                        ToastUtils.showToast(getApplication(), user.getName() + "登录成功");
                    } else {
                        dataLoadingError.setValue(resultModel.getMsg());
                    }
                }, new ExceptionConsumer(getApplication()))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("--------------->onCleared");
    }
}
