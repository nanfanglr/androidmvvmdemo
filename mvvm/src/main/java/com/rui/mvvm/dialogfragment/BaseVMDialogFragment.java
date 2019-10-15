package com.rui.mvvm.dialogfragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.rui.mvvm.fragment.BaseFragment;
import com.rui.mvvm.viewmodel.BaseViewModel;

/**
 * Created by rui on 2017/10/31.
 */
public abstract class BaseVMDialogFragment<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFragment<DB> {

    protected VM viewModel;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = obtainViewModel(getVMClass());
        bindingVM();
    }

    /**
     * 获取ViewModelClass
     *
     * @return
     */
    protected abstract Class<VM> getVMClass();

    /**
     * 绑定ViewModel,此方法可以扩展绑定
     */
    protected void bindingVM() {
        binding.setVariable(com.rui.mvvm.BR.viewModel, viewModel);
    }

    /**
     * 获取ViewModel实例
     *
     * @param modelClass
     * @return
     */
    abstract protected VM obtainViewModel(@NonNull Class<VM> modelClass);

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getLayoutID(Bundle savedInstanceState);


}