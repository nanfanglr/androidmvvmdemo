package com.rui.androidmvvmdemo.model;

import com.rui.mvvm.network.basemodel.BaseModel;

public class MultipleRvItemModel extends BaseModel {
    public static final int SINGLE_TEXT = 1;
    public static final int SINGLE_IMG = 2;
    public static final int TEXT_IMG = 3;

    public int type;
    public String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipleRvItemModel() {
    }

    public MultipleRvItemModel(int type) {
        this.type = type;
    }

    public MultipleRvItemModel(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
