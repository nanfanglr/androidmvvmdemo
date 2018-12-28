package com.rui.common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rui.common.BR;
import com.rui.common.R;
import com.rui.common.constant.APPValue;
import com.rui.common.databinding.EmptyViewVmBinding;
import com.rui.mvvm.fragment.BaseLazyVMFragment;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by rui on 2018/12/27
 */
public abstract class BaseListVMFragment<
        DB extends ViewDataBinding
        , VM extends BasePageViewModel
        , ADAPTER extends BaseQuickAdapter
        , LAYOUTMANAGER extends RecyclerView.LayoutManager
        , RVCB extends RvOnListChangedCallback
        > extends BaseLazyVMFragment<DB, VM> {
    /**
     * 空的布局
     */
    protected View emptyView;
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
    protected RvOnListChangedCallback rvOnListChangedCallback;

    @Inject
    Provider<ADAPTER> adapterProvider;
    @Inject
    Provider<LAYOUTMANAGER> layoutManagerProvider;
    @Inject
    Provider<RVCB> rvcbProvider;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        Timber.d("---------->onViewCreated=>"+this.toString());
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRV();
        initEmptyView();
        initEmptyOB();
    }

    /**
     * 初始化上来加载及下拉刷新ui
     */
    protected void initRefreshLayout() {
        if (getRefreshLayout() == null) return;
        ClassicsHeader classicsHeader = new ClassicsHeader(getActivity());
        classicsHeader.setTextSizeTitle(14);
        classicsHeader.setTextSizeTime(10);
        classicsHeader.setPrimaryColor(Color.parseColor("#eeeeee"));
        getRefreshLayout().setRefreshHeader(classicsHeader);
        ClassicsFooter classicsFooter = new ClassicsFooter(getActivity());
        classicsFooter.setTextSizeTitle(14);
        classicsFooter.setPrimaryColor(Color.parseColor("#eeeeee"));
        getRefreshLayout().setRefreshFooter(classicsFooter);
        getRefreshLayout().setHeaderHeight(46);
        getRefreshLayout().setFooterHeight(46);
        getRefreshLayout().setOnRefreshListener(refreshlayout -> {
            viewModel.getData(APPValue.LOAD_REFRESH);
        });
        getRefreshLayout().setOnLoadMoreListener(refreshlayout -> {
            viewModel.getData(APPValue.LOAD_MORE);
        });
    }


    /**
     * 子类必须实现此方法，这样才能做列表的初始化
     *
     * @return
     */
    protected abstract SmartRefreshLayout getRefreshLayout();

    /**
     * 初始化列表相关的view及适配器
     */
    protected void initRV() {
        if (getRV() == null) return;
        adapter = createAdapter();
        layoutManager = createLayoutmanager();
        binding.setVariable(BR.adapter, adapter);
        binding.setVariable(BR.layoutManager, layoutManager);

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
     * 初始化emptyview
     */
    protected void initEmptyView() {
        EmptyViewVmBinding emptyViewVmBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity())
                , R.layout.empty_view_vm, (ViewGroup) getRV().getParent(), false);
        emptyViewVmBinding.setViewModel(viewModel);
        emptyView = emptyViewVmBinding.getRoot();
    }

    /**
     * 数据为空的监听
     */
    private void initEmptyOB() {
        viewModel.empty.observe(this, aVoid -> {
                    viewModel.emptyText.set(this.getString(R.string.empty_no_data));
                    setEmptyView();
                }
        );
    }

    /**
     * 设置空布局显示的方法
     */
    protected void setEmptyView() {
        adapter.setEmptyView(emptyView);
    }

    @Override
    protected void setEorrorHint() {
        viewModel.dataLoadingError.observe(this, msg -> {
            if (adapter.getItemCount() > 0) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            } else {
                viewModel.emptyText.set(msg);
                setEmptyView();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //这里必须去除改监听，否则因callback中的adapter对象持有mainactivity的引用导致内存泄漏
        viewModel.items.removeOnListChangedCallback(rvOnListChangedCallback);
    }

}
