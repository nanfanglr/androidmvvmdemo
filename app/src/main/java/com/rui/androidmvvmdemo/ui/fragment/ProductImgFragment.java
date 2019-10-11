package com.rui.androidmvvmdemo.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.FragmentProductImgBinding;
import com.rui.androidmvvmdemo.di.viewmodel.MainViewModel;
import com.rui.androidmvvmdemo.di.viewmodel.ProductImgFgViewModel;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.androidmvvmdemo.ui.activity.ProductDtlActivity;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.common.base.BasePageVMFragment;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.disposables.Disposable;

/**
 * Created by rui on 2019/6/24
 */
public class ProductImgFragment extends BasePageVMFragment<
        FragmentProductImgBinding
        , ProductImgFgViewModel
        , BaseRvAdapter<ProductModel>
        , LinearLayoutManager
        , RvOnListChangedCallback
        > {

    private Disposable subscribe;
    /**
     * fragment所在activity的的viewmodel
     */
    private MainViewModel mainViewModel;

    public static ProductImgFragment newInstance(Context context, String dataType, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("dataType", dataType);
        bundle.putString("title", title);
        return (ProductImgFragment) Fragment.instantiate(context, ProductImgFragment.class.getName(), bundle);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVM();
        initOB();
        initRVClick();
    }

    private void initVM() {
        String dataType = getArguments().getString("dataType");
        viewModel.setDataType(dataType);
    }

    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return binding.refresh;
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvData;
    }

    private void initOB() {
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        subscribe = mainViewModel.subject.subscribe(s -> {
            //收到搜索事件
            viewModel.setSearchKeyWord(s);
        });
    }

    private void initRVClick() {
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            ProductModel clickItem = (ProductModel) adapter.getData().get(position);
            int proId = clickItem.getProd_ID();
            ProductDtlActivity.actionStart(getActivity(), proId, null);
        });
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.fragment_product_img;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscribe.dispose();
    }

    @Override
    protected void lazyFetchData() {
        // 当没有数据时才可以去请求数据
        if (viewModel.items.size() <= 0)
            viewModel.getData(APPValue.LOAD_FIRST);
    }

    @Override
    protected Class<ProductImgFgViewModel> getVMClass() {
        return ProductImgFgViewModel.class;
    }

}
