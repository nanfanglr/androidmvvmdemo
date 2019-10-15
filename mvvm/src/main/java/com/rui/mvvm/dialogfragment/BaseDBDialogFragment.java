package com.rui.mvvm.dialogfragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by rui on 2019/10/15
 */

public abstract class BaseDBDialogFragment<DB extends ViewDataBinding> extends Fragment {

    protected DB binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutID(savedInstanceState), container, false);
        return binding.getRoot();
    }

    /**
     * 初始化view
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int getLayoutID(Bundle savedInstanceState);

    /**
     * 获取屏幕的像素
     *
     * @param context
     * @return
     */
    private int[] getScreenWidthAndHight(Context context) {
        int[] wh = new int[2];

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        wh[0] = dm.widthPixels;
        wh[1] = dm.heightPixels;
        return wh;
    }


}
