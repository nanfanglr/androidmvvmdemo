package com.rui.androidmvvmdemo.ui.provider;


import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.androidmvvmdemo.ui.adapter.MultipleRvItemAdapter;
import com.rui.common.adapter.BaseMultipleItemProvider;
import com.rui.common.adapter.BaseRvViewHolder;

public class TextImgItemProvider extends BaseMultipleItemProvider<MultipleRvItemModel> {

    @Override
    public int viewType() {
        return MultipleRvItemAdapter.TYPE_TEXT_IMG;
    }

    @Override
    public int layout() {
        return R.layout.item_img_text_view;
    }

    @Override
    public void convert(BaseRvViewHolder helper, MultipleRvItemModel data, int position) {
        super.convert(helper,data,position);

    }


}
