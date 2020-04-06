package com.rui.componentservice.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过反射创建模块的module实现类
 */
public class ModuleManager {


    public static Module LoadModule(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Module module1 = null;

        Class<?> clazz = Class.forName(className);
        Constructor<?> ctor = clazz.getConstructor();

        Object object = ctor.newInstance();
//        Object object = ctor.newInstance(null);
        if (object instanceof Module) {
            module1 = (Module) object;
        }
        return module1;
    }
}
