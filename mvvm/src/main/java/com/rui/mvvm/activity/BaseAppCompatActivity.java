package com.rui.mvvm.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.annotation.Nullable;

/**
 * 为加载fragment抽出来的，此activity不需要获取数据显示因此没有MVP类
 * Created by rui on 2018/3/9.
 */
public abstract class BaseAppCompatActivity<DB extends ViewDataBinding>
        extends AppCompatActivity {

    /**
     * 当前页面的ViewDataBinding类，由databinding自动生成
     */
    protected DB binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutID(savedInstanceState));
    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getLayoutID(Bundle savedInstanceState);
}
