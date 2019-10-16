package com.rui.androidmvvmdemo.ui.rvbinding;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityRvBindingBinding;
import com.rui.androidmvvmdemo.model.ProductModel;
import com.rui.common.BR;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.common.base.BaseTranActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;

import javax.inject.Inject;


/**
 * ================================================
 * Description:
 * 本页面是用Databinding去实现RecyclerView绑定的例子，
 * 不同于继承common模块中的基础类,基础类中是通过代码去初始化RecyclerView相关配置
 * <p>
 * Created by MVVMSimpleTemplate on 10/16/2019 13:36
 * ================================================
 */
public class RvBindingActivity extends BaseTranActivity<ActivityRvBindingBinding, RvBindingViewModel> {

    @Inject
    BaseRvAdapter<ProductModel> adapter;
    /**
     * 列表布局管理器
     */
    @Inject
    LinearLayoutManager layoutManager;
    /**
     * 响应列表变化去刷新数据的回调
     */
    @Inject
    RvOnListChangedCallback rvOnListChangedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRV();
        viewModel.getData(APPValue.LOAD_FIRST);
    }

    /**
     * 初始化列表相关的view及适配器
     */
    protected void initRV() {
        //绑定适配器
        binding.setVariable(BR.adapter, adapter);
        //绑定布局管理器，还可以绑定装饰器
        binding.setVariable(BR.layoutManager, layoutManager);
        //列表数据变化回调，如果数据不发生变化则不需要有
        rvOnListChangedCallback.setAdapter(adapter);
        viewModel.items.addOnListChangedCallback(rvOnListChangedCallback);
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_rv_binding;
    }

    @Override
    protected Class<RvBindingViewModel> getVMClass() {
        return RvBindingViewModel.class;
    }
}
