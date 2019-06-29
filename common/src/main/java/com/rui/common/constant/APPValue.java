package com.rui.common.constant;//package com.mvp.rui.androidmvpdemo.common.constants;

/**
 * 这里主要定义APP用的常量
 * Created by rui on 2018/3/9.
 */
public interface APPValue {

    String HTTP = "http://";
    String HTTPS = "https://";
    String FILE = "file://";

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

//    int PAGE_LIMIT =10;

    String SP_PHONE = "sp_phone";

    /**
     * 在列表适配器中，分辨本地视频还是网络视频时使用
     */
    int NET_IMAGE = 31;

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

}
