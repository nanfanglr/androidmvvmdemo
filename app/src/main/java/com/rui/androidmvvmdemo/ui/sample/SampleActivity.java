package com.rui.androidmvvmdemo.ui.sample;

import android.content.Intent;
import android.os.Bundle;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivitySampleBinding;
import com.rui.androidmvvmdemo.ui.login.LoginActivity;
import com.rui.androidmvvmdemo.ui.multiple_rvitem.MultipleRvItemActivity;
import com.rui.mvvm.activity.BaseAppCompatActivity;

public class SampleActivity extends BaseAppCompatActivity<ActivitySampleBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.button.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        binding.button1.setOnClickListener(v -> {
            startActivity(new Intent(this, MultipleRvItemActivity.class));
        });
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_sample;
    }
}
