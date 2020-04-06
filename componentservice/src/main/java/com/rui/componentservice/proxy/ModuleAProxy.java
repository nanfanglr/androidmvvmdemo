package com.rui.componentservice.proxy;


import com.rui.componentservice.common.Module;
import com.rui.componentservice.common.Proxy;
import com.rui.componentservice.iservice.IModuleAService;
import com.rui.componentservice.iui.IModuleAUI;

public class ModuleAProxy extends Proxy<IModuleAUI, IModuleAService> {


    @Override
    public String getModuleClassName() {
        return "com.rui.module_a.componentservice.ModuleAModule";
    }

    @Override
    public Module<IModuleAUI, IModuleAService> getDefaultModule() {
        Module<IModuleAUI, IModuleAService> module
                = new Module<IModuleAUI, IModuleAService>() {
            @Override
            public void init() {

            }
        };
        module.init();
        return module;
    }
}
