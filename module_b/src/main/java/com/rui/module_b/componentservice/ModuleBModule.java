package com.rui.module_b.componentservice;


import com.rui.componentservice.common.Module;

public class ModuleBModule extends Module<ModuleBUI, ModuleBService> {


    public ModuleBModule() {
        init();
    }


    @Override
    public void init() {
        uiInterface = new ModuleBUI();
        serviceInterface = new ModuleBService();
    }
}
