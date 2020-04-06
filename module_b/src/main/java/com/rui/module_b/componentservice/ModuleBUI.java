package com.rui.module_b.componentservice;

import android.app.Activity;
import android.content.Intent;

import com.rui.componentservice.iui.IModuleBUI;
import com.rui.module_b.ModuleBActivity;

public class ModuleBUI implements IModuleBUI {


    @Override
    public void openModuleB(Activity context) {
        context.startActivity(new Intent(context, ModuleBActivity.class));
    }

}
