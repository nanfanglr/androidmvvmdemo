package com.rui.common.base;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.rui.common.constant.APPValue;
import com.rui.mvvm.livedata.SingleLiveEvent;
import com.rui.mvvm.viewmodel.BaseViewModel;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rui on 2018/12/22
 * 列表的viewModel基础类
 */
public abstract class BaseListViewModel<ITEM> extends BaseViewModel {

    public ObservableList<ITEM> items = new ObservableArrayList<>();
    /**
     * 下拉刷新完成
     */
    public ObservableBoolean finishRefreshing = new ObservableBoolean();
    /**
     * 上拉加载完成
     */
    public ObservableBoolean finishLoadmore = new ObservableBoolean();
    /**
     * 没有更多数据了
     */
    public ObservableBoolean loadNoMoreData = new ObservableBoolean();
    /**
     * 没有列表数据通知事件
     */
    public SingleLiveEvent<Void> empty = new SingleLiveEvent<>();
    /**
     * 列表为空的提示文案
     */
    public ObservableField<String> emptyText = new ObservableField<>();

    /**
     * @param application ，getApplication()方法可以得到application
     */
    public BaseListViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 存在下拉刷新需求是调用这个方法，否则调用上一个基类singleTransformer无参数的方法
     *
     * @param loadRefresh
     * @param <T>
     * @return
     */
    protected <T> SingleTransformer<T, T> singleTransformer(int loadRefresh) {
        return apiResultObservable -> {
            return apiResultObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        finishRefreshOrLoadMore(loadRefresh, false);
                    })
                    .doAfterTerminate(() -> {
                        finishRefreshOrLoadMore(loadRefresh, true);
                    });
        };
    }

    /**
     * 结束下拉刷新或加载更多动画效果
     *
     * @param loadRefresh
     * @param aboolean
     */
    protected void finishRefreshOrLoadMore(int loadRefresh, boolean aboolean) {
        if (loadRefresh == APPValue.LOAD_REFRESH) {
            finishRefreshing.set(aboolean);
            //下拉刷新重置loadNoMoreData的状态
            if (aboolean) loadNoMoreData.set(false);
        } else if (loadRefresh == APPValue.LOAD_MORE) {
            finishLoadmore.set(aboolean);
        }
    }

    public abstract void getData(int loadRefresh);

}
