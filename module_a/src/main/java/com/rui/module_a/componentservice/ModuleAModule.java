package com.rui.module_a.componentservice;


import com.rui.componentservice.common.Module;

public class ModuleAModule extends Module<ModuleAUI, ModuleAService> {


    public ModuleAModule() {
        init();
    }


    @Override
    public void init() {
        uiInterface = new ModuleAUI();
        serviceInterface = new ModuleAService();
    }
}
