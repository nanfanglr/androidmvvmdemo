package com.rui.retrofit2.basemodel;

import java.io.Serializable;

/**
 * Created by rui on 2018/3/9.
 */
public class BaseModel implements Serializable {

    public int getInt(Long value) {
        if (value == null) return 0;
        return value.intValue();
    }

    public float getFloat(Double value) {
        if (value == null) return 0;
        return value.floatValue();
    }

    public String getString(String value) {
        if (value == null)
            value = "";
        return value;
    }


}
