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

    int FUC_SPAN_COUNT = 3;

    int FUC_SPACE = 5;

    int CHARGE_SPAN_COUNT = 2;
    int CHARGE_SPACE = 20;
    /**
     * 标记从main去搜索
     */
    int SEARCH_TYPE_MAIN_CASHER = 6;
    /**
     * 标记从main去搜索
     */
    int SEARCH_TYPE_MAIN_GIFT = 16;
    /**
     * 标记从收银页面去搜索
     */
    int SEARCH_TYPE_CASHIER = 7;
    /**
     * 收银商品扫描
     */
    int MAIN_SCAN_CASHIER = 2001;
    /**
     * 礼品核销扫描
     */
    int MAIN_SCAN_GIFT = 2002;
    /**
     * 会员信息扫描
     */
    int MAIN_SCAN_MEMBER = 2003;
    int CHARGE_SCAN_MEMBER = 2013;
    /**
     * 会员信息扫描(退款页面)
     */
    int MAIN_SCAN_MEMBER_REFUND = 2010;
    /**
     *
     */
    int REUEST_SETTLE_PAY = 2004;
    /**
     * 选择优惠券的请求码
     */
    int REQUEST_COUPON = 2005;
    /**
     * 选择导购的请求码
     */
    int REQUEST_CLERK = 2006;

    /**
     * 从商品列表选择导购的请求码
     */
    int REQUEST_CLERK_PROD = 2010;
    /**
     *
     */
    int REQUEST_SEARCH_PROD = 2007;

    /**
     * 礼品券扫描
     */
    int SCAN_GIFT_COUPON = 2008;

    /**
     * POS退款的回调
     */
    int REQUEST_POS_REFUND = 3001;

    /**
     * 请求设备信息
     */
    int REQUEST_DEVICE_INFO = 2009;

    /**
     * 云卓开发者账号
     */
    String YUNZUO_DEV_ID = "YCFKlnSn";


}
