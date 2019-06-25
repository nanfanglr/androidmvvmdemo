package com.rui.androidmvvmdemo.model;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.rui.common.oss.ImageHelper;
import com.rui.mvvm.network.basemodel.BaseModel;

/**
 * Created by rui on 2019/6/25
 */
public class ColorModel extends BaseModel {

    /**
     * 颜色图的集合（包括本地及网络图片）
     */
    public ObservableArrayList<LocalMedia> localCLImgs = new ObservableArrayList<>();
    /**
     * 颜色图的url
     */
    public ObservableField<String> clImgUrl = new ObservableField<>();
    /**
     * color_NUM : L07
     * color_NAME : 紫色
     * prod_ID : 23
     * id : 13153
     * backup : null
     * loaded : false
     */
    private String color_NUM;
    private String color_NAME;
    private int prod_ID;
    private int id;
    private String backup;
    private boolean loaded;
    private long creatTime;
    /**
     * viewpager当前展示的下标
     */
    private ObservableInt currentPosition = new ObservableInt(0);
    /**
     * 上一次颜色详情图排到的序号
     */
    private int maxIndex = 0;
    /**
     * 各颜色下详情图图的集合（包括本地及网络图片）
     */
    public ObservableArrayList<LocalMedia> localZSImgs=new ObservableArrayList<>();

    public ObservableInt getCurrentPosition() {
        return currentPosition;
    }


    public String getColor_NAME() {
        return color_NAME == null ? "" : color_NAME;
    }

    public void setColor_NAME(String color_NAME) {
        this.color_NAME = color_NAME;
    }

    public int getProd_ID() {
        return prod_ID;
    }

    public void setProd_ID(int prod_ID) {
        this.prod_ID = prod_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackup() {
        return backup == null ? "" : backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public String getColor_NUM() {

        return color_NUM == null ? "" : color_NUM.toLowerCase();
    }

    public void setColor_NUM(String color_NUM) {
        this.color_NUM = color_NUM;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }


//    public List<LocalMedia> getLocalZSImgs() {
//        if (localZSImgs == null) {
//            localZSImgs = new ArrayList<>();
//        }
//        return localZSImgs;
//    }
//
//    public void setLocalZSImgs(List<LocalMedia> localZSImgs) {
//        this.localZSImgs = localZSImgs;
//    }

    public String getClImgUrlStr() {
        if (localCLImgs != null && localCLImgs.size() > 0) {
            int smallMineType = localCLImgs.get(0).getMimeType();
            if (smallMineType == PictureMimeType.ofImage()) {
                return localCLImgs.get(0).getCompressPath();
            } else {
                return ImageHelper.addImageDomain(localCLImgs.get(0).getCompressPath());
            }
        }
        return "";
    }


    public long getLoadTime() {
        if (localCLImgs != null && localCLImgs.size() > 0) {
            int smallMineType = localCLImgs.get(0).getMimeType();
            if (smallMineType == PictureMimeType.ofImage()) {
                return localCLImgs.get(0).getDuration();
            } else {
                return 0;
            }
        }
        return 0;
    }

}
