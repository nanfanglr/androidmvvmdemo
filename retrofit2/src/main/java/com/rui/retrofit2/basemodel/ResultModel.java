package com.rui.retrofit2.basemodel;

import java.util.List;

/**
 * Created by rui on 2018/3/9.
 */
public class ResultModel<T> extends BaseResultModel {

    protected T obj;
    protected List<T> data;
    protected PageData<T> pageData;

    public PageData<T> getPageData() {
        return pageData;
    }

    public void setPageData(PageData<T> pageData) {
        this.pageData = pageData;
    }

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


    public static class PageData<T> {

        private List<T> list;

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }

}
