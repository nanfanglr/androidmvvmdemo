package com.rui.toolkit;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by rui on 2018/11/23
 */
public class VideoUtils {
    /**
     * 获取视频的缩略图并压缩
     *
     * @param filePath
     * @return
     */
    public static String getVideoThumbnail(String filePath, String fileName) {
        Bitmap bitmap = null;
        String thumbnailPath = "";
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
            thumbnailPath = FileUtil.createFilePath("Mycode/") + fileName + ".jpg";
            OutputStream os = new FileOutputStream(thumbnailPath);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 30, os);
            if (!compress) thumbnailPath = "";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return thumbnailPath;
    }

    /**
     * 删除所有缩略图
     *
     * @return
     */
    public static boolean deleteVideoThumbnail() {
        return FileUtil.deleteDir(new File(FileUtil.createFilePath("Mycode/")));
    }

}
