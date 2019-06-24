package com.rui.androidmvvmdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.FragmentProductImgBinding;
import com.rui.androidmvvmdemo.di.viewmodel.ProductImgFgViewModel;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.common.base.BaseListVMFragment;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by rui on 2019/6/24
 */
public class ProductImgFragment extends BaseListVMFragment<
        FragmentProductImgBinding
        , ProductImgFgViewModel
        , BaseRvAdapter<ProductModel>
        , LinearLayoutManager
        , RvOnListChangedCallback
        > {

    public static ProductImgFragment newInstance(Context context, String dataType, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("dataType", dataType);
        bundle.putString("title", title);
        return (ProductImgFragment) Fragment.instantiate(context, ProductImgFragment.class.getName(), bundle);
    }

    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return binding.refresh;
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvData;
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected Class<ProductImgFgViewModel> getVMClass() {
        return ProductImgFgViewModel.class;
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.fragment_product_img;
    }
}
