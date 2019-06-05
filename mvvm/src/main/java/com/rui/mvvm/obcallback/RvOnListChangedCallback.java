package com.rui.mvvm.obcallback;

/**
 * Created by rui on 2018/12/20
 */

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

/**
 * 这个类作用是告诉recyclerview的adapter数据更新了并去做相应的动作，待完善
 * 这里不用通过构造将adapter传过来，而是通过set方法去设置
 * 因为这里构造传过去的与xml中绑定的adapter对象不是同一个
 *
 * @param <T>
 */
public class RvOnListChangedCallback<T extends ObservableList, ADAPTER extends RecyclerView.Adapter> extends ObservableList.OnListChangedCallback<T> {

    protected ADAPTER adapter;


    public RvOnListChangedCallback() {
    }

    public RvOnListChangedCallback(ADAPTER adapter) {
        this.adapter = adapter;
    }


    public void setAdapter(ADAPTER adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(T sender) {
//            Timber.d("------------>onChanged.sender=" + sender.size());
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(T sender, int positionStart, int itemCount) {
        if (adapter != null) adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(T sender, int positionStart, int itemCount) {
        if (adapter != null) adapter.notifyItemRangeInserted(positionStart, itemCount);
        //需要添加这个通知立即刷新，否则会出现列表闪动
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(T sender, int fromPosition, int toPosition, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            if (adapter != null) adapter.notifyItemMoved(fromPosition + i, toPosition + i);
        }
    }

    @Override
    public void onItemRangeRemoved(T sender, int positionStart, int itemCount) {
        if (adapter != null) adapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

}