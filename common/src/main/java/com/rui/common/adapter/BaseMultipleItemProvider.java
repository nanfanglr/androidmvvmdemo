package com.rui.common.adapter;

import android.databinding.ViewDataBinding;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.rui.common.BR;

/**
 * 多布局适配器BaseMultipleItemRvAdapter加载item布局、处理相关逻辑的基础类
 * 封装公共代码
 *
 * @param <T>
 */
public abstract class BaseMultipleItemProvider<T> extends BaseItemProvider<T, BaseRvViewHolder> {
    /**
     * 这个方法是数据进行绑定的方法，如果需要对item的view做更多操作，
     * 则需要继承该类，重写改方法，实现自需要的业务逻辑
     *
     * @param helper
     * @param data
     * @param position
     */
    @Override
    public void convert(BaseRvViewHolder helper, T data, int position) {
        ViewDataBinding binding = helper.getBinding(layout());
        binding.setVariable(BR.itemViewModel, data);
        binding.executePendingBindings();
    }

}
