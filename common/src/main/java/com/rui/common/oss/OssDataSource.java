package com.rui.common.oss;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.rui.retrofit2.apierror.ApiException;
import com.rui.retrofit2.basemodel.ResultModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * 阿里云文件上传类，全局单例
 */
public class OssDataSource {
    private static final String BUCKET_NAME_PRODUCT = "kf-img";
    private static final String BUCKET_NAME_DEV = "kf-img";
    private final OssService ossService;
    private final Context context;
    private String BUCKET_NAME = BUCKET_NAME_DEV;
//    private OssModel ossModel;
    /**
     * 阿里的客户端,用来上传文件的，类似一个网络请求客户端
     */
    private OSSClient client;

    public OssDataSource(OssService ossService, Context context) {
        this.ossService = ossService;
        this.context = context;
    }


    public Single<OssModel> getOssOB(String dir) {
        return ossService.getYunStoreInfo(dir)
                .flatMap((Function<ResultModel<OssModel>
                        , SingleSource<OssModel>>) yunStoreModelResultModel -> {
                    if (yunStoreModelResultModel.isSuccess()) {
                        OssModel obj = yunStoreModelResultModel.getObj();
                        initOSS(obj);
                        return Single.just(obj);
                    } else {
                        return Single.error(new ApiException(
                                yunStoreModelResultModel.getError()
                                , yunStoreModelResultModel.getMsg()));
                    }
                });
    }

    /**
     * 初始化OSSClient
     *
     * @param ossModel
     */
    private void initOSS(OssModel ossModel) {
        String endpoint = ossModel.getEndpoint();
        OSSCredentialProvider credentialProvider =
                new OSSStsTokenCredentialProvider(ossModel.getAccessKeyId()
                        , ossModel.getAccessKeySecret(), ossModel.getSecurityToken());
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

        client = new OSSClient(context, endpoint, credentialProvider, conf);
    }

    /**
     * 上传多个图片,这里仅是对 List<String> 这种类型的封装
     * 其他的类型多图上传，参考这个方法进行封装
     *
     * @param filePath
     * @return 返回这个集合拼接的字符串
     */
    private Single<String> uploadImageMutil(@Nullable List<String> filePath, @Nullable String dir) {
        return Observable.fromIterable(filePath)
                .flatMap((Function<String, ObservableSource<String>>) s
                        -> uploadImageSingle(s, dir).toObservable())
                .toList()
                .map(strings -> TextUtils.join(",", strings));
    }

    /**
     * 上传单个图片
     *
     * @param filePath
     * @return
     */
    private Single<String> uploadImageSingle(@Nullable String filePath, String dir) {
        return Single.create((SingleOnSubscribe<String>) emitter -> {
            String imageName = createImageName();
            String url = uploadResource(filePath, dir, createImageName());
            if (TextUtils.isEmpty(url)) {
                emitter.onError(new Throwable(imageName + "上传失败"));
            } else {
                emitter.onSuccess(url);
            }
        }).retry(3);
    }

    /**
     * 同步阻塞式上传资源文件
     *
     * @param filePath 待上传的图片本地地址,不能含有http/https/file开头
     * @return 返回阿里云存储的地址
     */
    public String uploadResource(@Nullable final String filePath, @Nullable String dir, @Nullable String resultName) {
        if (!TextUtils.isEmpty(filePath) && !TextUtils.isEmpty(resultName)) {
            Timber.i("-------------->filePath=" + filePath);
            String objectKey = dir + "/" + resultName;
            PutObjectRequest put = new PutObjectRequest(BUCKET_NAME, objectKey, filePath);

            // 异步上传时可以设置进度回调
//            put.setProgressCallback((request, currentSize, totalSize) -> {
////                    Log.i(TAG, "currentSize: " + currentSize + " totalSize: " + totalSize);
//            });

            try {
                PutObjectResult result = client.putObject(put);
                objectKey = "/" + objectKey;
//                Timber.i("-------------->result=" + result);
//                Timber.i("-------------->result.getStatusCode()=" + result.getStatusCode());
//                Timber.i("-------------->result.getServerCallbackReturnBody()=" + result.getServerCallbackReturnBody());
//                if (result.getStatusCode() == 200) {
//
//                }
                return objectKey;
            } catch (ClientException e) {
                // 本地异常如网络异常等
                e.printStackTrace();
            } catch (ServiceException e) {
                // 服务异常
                e.printStackTrace();
            }
        }
        return "";
    }


    public String createImageName() {
        return createFileName("png");
    }


    public String createFileName(String suffix) {
        return createFileName(null, suffix);
    }


    public String createFileName(String fileName, String suffix) {
        if (TextUtils.isEmpty(fileName)) {
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSSSSSSS").format(new Date());
            fileName = "thumb_" + timeStamp;
            Timber.i("---------------------------->fileName=" + fileName);
        }
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }


}
