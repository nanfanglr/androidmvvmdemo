package com.rui.common.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;

import com.rui.common.R;
import com.rui.mvvm.activity.BaseVMActivity;
import com.rui.mvvm.viewmodel.BaseViewModel;
import com.souyute.toolkit.BarUtils;

import javax.annotation.Nullable;

/**
 * Created by rui on 2018/4/3.
 */
public abstract class BaseTranActivity<DB extends ViewDataBinding,
        VM extends BaseViewModel>
        extends BaseVMActivity<DB, VM> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), 0);
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.addMarginTopEqualStatusBarHeight(getRootLayout());
    }

    public  ViewGroup getRootLayout(){
        return (ViewGroup) binding.getRoot();
    }
}
