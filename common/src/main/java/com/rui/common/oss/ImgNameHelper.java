package com.rui.common.oss;

/**
 * Created by rui on 2018/10/9
 */
public class ImgNameHelper {

    /**
     * 商品主图命名
     *
     * @param prodNum 款号
     * @param pos     位置
     * @param suffix  格式
     * @return
     */
    public static String createMainImg(String prodNum, int pos, String suffix) {
        String fileName = "";
        if (pos == 0) {
            fileName = prodNum;
        } else {
            fileName = String.format("%s_xj_%d", prodNum, pos);
        }
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }

    /**
     * 商品颜色图命名
     *
     * @param prodNum  款号
     * @param colorNum 色号
     * @param suffix   格式
     * @return
     */
    public static String createColorImg(String prodNum, String colorNum, String suffix) {
        String fileName = String.format("%s_ys_%s", prodNum, colorNum);
        fileName += "." + suffix;
        return fileName;
    }

    /**
     * 商品详情图命名
     *
     * @param prodNum  款号
     * @param colorNum 色号
     * @param pos      格式
     * @param suffix
     * @return
     */
    public static String createDtlImg(String prodNum, String colorNum, int pos, String suffix) {
        String fileName = String.format("%s_zs_%s_%d", prodNum, colorNum, pos);
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }

    /**
     * 颜色详情视频命名
     *
     * @param prodNum  款号
     * @param colorNum 色号
     * @param suffix   格式
     * @return
     */
    public static String createDtlVideo(String prodNum, String colorNum, String suffix) {
        String fileName = String.format("%s_zsvideo_%s", prodNum, colorNum);
        fileName += "." + suffix;
        return fileName;
    }

    /**
     * 主图视频命名
     *
     * @param prodNum 款号
     * @param suffix  格式
     * @return
     */
    public static String createMainVideo(String prodNum, String suffix) {
        String fileName = String.format("%s_dtvideo", prodNum);
        fileName += "." + suffix;
        return fileName;
    }

}
