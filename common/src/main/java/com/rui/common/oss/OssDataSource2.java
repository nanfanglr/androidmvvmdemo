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
import com.rui.mvvm.network.ApiErro.ApiException;
import com.rui.mvvm.network.ApiErro.ExceptionConsumer;
import com.rui.mvvm.network.basemodel.ResultModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 代码示例，在本项目中暂无作用
 * 阿里云文件上传类，全局单例
 */
public class OssDataSource2 {
    private static final String BUCKET_NAME_PRODUCT = "prod-img";
    private static final String BUCKET_NAME_DEV = "prod-img-test";
    private final OssService ossService;
    private final Context context;
    private String BUCKET_NAME = BUCKET_NAME_DEV;
    private OssModel ossModel;
    /**
     * 阿里的客户端,用来上传文件的，类似一个网络请求客户端
     */
    private OSSClient client;

    public OssDataSource2(OssService ossService, Context context) {
        this.ossService = ossService;
        this.context = context;
    }

    /**
     * 关于securityToken过期的考虑方面：
     * 1、要和后台确认他们设置的有效时长是多少，在此基础上可以考虑添加自动更新机制；
     * 2、可以考虑在判断时间已经过期后，重新调用后台相关接口，拿回新的securityToken，重新构造新的OSSClient
     * 3、更新CredentialProvider，同时主动更新yunStoreModel中的过期时间expiration，当前时间加上有效时长
     *
     * @param isDebug
     */
    public void initClient(boolean isDebug) {
        BUCKET_NAME = isDebug ? BUCKET_NAME_PRODUCT : BUCKET_NAME_DEV;
        Timber.i("------------->initClient");
        Disposable subscribe = Single.create((SingleOnSubscribe<Boolean>) emitter -> emitter
                .onSuccess(ossModel == null))
                .flatMap((Function<Boolean, SingleSource<OssModel>>) aBoolean -> {
                    if (aBoolean) {
                        return ossService.getYunStoreInfo("app/")
                                .flatMap((Function<ResultModel<OssModel>
                                        , SingleSource<OssModel>>) yunStoreModelResultModel -> {
                                    if (yunStoreModelResultModel.isSuccess()) {
                                        this.ossModel = yunStoreModelResultModel.getObj();
                                        return Single.just(ossModel);
                                    } else {
                                        return Single.error(new ApiException(
                                                yunStoreModelResultModel.getError()
                                                , yunStoreModelResultModel.getMsg()));
                                    }
                                });
                    } else {
                        return Single.just(ossModel);
                    }
                })
                //如果发生错误则重试3次
                .retry(3)
                .flatMap((Function<OssModel, SingleSource<OssModel>>) ossModel -> {
                    if (client == null) {
                        initOSS(ossModel);
                    } else {
                        updateOss(ossModel);
                    }
                    return Single.just(ossModel);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ossModel -> {
                    ImageHelper.OSS_HOST = ossModel.getHost();
                    Timber.i("------>OSS_HOST=" + ImageHelper.OSS_HOST);
                }, new ExceptionConsumer(context));
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
     * 更新OSSClient
     *
     * @param ossModel
     */
    private void updateOss(OssModel ossModel) {
        // 在您判断到Token即将过期时，您可以重新构造新的OSSClient，也可以通过如下方式更新CredentialProvider:
        //以下的方式需要去主动更新yunStoreModel中的过期时间expiration，当前时间加上有效时长
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(ossModel.getAccessKeyId()
                , ossModel.getAccessKeySecret(), ossModel.getSecurityToken());
        client.updateCredentialProvider(credentialProvider);
    }

    /**
     * 上传多个图片,这里仅是对 List<String> 这种类型的封装
     * 其他的类型多图上传，参考这个方法进行封装
     *
     * @param filePath
     * @return 返回这个集合拼接的字符串
     */
    private Single<String> uploadImageMutil(@Nullable List<String> filePath) {
        return Observable.fromIterable(filePath)
                .flatMap((Function<String, ObservableSource<String>>) s
                        -> uploadImageSingle(s).toObservable())
                .toList()
                .map(strings -> TextUtils.join(",", strings));
    }

    /**
     * 上传单个图片
     *
     * @param filePath
     * @return
     */
    private Single<String> uploadImageSingle(@Nullable String filePath) {
        return Single.create((SingleOnSubscribe<String>) emitter -> {
            String imageName = createImageName();
            String url = uploadResource(filePath, createImageName());
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
    private String uploadResource(@Nullable final String filePath, @Nullable String resultName) {
        if (!TextUtils.isEmpty(filePath) && !TextUtils.isEmpty(resultName)) {
            Timber.i("-------------->filePath=" + filePath);
            String objectKey = BUCKET_NAME + "/" + resultName;
            PutObjectRequest put = new PutObjectRequest(BUCKET_NAME, objectKey, filePath);
            // 异步上传时可以设置进度回调
            put.setProgressCallback((request, currentSize, totalSize) -> {
//                    Log.i(TAG, "currentSize: " + currentSize + " totalSize: " + totalSize);
            });

            try {
                PutObjectResult result = client.putObject(put);
                Timber.i("-------------->result=" + result);
                Timber.i("-------------->result.getStatusCode()=" + result.getStatusCode());
                Timber.i("-------------->result.getServerCallbackReturnBody()=" + result.getServerCallbackReturnBody());
                if (result.getStatusCode() == 200) {

                }
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

    /**
     * 商品主图命名
     *
     * @param prodNum 款号
     * @param pos     位置
     * @param suffix  格式
     * @return
     */
    public String createMainImg(String prodNum, int pos, String suffix) {
        String fileName = "";
        if (pos == 0) {
            fileName = prodNum;
        } else {
            fileName = String.format("%s_XJ_%d", prodNum, pos);
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
    public String createColorImg(String prodNum, String colorNum, String suffix) {
        String fileName = String.format("%s_YS_%s", prodNum, colorNum);
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
    public String createDtlImg(String prodNum, String colorNum, int pos, String suffix) {
        String fileName = String.format("%s_ZS_%s_%d", prodNum, colorNum, pos + 1);
        fileName += "." + suffix;// 照片命名;
        return fileName;
    }


}
