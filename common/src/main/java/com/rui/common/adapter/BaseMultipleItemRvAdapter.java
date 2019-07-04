package com.rui.common.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.MultipleItemRvAdapter;

import java.util.List;

/**
 * RecyclerView中多种view type时适配器基础类
 * 封装部分公共代码
 *ss
 * @param <T>
 */
public abstract class BaseMultipleItemRvAdapter<T> extends MultipleItemRvAdapter<T, BaseRvViewHolder> {


    public BaseMultipleItemRvAdapter(@Nullable List<T> data) {
        super(data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(layoutResId, binding);
        return view;
    }


}
