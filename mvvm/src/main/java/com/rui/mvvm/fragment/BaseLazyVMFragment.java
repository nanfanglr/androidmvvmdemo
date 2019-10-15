package com.rui.mvvm.fragment;

import android.app.ProgressDialog;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rui.mvvm.viewmodel.BaseViewModel;

import timber.log.Timber;

/**
 * Created by rui on 2017/10/31.
 */
public abstract class BaseLazyVMFragment<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFragment<DB> {

    protected boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    protected boolean hasFetchData; // 标识已经触发过懒加载数据

    protected VM viewModel;

    protected ProgressDialog _processBar;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
            Timber.d("---------->setUserVisibleHint=>");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = obtainViewModel(getVMClass());
        bindingVM();
        isViewPrepared = true;
        setLoadingBar();
        setEorrorHint();
    }

    @Override
    public void onStart() {
        super.onStart();
        lazyFetchDataIfPrepared();
        Timber.d("---------->onStart=>");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，
        // fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false;
        isViewPrepared = false;
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
            _processBar = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_LIGHT);
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
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        });
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

    protected void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }
    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getLayoutID(Bundle savedInstanceState);

}