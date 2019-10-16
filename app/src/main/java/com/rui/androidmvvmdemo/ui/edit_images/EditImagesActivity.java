package com.rui.androidmvvmdemo.ui.edit_images;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityEditImagesBinding;
import com.rui.androidmvvmdemo.ui.product_dtl.ImagePagerAdapter;
import com.rui.common.base.BasePageVMActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.rui.toolkit.DisplayUtils;
import com.rui.viewkit.GridSpacingItemDecoration;
import com.rui.viewkit.PhotoDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EditImagesActivity extends BasePageVMActivity<
        ActivityEditImagesBinding
        , EditImagesViewModel
        , EditImagesAdapter
        , GridLayoutManager
        , RvOnListChangedCallback
        > implements OnItemDragListener {

    @Inject
    ImagePagerAdapter mainImageAdapter;
    @Inject
    GridSpacingItemDecoration decoration;
    @Inject
    VPOnListChangedCallback vpOnListChangedCallback;

    /**
     * +号图片的view
     */
    private View footView;

    public static void actionStart(Activity context, ArrayList<LocalMedia> data
            , int currentPostion, int rvItemPosition, int requestCode) {
        Intent intent = new Intent(context, EditImagesActivity.class);
        intent.putExtra("items", data);
        intent.putExtra("currentPostion", currentPostion);
        intent.putExtra("rvItemPosition", rvItemPosition);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        initVP();
        initRVClick();
    }

    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return null;
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvSmallImage;
    }

    private void initVM() {
        List<LocalMedia> imgData = getIntent().getParcelableArrayListExtra("items");
        viewModel.items.addAll(imgData);
        int currentPostion = getIntent().getIntExtra("currentPostion", 0);
        viewModel.currentPostion.set(currentPostion);
        int rvItemPosition = getIntent().getIntExtra("rvItemPosition", -1);
        viewModel.rvItemPosition.set(rvItemPosition);
    }

    private void initVP() {
        mainImageAdapter.setDisableClick(true);
        binding.setHeadAdapter(mainImageAdapter);
        vpOnListChangedCallback.setAdapter(mainImageAdapter);
        viewModel.items.addOnListChangedCallback(vpOnListChangedCallback);

        mainImageAdapter.setSelectList(viewModel.items);

        ViewGroup.LayoutParams layoutParams = binding.pagerMain.getLayoutParams();
        layoutParams.height = DisplayUtils.getScreenWidthAndHight(this.getApplicationContext())[0];
        binding.pagerMain.setLayoutParams(layoutParams);
        binding.headBarMain.setTvRightOnclickListener(v -> {
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("items", (ArrayList<LocalMedia>) viewModel.items);
            intent.putExtra("currentPostion", viewModel.currentPostion.get());
            intent.putExtra("rvItemPosition", viewModel.rvItemPosition.get());
            setResult(RESULT_OK, intent);
            finish();
        });
        binding.pagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset
                    , int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewModel.currentPostion.set(position);
                adapter.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initRVClick() {
        binding.setDecoration(decoration);
//        rvOnListChangedCallback.setImageMainActivity(this);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            viewModel.currentPostion.set(position);
            this.adapter.setCurrentPosition(position);
        });

        //设置拖动相关
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(binding.rvSmallImage);
        //开启拖拽
        adapter.enableDragItem(itemTouchHelper, R.id.iv_small, true);
        adapter.setOnItemDragListener(this);
        setFootView();
    }

    /**
     * +添加的显示及隐藏
     */
    public void setFootView() {
        if (footView == null) {
            footView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_foot_small, null);
            footView.setOnClickListener(v -> showSelectDialog(APPValue.RESULTCODE_DT_TAKEPHOTO, APPValue.MAX_IMG_NUM - viewModel.items.size()));
        }
        if (viewModel.items.size() < 10) {
            if (adapter.getFooterLayoutCount() == 0) {
                adapter.addFooterView(footView);
                EditImagesAdapter.setImageViewSize(getApplicationContext(), footView);
                //设置脚部不占满一行
                adapter.setFooterViewAsFlow(true);
            }
        } else {
            adapter.removeFooterView(footView);
        }
    }

    private void showSelectDialog(int requestCode, int rest) {
        PhotoDialog.createDialog(this, true, (View v1, int position) -> {
            switch (v1.getId()) {
                case R.id.takePhoto:
                    openCamera(requestCode);
                    break;
                case R.id.choosePhoto:
                    openPicSelector(requestCode, rest);
                    break;
                case R.id.chooseVideo:
                    break;
                case R.id.btn_cancel:
                    break;
            }
        });
    }

    private void openPicSelector(int requestCode, int maxSelectNum) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxSelectNum)
                .isCamera(false)
//                .selectionMedia(lastHeadList)
                .enableCrop(true)
                .compress(true)
                //                .cropCompressQuality(50)// 裁剪压缩质量 默认100 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .withAspectRatio(1, 1)
                .showCropFrame(true)
                .showCropGrid(true)
                .scaleEnabled(true)
                .freeStyleCropEnabled(false)
                .isDragFrame(false)
                .rotateEnabled(false)
                .forResult(requestCode);//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
    }

    private void openCamera(int requestCode) {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())
                .enableCrop(true)
                .compress(true)
                //                .cropCompressQuality(50)// 裁剪压缩质量 默认100 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .withAspectRatio(1, 1)
                .showCropFrame(true)
                .showCropGrid(true)
                .scaleEnabled(true)
                .freeStyleCropEnabled(false)
                .isDragFrame(false)
                .rotateEnabled(false)
                .forResult(requestCode);
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_edit_images;
    }

    @Override
    protected Class<EditImagesViewModel> getVMClass() {
        return EditImagesViewModel.class;
    }

    // 拖动监听
    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
    }

    // 拖动监听
    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from
            , RecyclerView.ViewHolder target, int to) {
    }

    // 拖动监听
    @Override
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        //拖动完成之后通知头部及列表刷新数据
//        mainImageAdapter.notifyDataSetChanged();
        viewModel.currentPostion.set(pos);
        adapter.setCurrentPosition(pos);
        viewModel.isShowSave.set(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            viewModel.isShowSave.set(true);
            List<LocalMedia> localMedias = PictureSelector.obtainMultipleResult(data);
            switch (requestCode) {
                //图片选择回调
                case APPValue.RESULTCODE_DT_TAKEPHOTO:
                    viewModel.items.addAll(localMedias);
                    viewModel.currentPostion.set(viewModel.items.size() - 1);
                    break;
            }
        }
    }
}
