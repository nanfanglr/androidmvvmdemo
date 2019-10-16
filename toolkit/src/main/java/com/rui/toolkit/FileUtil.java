package com.rui.toolkit;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by linet on 16/1/27.
 */
public class FileUtil {

    public static String APP_NAME = "product_image";
    //    public static final String IMAGE_PATH = "/" + APP_NAME + "/image-path";
//    public static final String VOICE_PATH = "/" + APP_NAME + "/voice-path";
//    public static final String VIDEO_PATH = "/" + APP_NAME + "/video-path";
    public static String IMAGE_FOLDER = "image-path";
    public static String VOICE_FOLDER = "voice-path";
    public static String VIDEO_FOLDER = "video-path";
    public static Application application;

    public static String createImageName() {
        return createFileName("png");
    }

    public static String createVioceName() {
        return createFileName("amr");
    }

    public static String createVioceName(String fileName) {
        return createFileName(fileName, "amr");
    }

    public static String createVideoName() {
        return createFileName("mp4");
    }

    public static String createVideoName(String fileName) {
        return createFileName(fileName, "mp4");
    }

    public static String createFileName(String suffix) {
        return createFileName(null, suffix);
    }

    public static String createFileName(String fileName, String suffix) {
        if (TextUtils.isEmpty(fileName)) {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSSSSSSS").format(new Date());
            fileName = "thumb_" + timeStamp;
//            LogUtils.i("", "---------------------------->fileName=" + fileName);
        }
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }

    public static String createImagePath(Context context) {
        return createFilePath(context, IMAGE_FOLDER) + "/" + createImageName();
    }

    public static String createViocePath(Context context) {
        return createFilePath(context, VOICE_FOLDER) + "/" + createVioceName();
    }


    public static String createViocePath(Context context, String fileName) {
        return createFilePath(context, VOICE_FOLDER) + "/" + createVioceName(fileName);
    }

    public static String createVideoPath(Context context) {
        return createFilePath(context, VIDEO_FOLDER) + "/" + createVideoName();
    }

    public static String createVideoPath(Context context, String fileName) {
        return createFilePath(context, VIDEO_FOLDER) + "/" + createVideoName(fileName);
    }

    public static String createFilePath(Context context, String folder) {
        return getFileDir(context, folder);
    }


    public static String getFileDir(Context context, String folder) {
        String dir = getDir(context);
        String path = dir + "/" + APP_NAME + "/" + folder;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


    public static String getDir(Context context) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return context.getFilesDir().getAbsolutePath();
        }
    }


    public static String compress(Context context, String path) {
        try {
            float w = 500;
            float h = 500;

            String mergePath = createImagePath(context);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            float scale = options.outWidth / w;
            float scale1 = options.outHeight / h;
            if (scale < scale1)
                scale = scale1;

//            LogUtils.i("", String.format("------------>options.outWidth=%d, options.outHeight=%d", options.outWidth, options.outHeight));
//            LogUtils.i("", String.format("------------>scale=%f", scale));
            options.inJustDecodeBounds = false;
            options.inSampleSize = (int) scale;
            Bitmap cs = BitmapFactory.decodeFile(path, options);
            OutputStream os = new FileOutputStream(mergePath);
            if (path.endsWith("jpg") || path.endsWith("JPG") || path.endsWith("JPEG")) {
                cs.compress(Bitmap.CompressFormat.JPEG, 100, os);
            } else {
                cs.compress(Bitmap.CompressFormat.PNG, 100, os);
            }

            return mergePath;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void saveMyBitmap(Bitmap mBitmap, String bitName) {
//        File f = new File(createFilePath("Mycode/") + bitName + ".jpg");
//        FileOutputStream fOut = null;
//        if (f.exists()) {
//            f.delete();
//        }
//        try {
//            fOut = new FileOutputStream(f);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
//        try {
//            fOut.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fOut.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static File saveMyBitmap(Context context, Bitmap mBitmap, String bitName) {
        File f = new File(createFilePath(context, "Mycode/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File saveMyBitmap(Context context, Bitmap mBitmap, String folder, String bitName) {
        File f = new File(createFilePath(context, folder + "/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String saveFile(Context context, byte[] bytes, String fileName) {

        String path = createFilePath(context, "apps/") + fileName;
        File f = new File(path);
        FileOutputStream bos = null;
        if (f.exists()) {
            f.delete();
        }
        try {
            bos = new FileOutputStream(f);
            bos.write(bytes);
            bos.flush();
            bos.close();

            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean saveFileWithPath(byte[] bytes, String path) {

        File f = new File(path);
        FileOutputStream bos = null;
        if (f.exists()) {
            f.delete();
        }
        try {
            bos = new FileOutputStream(f);
            bos.write(bytes);
            bos.flush();
            bos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String saveFileWithPath(InputStream inputStream, String path) {

        //在这里用到了文件输出流
        FileOutputStream fileOutputStream = null;
        try {
            //把请求成功的response转为字节流
            File downloadedFile = new File(path);
            if (downloadedFile.exists()) {
                downloadedFile.delete();
            }


            fileOutputStream = new FileOutputStream(downloadedFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
//            LogUtils.d("wuyinlei", "文件下载成功...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    // outputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return path;
    }

    public static String saveFile(Context context, InputStream inputStream, String fileName) {

        String path = createFilePath(context, "apps/") + fileName;

        return saveFileWithPath(inputStream, path);
    }


    public static String getAssetFilePath(Context context, String fileName) {
        String filePath = createFilePath(context, IMAGE_FOLDER) + "/" + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            return filePath;
        }

        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(fileName);
            out = new FileOutputStream(file);
            copyFile(in, out);
        } catch (IOException e) {
            e.printStackTrace();
            filePath = "";
            file.delete();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }


    public static String createImagePath() {
        return createFilePath(IMAGE_FOLDER) + "/" + createImageName();
    }

    public static String createViocePath() {
        return createFilePath(VOICE_FOLDER) + "/" + createVioceName();
    }


    public static String createViocePath(String fileName) {
        return createFilePath(VOICE_FOLDER) + "/" + createVioceName(fileName);
    }

    public static String createVideoPath() {
        return createFilePath(VIDEO_FOLDER) + "/" + createVideoName();
    }

    public static String createVideoPath(String fileName) {
        return createFilePath(VIDEO_FOLDER) + "/" + createVideoName(fileName);
    }

    public static String createFilePath(String folder) {
        return getFileDir(folder);
    }


    public static String getFileDir(String folder) {
        return getFileDir(application, folder);
    }

    public static String getDir() {
        return getDir(application);
    }


    public static String compress(String path) {
        try {
            float w = 500;
            float h = 500;

            String mergePath = createImagePath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            float scale = options.outWidth / w;
            float scale1 = options.outHeight / h;
            if (scale < scale1)
                scale = scale1;

//            LogUtils.i("", String.format("------------>options.outWidth=%d, options.outHeight=%d", options.outWidth, options.outHeight));
//            LogUtils.i("", String.format("------------>scale=%f", scale));
            options.inJustDecodeBounds = false;
            options.inSampleSize = (int) scale;
            Bitmap cs = BitmapFactory.decodeFile(path, options);
            OutputStream os = new FileOutputStream(mergePath);
            if (path.endsWith("jpg") || path.endsWith("JPG") || path.endsWith("JPEG")) {
                cs.compress(Bitmap.CompressFormat.JPEG, 100, os);
            } else {
                cs.compress(Bitmap.CompressFormat.PNG, 100, os);
            }

            return mergePath;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static File saveMyBitmap(Bitmap mBitmap, String bitName) {
        File f = new File(createFilePath("Mycode/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File saveMyBitmap(Bitmap mBitmap, String folder, String bitName) {
        File f = new File(createFilePath(folder + "/") + bitName + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            return f;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String saveFile(byte[] bytes, String fileName) {

        String path = createFilePath("apps/") + fileName;
        File f = new File(path);
        FileOutputStream bos = null;
        if (f.exists()) {
            f.delete();
        }
        try {
            bos = new FileOutputStream(f);
            bos.write(bytes);
            bos.flush();
            bos.close();

            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String saveFile(InputStream inputStream, String fileName) {

        String path = createFilePath("apps/") + fileName;

        return saveFileWithPath(inputStream, path);
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


}
