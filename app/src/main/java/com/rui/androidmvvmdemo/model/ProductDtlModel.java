package com.rui.androidmvvmdemo.model;

import com.luck.picture.lib.entity.LocalMedia;
import com.rui.mvvm.network.basemodel.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rui on 2018/10/8
 */
public class ProductDtlModel extends BaseModel {

    /**
     * colors : []
     * imgs : [{"img64_URL":"/b8vb1786/b8vb1786_1.jpg","img32_URL":"/b8vb1786/b8vb1786_1.jpg","prod_ID":1,"img_TYPE":"DT","create_TIME":1537000986000,"line_ID":383,"seq_NUM":0,"status":"A","backup":null,"loaded":false},{"img64_URL":"/b8vb1786/b8vb1786_2.jpg","img32_URL":"/b8vb1786/b8vb1786_2.jpg","prod_ID":1,"img_TYPE":"DT","create_TIME":1537000986000,"line_ID":384,"seq_NUM":1,"status":"A","backup":null,"loaded":false},{"img64_URL":"/b8vb1786/b8vb1786_3.jpg","img32_URL":"/b8vb1786/b8vb1786_3.jpg","prod_ID":1,"img_TYPE":"DT","create_TIME":1537000987000,"line_ID":385,"seq_NUM":2,"status":"A","backup":null,"loaded":false},{"img64_URL":"/b8vb1786/b8vb1786_4.jpg","img32_URL":"/b8vb1786/b8vb1786_4.jpg","prod_ID":1,"img_TYPE":"DT","create_TIME":1537000987000,"line_ID":386,"seq_NUM":3,"status":"A","backup":null,"loaded":false},{"img64_URL":"/b8vb1786/b8vb1786_ys_n00.jpg","img32_URL":"/b8vb1786/b8vb1786_ys_n00.jpg","prod_ID":1,"img_TYPE":"CL","create_TIME":1537000988000,"line_ID":387,"seq_NUM":4,"status":"A","backup":null,"loaded":false},{"img64_URL":"/b8vb1786/b8vb1786_ys_n03.jpg","img32_URL":"/b8vb1786/b8vb1786_ys_n03.jpg","prod_ID":1,"img_TYPE":"CL","create_TIME":1537000988000,"line_ID":388,"seq_NUM":5,"status":"A","backup":null,"loaded":false}]
     * prod_ID : 1
     * prod_NAME : 女长裤(铅笔裤)
     * rack_RATE : 189.0
     * prod_NUM : B8VB1786
     */
    private int prod_ID;
    private String prod_NAME;
    private double rack_RATE;
    private String prod_NUM;
    private List<ColorModel> colors;
    private List<ImgModel> imgs;

    /**
     * 上一次主图排到的序号
     */
    private int maxIndex;

    /**
     * 图片的数据(包括网络和本地图片)
     */
    private List<LocalMedia> imgsDT;
//    /**
//     * 头部视图唯一视频,用来标记删除
//     */
//    private LocalMedia videoDT;

//    public LocalMedia getVideoDT() {
//        return videoDT;
//    }
//
//    public void setVideoDT(LocalMedia videoDT) {
//        this.videoDT = videoDT;
//    }

    public int getProd_ID() {
        return prod_ID;
    }

    public void setProd_ID(int prod_ID) {
        this.prod_ID = prod_ID;
    }

    public String getProd_NAME() {
        return prod_NAME == null ? "" : prod_NAME;
    }

    public void setProd_NAME(String prod_NAME) {
        this.prod_NAME = prod_NAME;
    }

    public double getRack_RATE() {
        return rack_RATE;
    }

    public void setRack_RATE(double rack_RATE) {
        this.rack_RATE = rack_RATE;
    }

    public String getProd_NUM() {
        return prod_NUM == null ? "" : prod_NUM.toLowerCase();
    }

    public void setProd_NUM(String prod_NUM) {
        this.prod_NUM = prod_NUM;
    }

    public List<ColorModel> getColors() {
        if (colors == null) {
            return new ArrayList<>();
        }
        return colors;
    }

    public void setColors(List<ColorModel> colors) {
        this.colors = colors;
    }

    public List<ImgModel> getImgs() {
        if (imgs == null) {
            return new ArrayList<>();
        }
        return imgs;
    }

    public void setImgs(List<ImgModel> imgs) {
        this.imgs = imgs;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }



    public List<LocalMedia> getImgsDT() {
        if (imgsDT == null) {
            return new ArrayList<>();
        }
        return imgsDT;
    }

    public void setImgsDT(List<LocalMedia> imgsDT) {
        this.imgsDT = imgsDT;
    }

}
