package com.rui.androidmvvmdemo.model;

import com.rui.retrofit2.basemodel.BaseModel;

/**
 * Created by rui on 2018/10/8
 */
public class ImgModel extends BaseModel {
    /**
     * img64_URL : /b8vb1786/b8vb1786_1.jpg
     * img32_URL : /b8vb1786/b8vb1786_1.jpg
     * prod_ID : 1
     * img_TYPE : DT
     * create_TIME : 1537000986000
     * line_ID : 383
     * seq_NUM : 0
     * status : A
     * backup : null
     * loaded : false
     */
    private String img64_URL;
    private String img32_URL;
    private int prod_ID;
    private String img_TYPE;
    private long create_TIME;
    private int line_ID;
    private int seq_NUM;
    private String status;
    private String backup;
    private boolean loaded;

    public String getImg64_URL() {
        return img64_URL == null ? "" : img64_URL;
    }

    public void setImg64_URL(String img64_URL) {
        this.img64_URL = img64_URL;
    }

    public String getImg32_URL() {
        return img32_URL == null ? "" : img32_URL;
    }

    public void setImg32_URL(String img32_URL) {
        this.img32_URL = img32_URL;
    }

    public int getProd_ID() {
        return prod_ID;
    }

    public void setProd_ID(int prod_ID) {
        this.prod_ID = prod_ID;
    }

    public String getImg_TYPE() {
        return img_TYPE == null ? "" : img_TYPE;
    }

    public void setImg_TYPE(String img_TYPE) {
        this.img_TYPE = img_TYPE;
    }

    public long getCreate_TIME() {
        return create_TIME;
    }

    public void setCreate_TIME(long create_TIME) {
        this.create_TIME = create_TIME;
    }

    public int getLine_ID() {
        return line_ID;
    }

    public void setLine_ID(int line_ID) {
        this.line_ID = line_ID;
    }

    public int getSeq_NUM() {
        return seq_NUM;
    }

    public void setSeq_NUM(int seq_NUM) {
        this.seq_NUM = seq_NUM;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackup() {
        return backup == null ? "" : backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    @Override
    public String toString() {
        return "ImgModel{" +
                "img64_URL='" + img64_URL + '\'' +
                ", img32_URL='" + img32_URL + '\'' +
                ", prod_ID=" + prod_ID +
                ", img_TYPE='" + img_TYPE + '\'' +
                ", create_TIME=" + create_TIME +
                ", line_ID=" + line_ID +
                ", seq_NUM=" + seq_NUM +
                ", status='" + status + '\'' +
                ", backup='" + backup + '\'' +
                ", loaded=" + loaded +
                '}';
    }

    public int getSeqIndex() {
        int index = img64_URL.lastIndexOf("_");
        int indexPoint = img64_URL.lastIndexOf(".");
        int seq_Index = 0;
        if (index != -1) {
            String substring = img64_URL.substring(index + 1, indexPoint);
            try {
                seq_Index = Integer.parseInt(substring);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return seq_Index;
    }
}
