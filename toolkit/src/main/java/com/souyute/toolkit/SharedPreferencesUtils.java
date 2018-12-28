package com.souyute.toolkit;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by linet on 16/1/23.
 */
public class SharedPreferencesUtils {

    private static final String SP_NAME = "challenge_sp";
    private static final int SP_MODE = Context.MODE_PRIVATE;
    private static SharedPreferences sp;


    public static void initSharedPreferences(Context context) {
        if (sp == null && context != null) {
            sp = context.getApplicationContext().getSharedPreferences(SP_NAME, SP_MODE);
        }
    }


    /**
     * 保存boolean
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        initSharedPreferences(context);
        saveBoolean(key, value);
    }

    public static void saveBoolean(String key, boolean value) {
        if (sp != null)
            sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 读取boolean
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean loadBoolean(Context context, String key) {
        initSharedPreferences(context);
        return loadBoolean(key);
    }

    public static boolean loadBoolean(String key) {
        if (sp != null)
            return sp.getBoolean(key, true);
        return true;
    }

    /**
     * 保存string
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        initSharedPreferences(context);
        saveString(key, value);
    }

    public static void saveString(String key, String value) {
        if (sp != null)
            sp.edit().putString(key, value).commit();
    }

    /**
     * 读取string
     *
     * @param context
     * @param key
     * @return
     */
    public static String loadString(Context context, String key) {
        initSharedPreferences(context);
        return loadString(key);
    }

    public static String loadString(String key) {
        if (sp != null)
            return sp.getString(key, "");
        return "";
    }

    /**
     * 保存int
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveint(Context context, String key, int value) {
        initSharedPreferences(context);
        saveint(key, value);
    }

    public static void saveint(String key, int value) {
        if (sp != null)
            sp.edit().putInt(key, value).commit();
    }

    /**
     * 读取int
     *
     * @param context
     * @param key
     * @return
     */
    public static int loadint(Context context, String key) {
        initSharedPreferences(context);
        return loadint(key);
    }

    public static int loadint(String key) {
        if (sp != null)
            return sp.getInt(key, 0);
        return 0;
    }
}
