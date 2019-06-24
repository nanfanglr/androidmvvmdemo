package com.rui.androidmvvmdemo.ui.activity;

import android.os.Bundle;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityMainBinding;
import com.rui.androidmvvmdemo.di.viewmodel.MainViewModel;
import com.rui.mvvm.activity.BaseVMActivity;

public class MainActivity extends BaseVMActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOB();
    }

    protected void initOB() {
        viewModel.finishAct.observe(this, aVoid -> finish());
//        viewModel.closeKeyBoard.observe(this, aVoid -> {
//            if (getCurrentFocus() != null && getCurrentFocus()
//                    .getWindowToken() != null) {
//                ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
//                        .hideSoftInputFromWindow(getCurrentFocus()
//                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        });
    }

    @Override
    protected Class<MainViewModel> getVMClass() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }
}
