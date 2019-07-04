package com.rui.androidmvvmdemo.ui.provider;

import android.widget.Toast;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.androidmvvmdemo.ui.adapter.MultipleRvItemAdapter;
import com.rui.common.adapter.BaseMultipleItemProvider;
import com.rui.common.adapter.BaseRvViewHolder;

public class ImgItemProvider extends BaseMultipleItemProvider<MultipleRvItemModel> {


    @Override
    public void convert(BaseRvViewHolder helper, MultipleRvItemModel data, int position) {
        super.convert(helper, data, position);
    }

    @Override
    public int viewType() {
        return MultipleRvItemAdapter.TYPE_IMG;
    }

    @Override
    public int layout() {
        return R.layout.item_image_view;
    }

    @Override
    public void onClick(BaseRvViewHolder helper, MultipleRvItemModel data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseRvViewHolder helper, MultipleRvItemModel data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }


}
