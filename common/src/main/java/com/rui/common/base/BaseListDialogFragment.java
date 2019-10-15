package com.rui.common.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rui.mvvm.dialogfragment.BaseDaggerDialogFragment;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by rui on 2018/12/27
 */
public abstract class BaseListDialogFragment<
        DB extends ViewDataBinding
        , VM extends BaseListViewModel
        , ADAPTER extends BaseQuickAdapter
        , LAYOUTMANAGER extends RecyclerView.LayoutManager
        , RVCB extends RvOnListChangedCallback
        > extends BaseDaggerDialogFragment<DB, VM> {
    /**
     * 列表适配器
     */
    protected ADAPTER adapter;
    /**
     * 列表布局管理器
     */
    protected LAYOUTMANAGER layoutManager;
    /**
     * 响应列表变化去刷新数据的回调
     */
    protected RVCB rvOnListChangedCallback;

    @Inject
    Provider<ADAPTER> adapterProvider;
    @Inject
    Provider<LAYOUTMANAGER> layoutManagerProvider;
    @Inject
    Provider<RVCB> rvcbProvider;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRV();
    }

    /**
     * 初始化列表相关的view及适配器
     */
    protected void initRV() {
        if (getRV() == null) return;
        adapter = createAdapter();
        layoutManager = createLayoutmanager();
        adapter.setNewData(viewModel.items);
        getRV().setLayoutManager(layoutManager);
        getRV().setAdapter(adapter);

//        binding.setVariable(BR.adapter, adapter);
//        binding.setVariable(BR.layoutManager, layoutManager);

        rvOnListChangedCallback = createRVCB();
        rvOnListChangedCallback.setAdapter(adapter);
        viewModel.items.addOnListChangedCallback(rvOnListChangedCallback);
    }

    /**
     * 创建列表适配器，可以在module中provides，也可以通过构造加@inject
     *
     * @return
     */
    private ADAPTER createAdapter() {
        return adapterProvider.get();
    }

    private RVCB createRVCB() {
        return rvcbProvider.get();
    }

    /**
     * 创建布局管理器，在module中provides
     *
     * @return
     */
    private LAYOUTMANAGER createLayoutmanager() {
        return layoutManagerProvider.get();
    }

    /**
     * 子类必须实现此方法，这样才能做列表的初始化
     *
     * @return
     */
    protected abstract RecyclerView getRV();

    /**
     * 子类必须实现此方法，这样才能做列表的初始化
     *
     * @return
     */
    protected abstract SmartRefreshLayout getRefreshLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //这里必须去除改监听，否则因callback中的adapter对象持有mainactivity的引用导致内存泄漏
        viewModel.items.removeOnListChangedCallback(rvOnListChangedCallback);
    }

}
