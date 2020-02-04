package com.rui.androidmvvmdemo.model;

import com.rui.retrofit2.basemodel.BaseModel;

/**
 * Created by rui on 2019/6/24
 */
public class ProductModel extends BaseModel {

    /**
     * img_URL : /b8vb1786/b8vb1786_1.jpg
     * position : C
     * position_NAME : null
     * prod_ID : 1
     * prod_NAME : 女长裤(铅笔裤)
     * rack_RATE : 189.0
     * prod_NUM : B8VB1786
     * cat_ID : 62
     * cat_NAME : null
     * status_NAME : null
     * market_LVL : 1
     * brand_NUM : B
     * year_VAL : 2018
     * season_NUM : 4
     * gender_NUM : F
     * market_LVL_NAME : null
     * brand_NUM_NAME : null
     * season_NUM_NAME : null
     * create_TIME : 1536746828000
     * status : A
     * backup : null
     * loaded : true
     */

    /**
     * 商品图片
     */
    private String img_URL;
    private String position;
    private String position_NAME;
    /**
     * 商品id
     */
    private int prod_ID;
    /**
     * 商品名称
     */
    private String prod_NAME;
    /**
     * 价格
     */
    private double rack_RATE;
    /**
     * 商品款号
     */
    private String prod_NUM;
    private int cat_ID;
    private String cat_NAME;
    private String status_NAME;
    private String market_LVL;
    private String brand_NUM;
    private String year_VAL;
    private String season_NUM;
    private String gender_NUM;
    private String market_LVL_NAME;
    private String brand_NUM_NAME;
    private String season_NUM_NAME;
    private long create_TIME;
    private String status;
    private String backup;
    private boolean loaded;

    /**
     * 仅是一个标记,用于更新图片缓存
     */
    private long loadTime = System.currentTimeMillis();

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public String getImg_URL() {
        return img_URL == null ? "" : img_URL;
    }

    public void setImg_URL(String img_URL) {
        this.img_URL = img_URL;
    }

    public String getPosition() {
        return position == null ? "" : position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition_NAME() {
        return position_NAME == null ? "" : position_NAME;
    }

    public void setPosition_NAME(String position_NAME) {
        this.position_NAME = position_NAME;
    }

    public int getProd_ID() {
        return prod_ID;
    }

    public void setProd_ID(int prod_ID) {
        this.prod_ID = prod_ID;
    }

    public String getProd_NAME() {
        return prod_NAME == null ? "" : prod_NAME;
    }

    public void setProd_NAME(String prod_NAME) {
        this.prod_NAME = prod_NAME;
    }

    public double getRack_RATE() {
        return rack_RATE;
    }

    public void setRack_RATE(double rack_RATE) {
        this.rack_RATE = rack_RATE;
    }

    public String getProd_NUM() {
        return prod_NUM == null ? "" : prod_NUM;
    }

    public void setProd_NUM(String prod_NUM) {
        this.prod_NUM = prod_NUM;
    }

    public int getCat_ID() {
        return cat_ID;
    }

    public void setCat_ID(int cat_ID) {
        this.cat_ID = cat_ID;
    }

    public String getCat_NAME() {
        return cat_NAME == null ? "" : cat_NAME;
    }

    public void setCat_NAME(String cat_NAME) {
        this.cat_NAME = cat_NAME;
    }

    public String getStatus_NAME() {
        return status_NAME == null ? "" : status_NAME;
    }

    public void setStatus_NAME(String status_NAME) {
        this.status_NAME = status_NAME;
    }

    public String getMarket_LVL() {
        return market_LVL == null ? "" : market_LVL;
    }

    public void setMarket_LVL(String market_LVL) {
        this.market_LVL = market_LVL;
    }

    public String getBrand_NUM() {
        return brand_NUM == null ? "" : brand_NUM;
    }

    public void setBrand_NUM(String brand_NUM) {
        this.brand_NUM = brand_NUM;
    }

    public String getYear_VAL() {
        return year_VAL == null ? "" : year_VAL;
    }

    public void setYear_VAL(String year_VAL) {
        this.year_VAL = year_VAL;
    }

    public String getSeason_NUM() {
        return season_NUM == null ? "" : season_NUM;
    }

    public void setSeason_NUM(String season_NUM) {
        this.season_NUM = season_NUM;
    }

    public String getGender_NUM() {
        return gender_NUM == null ? "" : gender_NUM;
    }

    public void setGender_NUM(String gender_NUM) {
        this.gender_NUM = gender_NUM;
    }

    public String getMarket_LVL_NAME() {
        return market_LVL_NAME == null ? "" : market_LVL_NAME;
    }

    public void setMarket_LVL_NAME(String market_LVL_NAME) {
        this.market_LVL_NAME = market_LVL_NAME;
    }

    public String getBrand_NUM_NAME() {
        return brand_NUM_NAME == null ? "" : brand_NUM_NAME;
    }

    public void setBrand_NUM_NAME(String brand_NUM_NAME) {
        this.brand_NUM_NAME = brand_NUM_NAME;
    }

    public String getSeason_NUM_NAME() {
        return season_NUM_NAME == null ? "" : season_NUM_NAME;
    }

    public void setSeason_NUM_NAME(String season_NUM_NAME) {
        this.season_NUM_NAME = season_NUM_NAME;
    }

    public long getCreate_TIME() {
        return create_TIME;
    }

    public void setCreate_TIME(long create_TIME) {
        this.create_TIME = create_TIME;
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
}
