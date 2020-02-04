package com.rui.retrofit2.convert;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.rui.retrofit2.apierror.ApiException;
import com.rui.retrofit2.basemodel.ResultModel;

import java.io.IOException;
import java.net.UnknownServiceException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by rui on 2018/7/31.
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        if (adapter != null && gson != null) {
            try {
                JsonReader jsonReader = gson.newJsonReader(value.charStream());
                T data = adapter.read(jsonReader);
                if (data == null) throw new UnknownServiceException("server back data is null");
                //关注的重点，自定义响应码中非1的情况，一律抛出ApiException异常。
                //这样，我们就成功的将该异常交给onError()去处理了。
                ResultModel resultModel = (ResultModel) data;
                if (data instanceof ResultModel && !resultModel.isSuccess()) {
                    value.close();
                    throw new ApiException(((ResultModel) data).getError_code(), "服务暂不可用，请稍后重试！");
                }
                return data;
            } finally {
                value.close();
            }
        } else {
            return null;
        }
    }
}
