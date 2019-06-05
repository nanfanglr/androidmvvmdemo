/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rui.common.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rui.common.imageloader.ImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import timber.log.Timber;

/**
 * xml中自定义绑定属性
 */
public class CustomBindings {
    /**
     * ViewPager绑定
     *
     * @param viewPager
     * @param fgPagerAdapter
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"app:vpAdapter"}, requireAll = false)
    public static void setVpAdapter(ViewPager viewPager, PagerAdapter fgPagerAdapter) {
        PagerAdapter oldAdapter = viewPager.getAdapter();
        if (oldAdapter != fgPagerAdapter) {
            viewPager.setAdapter(fgPagerAdapter);
        }
    }

    /**
     * ViewPager绑定当前的页面
     *
     * @param viewPager
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"app:vpCurrentPos"})
    public static void setVpCurrentPos(ViewPager viewPager, int vpCurrentPos) {
        Timber.d("---------->setVpCurrentPos.vpCurrentPos=>" + vpCurrentPos);
        viewPager.setCurrentItem(vpCurrentPos);
    }

    /**
     * RecyclerView绑定
     *
     * @param recyclerView
     * @param items
     * @param adapter
     * @param layoutManager
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"app:rvItems", "app:rvAdapter", "app:rvLayoutManager", "app:rvDecoration"}, requireAll = false)
    public static void setRvItems(RecyclerView recyclerView, List items, BaseQuickAdapter adapter
            , RecyclerView.LayoutManager layoutManager, RecyclerView.ItemDecoration decoration) {
        BaseQuickAdapter oldAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
        if (oldAdapter != adapter) {
//            Timber.d("---------->setRvItems=>");
            if (adapter != null && items != null) adapter.setNewData(items);
            if (decoration != null) recyclerView.addItemDecoration(decoration);
            if (layoutManager != null) recyclerView.setLayoutManager(layoutManager);
            if (adapter != null) recyclerView.setAdapter(adapter);
        }
    }

    /**
     * Glide加载图片
     *
     * @param imageView
     * @param url
     * @param timeStamp
     */
    @BindingAdapter(value = {"app:ivUrl", "app:ivTimeStamp"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, long timeStamp) {
        ImageLoader.displayImage(imageView.getContext(), !TextUtils.isEmpty(url) ? url : null, imageView, timeStamp);
    }

    /**
     * Glide加载图片
     *
     * @param imageView
     * @param url
     * @param timeStamp
     */
    @BindingAdapter(value = {"app:ivHeadUrl", "app:ivTimeStamp"}, requireAll = false)
    public static void setImageHeadUri(ImageView imageView, String url, long timeStamp) {
        ImageLoader.displayHeadImage(imageView.getContext(), url, imageView);
    }

    /**
     * View类的OnKeyListener绑定，
     * 因为在ViewBindingAdapter这个databinding类库中没有去实现这个方法
     *
     * @param view
     * @param listener
     */
    @BindingAdapter({"android:onKey"})
    public static void setOnKeyListener(View view, View.OnKeyListener listener) {
        view.setOnKeyListener(listener);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, int text) {
        view.setText(text + "");
    }

//    @BindingAdapter("android:text")
//    public static void setText(EditText view, double text) {
//        String str = String.format("%.02f", text);
//        view.setText(str);
//        view.setSelection(str.length());
//    }

    @BindingAdapter("app:etselection")
    public static void setText(EditText view, int selection) {
        Timber.d("----------->selection=" + selection);
        view.requestFocus();
        view.setSelection(selection);
    }

//    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
//    public static double getTextString(TextView view) {
//        try {
//            double aDouble = Double.parseDouble(view.getText().toString());
//            return aDouble;
//        } catch (Exception e) {
//            return 0.00;
//        }
//    }

    /**
     * 下拉 上拉 加载效果完成
     *
     * @param smartRefreshLayout
     * @param finishRefreshing
     * @param finishLoadmore
     */
    @BindingAdapter(value = {"app:finishRefreshing", "app:finishLoadmore", "app:loadNoMoreData"}, requireAll = false)
    public static void setFinishRefreshing(SmartRefreshLayout smartRefreshLayout
            , boolean finishRefreshing, boolean finishLoadmore, boolean loadNoMoreData) {
        if (finishLoadmore) {
            smartRefreshLayout.finishLoadMore();
        }
        if (finishRefreshing) {
            smartRefreshLayout.finishRefresh();
        }
        if (loadNoMoreData) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }


}
