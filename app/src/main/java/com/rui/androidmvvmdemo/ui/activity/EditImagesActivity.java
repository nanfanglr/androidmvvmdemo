package com.rui.androidmvvmdemo.ui.activity;

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
import com.luck.picture.lib.entity.LocalMedia;
import com.rui.androidmvvmdemo.R;
import com.rui.androidmvvmdemo.databinding.ActivityEditImagesBinding;
import com.rui.androidmvvmdemo.di.viewmodel.EditImagesViewModel;
import com.rui.androidmvvmdemo.ui.adapter.EditImagesAdapter;
import com.rui.androidmvvmdemo.ui.adapter.ImagePagerAdapter;
import com.rui.common.base.BasePageVMActivity;
import com.rui.mvvm.obcallback.RvOnListChangedCallback;
import com.rui.mvvm.obcallback.VPOnListChangedCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.souyute.toolkit.DisplayUtils;
import com.souyute.viewkit.GridSpacingItemDecoration;

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
        binding.setHeadAdapter(mainImageAdapter);
        vpOnListChangedCallback.setAdapter(mainImageAdapter);
        viewModel.items.addOnListChangedCallback(vpOnListChangedCallback);

        mainImageAdapter.imgs.addAll(viewModel.items) ;
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

//            footView.setOnClickListener(v -> showSelectDialog(APPValue.RESULTCODE_DT_TAKEPHOTO, APPValue.MAX_IMG_NUM - viewModel.items.size()));
        }
        if (viewModel.items.size() <10) {
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

    @Override
    protected int getLayoutID(Bundle savedInstanceState) {
        return R.layout.activity_edit_images;
    }

    @Override
    protected Class<EditImagesViewModel> getVMClass() {
        return EditImagesViewModel.class;
    }

    @Override
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

    }

    @Override
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

    }

    @Override
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

    }
}
