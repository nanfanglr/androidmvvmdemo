package com.rui.androidmvvmdemo.ui.multiple_rvitem;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityMultipleRvItemBinding;
import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.common.base.BasePageVMActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.souyute.toolkit.ToastUtils;

public class MultipleRvItemActivity extends BasePageVMActivity<
        ActivityMultipleRvItemBinding
        , MultipleRvItemViewModel
        , MultipleRvItemAdapter
        , GridLayoutManager
        , RvOnListChangedCallback
        > {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.getData(APPValue.LOAD_FIRST);

        adapter.setSpanSizeLookup((gridLayoutManager, position) -> {
            int type = viewModel.items.get(position).type;
            if (type == MultipleRvItemModel.SINGLE_TEXT) {
                return 3;
            } else if (type == MultipleRvItemModel.SINGLE_IMG) {
                return 1;
            } else {
                return 4;
            }
        });

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (id == R.id.iv) {
                ToastUtils.showToast(this, "点击了图片文字的图片");
            } else if (id == R.id.tv) {
                ToastUtils.showToast(this, "点击了图片文字的文字");
            }
        });

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
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_multiple_rv_item;
    }

    @Override
    protected Class<MultipleRvItemViewModel> getVMClass() {
        return MultipleRvItemViewModel.class;
    }


}
