package com.rui.common.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.annotation.NonNull;

import com.rui.common.constant.APPValue;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;
import com.rui.mvvm.network.basemodel.ResultModel;

import java.util.List;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by rui on 2018/12/22
 * 具有数据分页加载功能的列表viewModel基础类
 */
public abstract class BasePageViewModel<ITEM> extends BaseListViewModel<ITEM> {

    /**
     * 数据页码（第几页数据）
     */
    protected int page = 1;
    /**
     * 总计页数
     */
    protected int sumPage;

    /**
     * @param application ，getApplication()方法可以得到application
     */
    public BasePageViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData(int loadRefresh) {
        Timber.d("---------->getData.loadRefresh=>" + loadRefresh);
        if (loadRefresh <= APPValue.LOAD_REFRESH) {
            page = 1;
            sumPage = 100;
        }
        if (page > sumPage) {
            loadNoMoreData.set(true);
            return;
        }
        getDataOB().compose(singleTransformer(loadRefresh))
                .subscribe(listResultModel -> {
                    if (listResultModel.isSuccess()) {
                        List<ITEM> data = listResultModel.getData();
                        if (page == 1) {
                            items.clear();
                            items.addAll(data);
                        } else {
                            items.addAll(data);
                        }
                        sumPage = listResultModel.getSumPage();
                        page++;
                        if (items.size() == 0) empty.call();
                    } else {
                        dataLoadingError.setValue(listResultModel.getMsg());
                    }
                }, new ExceptionConsumer(getApplication()));
    }

    public abstract Single<ResultModel<ITEM>> getDataOB();

}
