package com.rui.androidmvvmdemo.model;

import com.rui.mvvm.network.basemodel.BaseModel;

/**
 * Created by rui on 2019/2/13
 */
public class UserModel extends BaseModel {

    private String apiAutoToken;
    private String code;
    private String name;
    private String headUrl;
    private long id;

    public String getApiAutoToken() {
        return apiAutoToken == null ? "" : apiAutoToken;
    }

    public void setApiAutoToken(String apiAutoToken) {
        this.apiAutoToken = apiAutoToken;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl == null ? "" : headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
