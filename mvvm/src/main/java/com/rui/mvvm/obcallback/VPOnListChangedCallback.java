package com.rui.mvvm.obcallback;

import android.databinding.ObservableList;
import android.support.v4.view.PagerAdapter;

/**
 * Created by rui on 2018/12/20
 */
public class VPOnListChangedCallback<T extends ObservableList> extends ObservableList.OnListChangedCallback<T> {

    private PagerAdapter adapter;

    public VPOnListChangedCallback() {
    }

    public VPOnListChangedCallback(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(T sender) {
//            Timber.d("------------>onChanged.sender=" + sender.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(T sender, int positionStart, int itemCount) {
//            Timber.d("------------>onChanged.onItemRangeChanged=" + sender.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(T sender, int positionStart, int itemCount) {
//            Timber.d("------------>onChanged.onItemRangeInserted=" + sender.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(T sender, int fromPosition, int toPosition, int itemCount) {
//            Timber.d("------------>onChanged.onItemRangeMoved=" + sender.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(T sender, int positionStart, int itemCount) {
//            Timber.d("------------>onChanged.onItemRangeRemoved=" + sender.size());
        adapter.notifyDataSetChanged();
    }
}