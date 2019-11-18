package com.rui.androidmvvmdemo.model;

/**
 * Created by rui on 2019/11/13
 */
public class AddressModel {

    //第一个地址接口返回的其他属性
    private int addressId;
    //以下是其他接口返回的数据
    private LocalModel localModel;
    private InterNationalModel interNationalModel;
    private PayModel payModel;
    private PriceModel priceModel;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public LocalModel getLocalModel() {
        return localModel;
    }

    public void setLocalModel(LocalModel localModel) {
        this.localModel = localModel;
    }

    public InterNationalModel getInterNationalModel() {
        return interNationalModel;
    }

    public void setInterNationalModel(InterNationalModel interNationalModel) {
        this.interNationalModel = interNationalModel;
    }

    public PayModel getPayModel() {
        return payModel;
    }

    public void setPayModel(PayModel payModel) {
        this.payModel = payModel;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(PriceModel priceModel) {
        this.priceModel = priceModel;
    }
}
