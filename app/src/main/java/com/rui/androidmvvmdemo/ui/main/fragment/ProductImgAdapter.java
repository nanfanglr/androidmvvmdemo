package com.rui.androidmvvmdemo.ui.main.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ItemProductDtlBinding;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.ui.product_dtl.ImagePagerAdapter;
import com.rui.common.adapter.BaseRvAdapter;
import com.rui.common.adapter.BaseRvViewHolder;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.souyute.toolkit.DisplayUtils;

import javax.inject.Inject;

/**
 * Created by rui on 2019/6/25
 */
public class ProductImgAdapter extends BaseRvAdapter<ColorModel> {
    @Inject
    FragmentActivity activity;

    @Inject
    public ProductImgAdapter() {
        super(R.layout.item_product_dtl);
    }

    @Override
    protected void convert(BaseRvViewHolder helper, ColorModel item) {
        helper.addOnClickListener(R.id.item_camera)
                .addOnClickListener(R.id.rl_color)
                .addOnClickListener(R.id.item_camera_right);

        ItemProductDtlBinding binding = (ItemProductDtlBinding) helper.getBinding();
        ImagePagerAdapter itemImageAdapter = new ImagePagerAdapter(activity,helper.getAdapterPosition(), item);
        binding.setAdapter(itemImageAdapter);
        item.localZSImgs.addOnListChangedCallback(new VPOnListChangedCallback<>(itemImageAdapter));
        binding.setItemViewModel(item);
        binding.executePendingBindings();

        int screenWidth = DisplayUtils.getScreenWidthAndHight(mContext.getApplicationContext())[0];
        ViewGroup.LayoutParams layoutParams = binding.itemPager.getLayoutParams();
        layoutParams.height = screenWidth;
        binding.itemPager.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams rlCameraParams = binding.rlCamera.getLayoutParams();
        rlCameraParams.height = screenWidth;
        binding.rlCamera.setLayoutParams(rlCameraParams);

        binding.itemPager.setCurrentItem(item.getCurrentPosition().get());
        binding.itemPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                item.getCurrentPosition().set(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
