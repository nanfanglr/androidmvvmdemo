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

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityProductDtlBinding;
import com.rui.androidmvvmdemo.databinding.RvHeadBinding;
import com.rui.androidmvvmdemo.di.viewmodel.ProductDtlViewModel;
import com.rui.androidmvvmdemo.ui.adapter.ImagePagerAdapter;
import com.rui.androidmvvmdemo.ui.adapter.ProductImgAdapter;
import com.rui.common.base.BasePageVMActivity;
import com.rui.common.constant.APPValue;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.souyute.toolkit.DisplayUtils;

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


    @Override
    protected SmartRefreshLayout getRefreshLayout() {
        return null;
    }

    @Override
    protected RecyclerView getRV() {
        return binding.rvData;
    }

    private void initData() {
        viewModel.getData(APPValue.LOAD_FIRST);
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
            case 0:
                break;
            case 1:
                break;
        }
    }

    private void initRVHeader() {
        vpOnListChangedCallback.setAdapter(headImageAdapter);
        viewModel.headImgs.addOnListChangedCallback(vpOnListChangedCallback);

        adapter.addHeaderView(getHeader());

        //列表item的控件点击事件处理
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
//            viewModel.listClickPos.set(position);
            switch (view.getId()) {
                //点击添加item详情图
                case R.id.item_camera_right:
                case R.id.item_camera:
                    List<LocalMedia> localZSImgs = viewModel.items.get(position).localZSImgs;
//                    showSelectDialog(APPValue.RESULTCODE_ITEM_TAKEPHOTO,
//                            APPValue.MAX_IMG_NUM - localZSImgs.size(), false);
                    break;
                //点击添加item颜色图
                case R.id.rl_color:
//                    showSelectDialog(APPValue.RESULTCODE_COLOR_TAKEPHOTO, 1, true);
                    break;
            }
        });
    }

    /**
     * 加载recyclerview头部相关
     */
    private View getHeader() {
        RvHeadBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getApplicationContext())
                , R.layout.rv_head, null, false);
        headImageAdapter.imgs = viewModel.headImgs;
//        headImageAdapter.setListener(this);
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
}
