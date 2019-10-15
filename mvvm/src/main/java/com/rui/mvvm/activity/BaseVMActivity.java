package com.rui.mvvm.activity;

import android.app.ProgressDialog;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.rui.mvvm.viewmodel.BaseViewModel;

import javax.annotation.Nullable;

/**
 * 为加载fragment抽出来的，此activity不需要获取数据显示因此没有MVP类
 * Created by rui on 2018/3/9.
 */

public abstract class BaseVMActivity<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseAppCompatActivity<DB> {

    /**
     * 当前页面的ViewModel
     */
    protected VM viewModel;


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
    abstract protected VM obtainViewModel(@NonNull Class<VM> modelClass);

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
}

