package com.rui.componentservice.common;

/**
 * 协议接口，其会提供组件对外的UI相关接口和服务相关接口
 */
public interface IProxy<T, C> {

    /**
     * 模块中的 UI 相关接口
     *
     * @return
     */
    T getUiInterface();

    /**
     * 模块的服务接口
     *
     * @return
     */
    C getServiceInterface();
}
