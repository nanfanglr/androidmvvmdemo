package com.rui.androidmvvmdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityProductDtlBinding;
import com.rui.androidmvvmdemo.di.viewmodel.ProductDtlViewModel;
import com.rui.androidmvvmdemo.ui.adapter.ProductImgAdapter;
import com.rui.common.base.BasePageVMActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class ProductDtlActivity extends BasePageVMActivity<
        ActivityProductDtlBinding
        , ProductDtlViewModel
        , ProductImgAdapter
        , LinearLayoutManager
        , RvOnListChangedCallback
        > implements
        View.OnClickListener {

    public static void actionStart(Context context, int prodId, String prodNum) {
        Intent intent = new Intent(context, ProductDtlActivity.class);
        intent.putExtra("prodId", prodId);
        intent.putExtra("prodNum", prodNum);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        initData();
    }

    private void initVM() {
        String prodNum = getIntent().getStringExtra("prodNum");
        viewModel.productNum.set(prodNum);
        int prodId = getIntent().getIntExtra("prodId", -1);
        viewModel.prodId = prodId;
    }


    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return null;
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvData;
    }

    private void initData() {
        viewModel.getData(APPValue.LOAD_FIRST);
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_product_dtl;
    }

    @Override
    protected Class<ProductDtlViewModel> getVMClass() {
        return ProductDtlViewModel.class;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case 0:
                break;
            case 1:
                break;
        }
    }
}
