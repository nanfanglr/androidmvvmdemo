package com.rui.mvvm.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by rui on 2017/10/31.
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment implements HasSupportFragmentInjector {
    
    protected DB binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(savedInstanceState), container, false);
        return binding.getRoot();
    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getLayoutID(Bundle savedInstanceState);


}
