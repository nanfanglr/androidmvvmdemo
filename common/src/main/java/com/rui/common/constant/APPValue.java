package com.rui.common.constant;//package com.mvp.rui.androidmvpdemo.common.constants;

/**
 * 这里主要定义APP用的常量
 * Created by rui on 2018/3/9.
 */
public interface APPValue {
    /**
     * 第一次加载数据
     */
    int LOAD_FIRST = 0;
    /**
     * 下拉刷新数据
     */
    int LOAD_REFRESH = 1;
    /**
     * 上拉加载更多
     */
    int LOAD_MORE = 2;

//    int PAGE_LIMIT = 20;

    /**
     * DT-细节，对应主图多张,上传、数据解析时使用
     */
    String IMG_MAIN = "DT";
    /**
     * CL-颜色，对应单张颜色图,上传、数据解析时使用
     */
    String IMG_COLOR = "CL";
    /**
     * ZS-展示图，对应颜色下的多张细节图,上传、数据解析时使用
     */
    String IMG_COLOR_DTL = "ZS";
    /**
     * 对应主图视频,上传、数据解析时使用
     * 视频缩略图默认路径为视频路径后缀将.mp4替换成.jpg
     */
    String VIDEO_MAIN = "DTSP";
    /**
     * 对应各颜色下详情视频,上传、数据解析时使用
     * 视频缩略图默认路径为视频路径后缀将.mp4替换成.jpg
     */
    String VIDEO_DTL = "ZSSP";


    String HTTP = "http://";
    String HTTPS = "https://";
    String FILE = "file://";

    /**
     * 是否为本地图片，1为本地图片
     */
    int PICTITURE_TYPE = 1;

    int HEAD_REQUESTCODE = 400;

    int ITEM_REQUESTCODE = 403;

    int RESULTCODE_HEAD_TAKEPHOTO = 105;

    int RESULTCODE_ITEM_TAKEPHOTO = 107;

    int RESULTCODE_COLOR_TAKEPHOTO = 111;

    int RESULTCODE_DT_TAKEPHOTO = 112;
    /**
     * 可选的最大图片数量
     */
    int MAX_IMG_NUM = 10;
    /**
     * 在列表适配器中，分辨本地视频还是网络视频时使用
     */
    int NET_IMAGE = 31;
    /**
     * 在列表适配器中，分辨本地视频还是网络视频时使用
     */
    int NET_VIDEO = 32;
}
