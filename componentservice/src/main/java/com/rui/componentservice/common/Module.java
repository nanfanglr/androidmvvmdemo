package com.rui.componentservice.common;

/**
 * 接口管理抽象类类，
 * 用于获取组件对外提供的接口，
 * 组件内部需要创建一个管理对外提供服务的类的管理类，该类继承抽象类Module<T, C>；
 * 组件内的这个管理实现类，不提供具体的服务，只需负责中转组件对外提供的UI接口服务和Service接口服务即可。
 */
public abstract class Module<T, C> {

    protected T uiInterface;
    protected C serviceInterface;


    public T getUiInterface() {
        return uiInterface;
    }

    public C getServiceInterface() {
        return serviceInterface;
    }

    public abstract void init();
}
