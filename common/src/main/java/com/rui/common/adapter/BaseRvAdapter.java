package com.rui.common.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rui.common.BR;
import com.rui.common.R;

import java.util.ArrayList;
import java.util.List;


/**
 * recyclerview适配器BaseQuickAdapter的进行mvvm公共代码封装
 *
 * @param <T>
 */
public class BaseRvAdapter<T> extends BaseQuickAdapter<T, BaseRvViewHolder> {
    /**
     * 点击事件的viewId集合
     */
    private List<Integer> clickIds = new ArrayList<>();

    public BaseRvAdapter(@LayoutRes int layoutResId) {
        super(layoutResId, new ArrayList<>());
    }

    public BaseRvAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void convert(BaseRvViewHolder helper, T item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.itemViewModel, item);
        binding.executePendingBindings();
        for (Integer id : clickIds) {
            helper.addOnClickListener(id);
        }
    }

    /**
     * 添加点击事件
     *
     * @param ids 需要添加点击事件的view的id
     */
    public void addItemChildClickListener(@IdRes int... ids) {
        for (int id : ids) {
            clickIds.add(id);
        }
    }


}
