package com.rui.mvvm.network.networking;

import com.rui.mvvm.network.networkconfig.PropertiesManager;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * 这类是用来添加请求头的
 */
public class AuthenticationInterceptor implements Interceptor {


    private static final String API_AUTH_TOKEN = "api_auth_token";

    private final PropertiesManager propertiesManager;

    @Inject
    public AuthenticationInterceptor(PropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Timber.d("----------->intercept");
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader(API_AUTH_TOKEN, propertiesManager.getApiAutoToken())
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .build();
        return chain.proceed(request);
    }

}
