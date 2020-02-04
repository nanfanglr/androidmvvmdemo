package com.rui.mvvm.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.rui.mvvm.obcallback.RvOnListChangedCallback;

import dagger.Module;
import dagger.Provides;

/**
 * 提供竖向布局列表的基础类，主要为了复用代码
 * Created by rui on 2018/3/9.
 */
@Module
public class LLModule {

    @Provides
    static LinearLayoutManager providesLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    static RvOnListChangedCallback providesRvOnListChangedCallback() {
        return new RvOnListChangedCallback();
    }

}
