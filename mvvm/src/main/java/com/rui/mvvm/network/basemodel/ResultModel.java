package com.rui.mvvm.network.basemodel;

import java.util.List;

/**
 * Created by rui on 2018/3/9.
 */
public class ResultModel<T> extends BaseResultModel {

    protected T obj;
    
    protected List<T> data;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}
