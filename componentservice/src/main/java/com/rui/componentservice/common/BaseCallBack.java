package com.rui.componentservice.common;

/**
 * 每一个Activity，如果需要回传数据给它的调用者，需要继承该类，创建一个对应的回调类型，
 * 然后用该回调类型实例化一个静态的对象，作为调用者和被调用者之间传递数据的桥梁。
 * 即，如果Activity A，打开了Activity B，在B中操作后，需要B的数据回传给A，
 * 需要通过一个类Callback A（该类继承了BaseCallBack）的静态实例对象作为两者交换数据的桥梁，
 * 并且在交换完数据后，需要把这个静态对象清理，即调用BaseCallBack的方法clear()
 */
public abstract class BaseCallBack {

    protected static BaseCallBack mCallBack;

    /**
     * 在用完这个回调处理后，记得调用这个方法清空
     */
    public static void clear() {
        mCallBack = null;
    }

    /**
     * 初始化时，需要调用setCallBack方法，目的是保存为静态变量
     */
    public void init() {
        mCallBack = this;
    }
}
