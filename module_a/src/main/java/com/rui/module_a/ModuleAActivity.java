package com.rui.module_a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rui.componentservice.iui.IModuleBUI;
import com.rui.componentservice.proxy.ModuleBProxy;

public class ModuleAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleBProxy moduleBProxy = new ModuleBProxy();
                IModuleBUI iModuleBUI = moduleBProxy.getUiInterface();
                if (iModuleBUI != null) {
                    iModuleBUI.openModuleB(ModuleAActivity.this);
                }
            }
        });
    }
}
