package com.rui.androidmvvmdemo.di.viewmodel;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.livedata.SingleLiveEvent;
import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.inject.Inject;

/**
 * Created by rui on 2019/2/12
 */
public class MainViewModel extends BaseViewModel {

    /**
     * 关闭页面的事件
     */
    @Inject
    public SingleLiveEvent<Void> finishAct;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    @Inject
    public MainViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    /**
     * 点击事件退出示例2
     */
    public void closeAct(View view) {
        finishAct.call();
        Toast.makeText(getApplication(), view.getContext().getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }



}
