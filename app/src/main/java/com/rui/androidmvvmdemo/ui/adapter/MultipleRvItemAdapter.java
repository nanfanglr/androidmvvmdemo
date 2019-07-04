package com.rui.androidmvvmdemo.ui.adapter;

import com.rui.androidmvvmdemo.model.MultipleRvItemModel;
import com.rui.androidmvvmdemo.ui.provider.ImgItemProvider;
import com.rui.androidmvvmdemo.ui.provider.TextImgItemProvider;
import com.rui.androidmvvmdemo.ui.provider.TextItemProvider;
import com.rui.common.adapter.BaseMultipleItemRvAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by rui on 2019/6/25
 */
public class MultipleRvItemAdapter extends BaseMultipleItemRvAdapter<MultipleRvItemModel> {

    public static final int TYPE_TEXT = 100;
    public static final int TYPE_IMG = 200;
    public static final int TYPE_TEXT_IMG = 300;

    @Inject
    public MultipleRvItemAdapter() {
        super(new ArrayList<>());
        finishInitialize();
    }


    @Override
    protected int getViewType(MultipleRvItemModel entity) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        if (entity.type == MultipleRvItemModel.SINGLE_TEXT) {
            return TYPE_TEXT;
        } else if (entity.type == MultipleRvItemModel.SINGLE_IMG) {
            return TYPE_IMG;
        } else if (entity.type == MultipleRvItemModel.TEXT_IMG) {
            return TYPE_TEXT_IMG;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        //Register related entries provider
        mProviderDelegate.registerProvider(new TextItemProvider());
        mProviderDelegate.registerProvider(new ImgItemProvider());
        mProviderDelegate.registerProvider(new TextImgItemProvider());

    }
}
