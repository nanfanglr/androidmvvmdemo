package com.rui.module_a.componentservice;

import android.app.Activity;
import android.content.Intent;

import com.rui.componentservice.iui.IModuleAUI;
import com.rui.module_a.ModuleAActivity;

public class ModuleAUI implements IModuleAUI {


    @Override
    public void openModuleA(Activity context) {
        context.startActivity(new Intent(context, ModuleAActivity.class));
    }

}
