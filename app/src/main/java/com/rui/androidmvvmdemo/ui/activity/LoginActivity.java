package com.rui.androidmvvmdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityLoginBinding;
import com.rui.androidmvvmdemo.di.viewmodel.LoginViewModel;
import com.rui.mvvm.activity.BaseVMActivity;

public class LoginActivity extends BaseVMActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOB();
    }

    @Override
    protected Class<LoginViewModel> getVMClass() {
        return LoginViewModel.class;
    }

    protected void initOB() {
        viewModel.getLoginSuccess().observe(this, aVoid -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }


}
