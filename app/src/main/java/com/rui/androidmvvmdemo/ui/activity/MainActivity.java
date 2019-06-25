package com.rui.androidmvvmdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityMainBinding;
import com.rui.androidmvvmdemo.di.viewmodel.MainViewModel;
import com.rui.androidmvvmdemo.ui.adapter.FgPagerAdapter;
import com.rui.common.base.BaseTranActivity;

import javax.inject.Inject;

public class MainActivity extends BaseTranActivity<ActivityMainBinding, MainViewModel> {

    @Inject
    FgPagerAdapter fgPagerAdapter;

    /**
     * 声明一个long类型变量：用于存放上一点击“返回键”的时刻
     */
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initVM();
        initOB();
    }
    private void initView() {
        binding.tabLayout.setupWithViewPager(binding.vpContainer);
    }

    private void initVM() {
        fgPagerAdapter.setFragmentList(viewModel.items);
        binding.setFgPagerAdapter(fgPagerAdapter);
        viewModel.addPage();
    }

    @Override
    protected void onDestroy() {
        viewModel.items.clear();
        super.onDestroy();
    }


    protected void initOB() {
        viewModel.finishAct.observe(this, aVoid -> finish());
        viewModel.closeKeyBoard.observe(this, aVoid -> {
            if (getCurrentFocus() != null && getCurrentFocus()
                    .getWindowToken() != null) {
                ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    @Override
    protected Class<MainViewModel> getVMClass() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出商品快传", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
