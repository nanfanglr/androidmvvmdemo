package com.rui.componentservice.proxy;


import com.rui.componentservice.common.Module;
import com.rui.componentservice.common.Proxy;
import com.rui.componentservice.iservice.IModuleBService;
import com.rui.componentservice.iui.IModuleBUI;

public class ModuleBProxy extends Proxy<IModuleBUI, IModuleBService> {

    @Override
    public String getModuleClassName() {
        return "com.rui.module_b.componentservice.ModuleBModule";
    }

    @Override
    public Module<IModuleBUI, IModuleBService> getDefaultModule() {
        Module<IModuleBUI, IModuleBService> module
                = new Module<IModuleBUI, IModuleBService>() {
            @Override
            public void init() {

            }
        };
        module.init();
        return module;
    }
}
