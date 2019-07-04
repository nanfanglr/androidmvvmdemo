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

    /**
     * 此方法适用于只有一种view type的情况
     */
    public ViewDataBinding getBinding() {
        return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
    }
    /**
     * 此方法适用于多种view type的情况
     */
    public ViewDataBinding getBinding(int layoutResId) {
        return (ViewDataBinding) itemView.getTag(layoutResId);
    }

}
