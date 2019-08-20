package com.rui.common.base;

import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.rui.common.constant.APPValue;
import com.rui.mvvm.BaseApplication.BaseApplication;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;

import java.util.List;

import timber.log.Timber;

/**
 * Created by rui on 2018/12/22
 * 具有数据分页加载功能的列表viewModel基础类
 */
public abstract class BasePageViewModel<ITEM> extends BaseListViewModel<ITEM> {

    /**
     * 总数量
     */
    public ObservableInt total = new ObservableInt();
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
    public BasePageViewModel(@NonNull BaseApplication application) {
        super(application);
    }

    /**
     * 简单展示列表的通用处理方式，如果不满足需求，请重写改方法
     *
     * @param loadRefresh
     */
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
        addSubscribe(getDataOB()
                .compose(singleTransformer(loadRefresh))
                .subscribe(listResultModel -> {
                    if (listResultModel.isSuccess()) {
                        List<ITEM> data = listResultModel.getPageData().getList();
                        if (page == 1) {
                            items.clear();
                            items.addAll(data);
                        } else {
                            items.addAll(data);
                        }
                        total.set(listResultModel.getTotal());
                        sumPage = listResultModel.getSumPage();
                        page++;
                        if (items.size() == 0) empty.call();
                    } else {
                        dataLoadingError.setValue(listResultModel.getMsg()) ;
                    }
                }, new ExceptionConsumer(getApplication()))
        );
    }


}
