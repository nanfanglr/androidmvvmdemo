package com.rui.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.rui.mvvm.livedata.SingleLiveEvent;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rui on 2018/10/29
 */
public class BaseViewModel extends AndroidViewModel {

    /**
     * 数据加载中。主要用来显示加载中的效果
     */
    public final SingleLiveEvent<Boolean> dataLoading = new SingleLiveEvent();
//    /**
//     * 空数据
//     */
//    public final ObservableBoolean empty = new ObservableBoolean(false);
    /**
     * 数据加载出错
     */
    public final SingleLiveEvent<String> dataLoadingError = new SingleLiveEvent();


    /**
     * @param application，getApplication()方法可以得到application
     */
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    protected <T> SingleTransformer<T, T> singleTransformer() {
        return apiResultObservable -> {
            return apiResultObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        dataLoading.setValue(true);
                    })
                    .doAfterTerminate(() -> {
                        dataLoading.setValue(false);
                    });
        };
    }

}
