package com.rui.mvvm.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.rui.mvvm.BR;
import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * Created by rui on 2018/10/29
 */
public abstract class BaseVMActivity<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseDaggerActivity<DB> {

    /**
     * 当前页面的ViewModel
     */
    protected VM viewModel;

    /**
     * 当前页面的ViewModel实例工厂
     */
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    protected ProgressDialog _processBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = obtainViewModel(getVMClass());
        bindingVM();
        setLoadingBar();
        setEorrorHint();
    }


    /**
     * 获取ViewModel实例
     *
     * @param modelClass
     * @return
     */
    protected VM obtainViewModel(@NonNull Class<VM> modelClass) {
        return ViewModelProviders.of(this, viewModelFactory).get(modelClass);
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
        binding.setVariable(BR.viewModel, viewModel);
    }

    /**
     * 如果该页面不需要统一的loading效果可以重写此方法
     */
    protected void setLoadingBar() {
        viewModel.dataLoading.observe(this, aBoolean -> {
            if (aBoolean) {
                showProcessBar(getLoadingText());
            } else {
                closeDialog();
            }
        });
    }

    protected void showProcessBar(String message) {
        if (_processBar == null) {
            _processBar = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        }
        if (_processBar.isShowing()) {
            _processBar.dismiss();
        }

        if (TextUtils.isEmpty(message)) {
            String str = "正在获取信息，请稍候！";
            _processBar.setMessage(str);
        } else {
            _processBar.setMessage(message);
        }
        _processBar.show();
        _processBar.setCanceledOnTouchOutside(false);
        _processBar.setCancelable(true);
    }

    protected void closeDialog() {
        if (null != _processBar) {
            _processBar.cancel();
        }
    }

    /**
     * 重写此方法改变loading效果的文案
     *
     * @return
     */
    protected String getLoadingText() {
        return "";
    }

    /**
     * 加载错误统一提示，如果不需要就重写此方法
     */
    protected void setEorrorHint() {
        viewModel.dataLoadingError.observe(this, msg -> {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
