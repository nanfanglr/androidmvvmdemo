package com.rui.androidmvvmdemo.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.common.imageloader.ImageLoader;
import com.rui.common.oss.ImageHelper;

import java.util.List;

/**
 * Created by rui on 2019/6/25
 */
public class ImagePagerAdapter extends PagerAdapter {
    public int rvItemPos;
    private List<LocalMedia> imgs;
    private ColorModel colorModel;


    public ImagePagerAdapter(int rvItemPos, ColorModel colorModel) {
        this.imgs = colorModel.localZSImgs;
        this.rvItemPos = rvItemPos;
        this.colorModel = colorModel;
    }

    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LocalMedia localMedia = imgs.get(position);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String path = localMedia.getCompressPath();
        int mineType = localMedia.getMimeType();
        if (mineType == PictureMimeType.ofImage()) {
            ImageLoader.displayImage(container.getContext(), path, imageView);
        } else {
            ImageLoader.displayImage(container.getContext(), ImageHelper.addImageDomain(path)
                    , imageView, colorModel.getCreatTime());
        }
        container.addView(imageView);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
