package com.rui.common.oss;

import android.text.TextUtils;

/**
 * Created by linet on 16/1/27.
 */
public class ImageHelper {


    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String FILE = "file://";
    public static String OSS_HOST = "http://kf-img.oss-cn-beijing.aliyuncs.com";

    public static String addImageDomain(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.startsWith(HTTP) || url.startsWith(HTTPS) || url.startsWith(FILE)) {
            return url;
        }
        if (TextUtils.isEmpty(OSS_HOST)) {
            OSS_HOST = "";
        } else if (!OSS_HOST.startsWith(HTTPS) && !OSS_HOST.startsWith(HTTP)) {
            OSS_HOST = String.format("http://%s/", OSS_HOST);
        }
//        YunStoreModel.getYunStoreModel().host = host;

        return String.format("%s/%s", OSS_HOST, url);
//        return host + url;
//        return AppVersionModel.getAppVersionModel().img_server + url;
    }

    public static String addImageDomain2(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.startsWith(HTTP)) {
            return url;
        }

        return "https://b2b-oss.oss-cn-beijing.aliyuncs.com/" + url;
    }

    /**
     * 本地图片加载时，需要给地址添加file://开头标记
     *
     * @param path
     * @return
     */
    public static String addLocalFileFlag(String path) {
        if (TextUtils.isEmpty(path))
            return "";
        if (path.startsWith(FILE) || path.startsWith(HTTP) || path.startsWith(HTTPS))
            return path;
        return FILE + path;
    }

    /**
     * 处理返回视频缩略图路径
     *
     * @param videoUrl
     * @return
     */
    public static String getVideoImg(String videoUrl) {
        return new StringBuilder(videoUrl)
                .replace(
                        videoUrl.lastIndexOf(".")
                        , videoUrl.length()
                        , ".jpg"
                )
                .toString();
    }
}
