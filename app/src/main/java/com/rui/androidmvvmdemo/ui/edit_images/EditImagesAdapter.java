package com.rui.androidmvvmdemo.ui.edit_images;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.R;
import com.rui.common.constant.APPValue;
import com.rui.common.imageloader.ImageLoader;
import com.rui.common.oss.ImageHelper;
import com.rui.toolkit.DisplayUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * 这个适配器由于使用第三方LocalMedia数据类（不可以修改类属性），因此就不用databinding对他进行绑定
 * 如果需要使用databinding，需要继承LocalMedia这个类去使用绑定
 */
public class EditImagesAdapter extends BaseItemDraggableAdapter<LocalMedia, BaseViewHolder> {

    private int currentPosition;

    @Inject
    public EditImagesAdapter() {
        super(R.layout.item_samll_image, new ArrayList<>());
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia item) {
        helper.addOnClickListener(R.id.iv_small);
        int mineType = item.getMimeType();

        ImageView iv = helper.getView(R.id.iv_small);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        setImageViewSize(mContext, iv);

        if (mineType == PictureMimeType.ofImage()) {
            //本地图片的展示
            ImageLoader.displayImage(mContext, item.getCompressPath(), iv);
        } else if (mineType == APPValue.NET_IMAGE) {
            //网络图片的展示
            ImageLoader.displayImage(mContext, ImageHelper.addImageDomain(item.getPath()), iv, item.getDuration());
        }

        //默认当前展示的图片为选中状态
        iv.setSelected(helper.getAdapterPosition() == currentPosition);

    }

    @Nullable
    @Override
    public View getViewByPosition(int position, int viewId) {
        return super.getViewByPosition(position, viewId);
    }

    /**
     * 设置图片的长宽
     */
    public static void setImageViewSize(Context context, View imageView) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        int image_width = (DisplayUtils.getScreenWidthAndHight(context)[0]
                - DisplayUtils.dip2px(context, 10) * 3 - DisplayUtils.dip2px(context, 16) * 2) / 4;
        params.height = image_width;
        params.width = image_width;
        imageView.setLayoutParams(params);
    }

}

