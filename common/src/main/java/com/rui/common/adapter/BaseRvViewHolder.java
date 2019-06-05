package com.rui.common.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.rui.common.R;


/**
 * BaseQuickAdapter的BaseViewHolder进行mvvm公共代码封装
 */
public class BaseRvViewHolder extends BaseViewHolder {

    public BaseRvViewHolder(View view) {
        super(view);
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }

}
