package com.rui.androidmvvmdemo.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityLoginBinding;
import com.rui.androidmvvmdemo.ui.main.activity.MainActivity;
import com.rui.androidmvvmdemo.ui.multiple_rvitem.MultipleRvItemActivity;
import com.rui.common.base.BaseTranActivity;

public class LoginActivity extends BaseTranActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
        initOB();
    }

    private void initEvent() {
        binding.tvMultiple.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, MultipleRvItemActivity.class));
        });
    }

    protected void initOB() {
        viewModel.getLoginSuccess().observe(this, aVoid -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    protected Class<LoginViewModel> getVMClass() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }


}
