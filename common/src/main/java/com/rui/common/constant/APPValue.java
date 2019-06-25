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

}
