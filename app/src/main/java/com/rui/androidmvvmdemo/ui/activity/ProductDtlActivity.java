package com.rui.androidmvvmdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityProductDtlBinding;
import com.rui.androidmvvmdemo.databinding.RvHeadBinding;
import com.rui.androidmvvmdemo.di.viewmodel.ProductDtlViewModel;
import com.rui.androidmvvmdemo.model.ColorModel;
import com.rui.androidmvvmdemo.ui.adapter.ImagePagerAdapter;
import com.rui.androidmvvmdemo.ui.adapter.ProductImgAdapter;
import com.rui.common.base.BasePageVMActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.souyute.toolkit.DisplayUtils;
import com.souyute.toolkit.ToastUtils;
import com.souyute.viewkit.PhotoDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductDtlActivity extends BasePageVMActivity<
        ActivityProductDtlBinding
        , ProductDtlViewModel
        , ProductImgAdapter
        , LinearLayoutManager
        , RvOnListChangedCallback
        > implements
        View.OnClickListener {

    @Inject
    ImagePagerAdapter headImageAdapter;
    @Inject
    VPOnListChangedCallback vpOnListChangedCallback;

    public static void actionStart(Context context, int prodId, String prodNum) {
        Intent intent = new Intent(context, ProductDtlActivity.class);
        intent.putExtra("prodId", prodId);
        intent.putExtra("prodNum", prodNum);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        initRVHeader();
        initData();
    }

    private void initVM() {
        String prodNum = getIntent().getStringExtra("prodNum");
        viewModel.productNum.set(prodNum);
        int prodId = getIntent().getIntExtra("prodId", -1);
        viewModel.prodId = prodId;
    }

    private void initData() {
        viewModel.getData(APPValue.LOAD_FIRST);
    }

    private void initRVHeader() {
        vpOnListChangedCallback.setAdapter(headImageAdapter);
        viewModel.headImgs.addOnListChangedCallback(vpOnListChangedCallback);

        adapter.addHeaderView(getRVHeader());

        //列表item的控件点击事件处理
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                //点击添加item详情图
                case R.id.item_camera_right:
                case R.id.item_camera:
                    List<LocalMedia> localZSImgs = viewModel.items.get(position).localZSImgs;
                    showSelectDialog(APPValue.RESULTCODE_ITEM_TAKEPHOTO,
                            APPValue.MAX_IMG_NUM - localZSImgs.size(), true);
                    break;
                //点击添加item颜色图
                case R.id.rl_color:
                    showSelectDialog(APPValue.RESULTCODE_COLOR_TAKEPHOTO, 1, true);
                    break;
            }
        });
    }

    /**
     * 加载recyclerview头部相关
     */
    private View getRVHeader() {
        RvHeadBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getApplicationContext())
                , R.layout.rv_head, null, false);
        headImageAdapter.setSelectList(viewModel.headImgs);
        binding.setViewModel(viewModel);
        binding.setAdapter(headImageAdapter);

        //头部的相机小图标添加点击事件
        binding.ivCamera.setOnClickListener(this);
        binding.ivCameraRight.setOnClickListener(this);

        ViewGroup.LayoutParams rlImgsParams = binding.rlImg.getLayoutParams();
        rlImgsParams.height = DisplayUtils.getScreenWidthAndHight(this.getApplicationContext())[0];
        binding.rlImg.setLayoutParams(rlImgsParams);

        ViewGroup.LayoutParams layoutParams = binding.pager.getLayoutParams();
        layoutParams.height = DisplayUtils.getScreenWidthAndHight(this.getApplicationContext())[0];
        binding.pager.setLayoutParams(layoutParams);

        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewModel.headCurrentPos.set(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return binding.getRoot();
    }

    private void showSelectDialog(int requestCode, int rest, boolean hideVideo) {
        if (rest <= 0) {
            ToastUtils.showToast(this, "最多只能选择10张图片");
            return;
        }
        PhotoDialog.createDialog(this, hideVideo, (v, position) -> {
            switch (v.getId()) {
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

    /**
     * 打开相机去拍图并裁剪
     *
     * @param requestCode
     */
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

    /**
     * 选择图片并裁剪
     *
     * @param requestCode
     * @param maxSelectNum
     */
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
                .forResult(requestCode);
        //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
    }

    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PictureFileUtils.deleteCacheDirFile(this);
        viewModel.headImgs.removeOnListChangedCallback(vpOnListChangedCallback);
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvData;
    }

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_product_dtl;
    }

    @Override
    protected Class<ProductDtlViewModel> getVMClass() {
        return ProductDtlViewModel.class;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_camera_right:
            case R.id.iv_camera:
                showSelectDialog(APPValue.RESULTCODE_HEAD_TAKEPHOTO, APPValue.MAX_IMG_NUM - viewModel.headImgs.size(), true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            viewModel.isShowCommit.set(true);
            switch (requestCode) {
                //头部主图选择相册及相机拍照回调
                case APPValue.RESULTCODE_HEAD_TAKEPHOTO: {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    viewModel.headImgs.addAll(selectList);
                    viewModel.headCurrentPos.set(viewModel.headImgs.size() - 1);
                    break;
                }
                //头部主图从编辑页面返回，保存返回结果
                case APPValue.HEAD_REQUESTCODE: {
                    ArrayList<LocalMedia> imgData = data.getParcelableArrayListExtra("items");
                    int currentPostion = data.getIntExtra("currentPostion", 0);
                    viewModel.headImgs.clear();//这种全部刷新的方式还可以尝试用DiffUtil进行优化
                    viewModel.headImgs.addAll(imgData);
                    viewModel.headCurrentPos.set(currentPostion);
                    break;
                }
                //列表颜色图选择相册和拍照回调
                case APPValue.RESULTCODE_COLOR_TAKEPHOTO: {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    ColorModel colorModel = viewModel.items.get(viewModel.rvClickPos.get());
                    colorModel.localCLImgs.clear();
                    colorModel.localCLImgs.addAll(selectList);
                    colorModel.clImgUrl.set(colorModel.getClImgUrlStr());
                    break;
                }
                //列表点击item中间相机、右边相机选择相册及拍照回调
                case APPValue.RESULTCODE_ITEM_TAKEPHOTO: {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    ColorModel colorModel = viewModel.items.get(viewModel.rvClickPos.get());
                    colorModel.localZSImgs.addAll(selectList);
                    colorModel.getCurrentPosition().set(colorModel.localZSImgs.size() - 1);
                    break;
                }
                //列表从编辑页面返回，保存item后返回结果
                case APPValue.ITEM_REQUESTCODE: {
                    ArrayList<LocalMedia> imgData = data.getParcelableArrayListExtra("items");
                    int currentPostion = data.getIntExtra("currentPostion", 0);
                    int rvItemPostion = data.getIntExtra("rvItemPosition", 0);
                    ColorModel item = viewModel.items.get(rvItemPostion - 1);
                    item.localZSImgs.clear();
                    item.localZSImgs.addAll(imgData);
                    item.getCurrentPosition().set(currentPostion);
                    break;
                }
            }
        }
    }


}
