package com.rui.componentservice.common;

import android.text.TextUtils;
import android.util.Log;


/**
 * 组件之间交互的协议，抽象类，该类实现了IProxy协议接口，
 * 为了不使用硬编码的引用，避免组件之间耦合，我们使用了这个组件中间件协议，该协议不需要进行注册维护，降低了维护成本；
 * 使用该协议，每个需要对外提供服务的组件，都需要维护该组件对应的接口和管理器。
 * <p>
 * 即，每个组件，需要继承该中间件协议的接口，并在中间件组件中提供对应的对外服务接口IService(处理逻辑，放在iservice文件夹)、IUI（处理UI，放在iui文件夹），
 * 管理器Proxy（继承了抽象类Proxy<T, C>），该管理器类，只负责根据具体的组件内部的管理器名字，实例化对象，并对外提供接口的实例对象，
 * 在其组件内部，需要有实现了对外接口的实现类Service（实现了接口IService）、UI（实现了接口IUI），以及管理这两个接口实现类的管理类（该类继承Module<T, C>）
 * 例如，组件Module A，和组件Module B，需要相互调用，只需要去调用中间件组件中对应模块对外提供的接口即可。
 */
public abstract class Proxy<T, C> implements IProxy<T, C> {


    private static final String TAG = "Proxy";

    private Module<T, C> proxy;

    @Override
    public final T getUiInterface() {
        if (getProxy() == null)
            return null;
        return getProxy().getUiInterface();
    }

    @Override
    public final C getServiceInterface() {
        if (getProxy() == null)
            return null;
        return getProxy().getServiceInterface();
    }

    public abstract String getModuleClassName();

    public abstract Module<T, C> getDefaultModule();

    protected Module<T, C> getProxy() {

        if (proxy == null) {
            String module = getModuleClassName();
            if (!TextUtils.isEmpty(module)) {
                try {
                    proxy = (Module<T, C>) ModuleManager.LoadModule(module);
                } catch (Throwable e) {
                    Log.e(TAG, module + " module load failed", e);
                    proxy = getDefaultModule();
                }
            }
        }
        return proxy;
    }
}
