package com.rui.mvvm.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.livedata.SingleLiveEvent;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rui on 2018/10/29
 * MVVM BaseViewModel (ViewModel 不再持有 View，而是 store and manage UI-related data)
 * ViewModel objects are scoped to the Lifecycle passed to the ViewModelProvider when getting the ViewModel.
 * The ViewModel stays in memory until the Lifecycle it’s scoped to goes away permanently
 * —in the case of an activity, when it finishes;
 * in the case of a fragment, when it’s detached.
 *
 * @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel.html">ViewModel
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
     * Rxjava订阅池
     */
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * @param application，getApplication()方法可以得到application
     */
    public BaseViewModel(@NonNull BaseApplication application) {
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

    protected <T> ObservableTransformer<T, T> observableTransformer() {
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

    /**
     * 添加Rxjava订阅到容器
     *
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 在activity或则fragment销毁的时候，清空订阅池
     */
    @Override
    protected void onCleared() {
        //ViewModel销毁时会执行，同时取消所有异步任务，防止viewmodel泄漏
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

}
