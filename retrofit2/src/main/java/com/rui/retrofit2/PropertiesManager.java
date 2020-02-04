package com.rui.retrofit2;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Nullable;

import dagger.internal.Preconditions;


/**
 * Class that provides easy access to properties files found in the project.
 */
public class PropertiesManager {

    private static final String PROPERTIES_FILENAME = "project.properties";

    private final Properties properties;
    private boolean isDebug;
    /**
     * 接口返回的授权token
     */
    private String apiAutoToken;

    public PropertiesManager(AssetManager assetManager, boolean isDebug) {
        properties = new Properties();
        this.isDebug = isDebug;
        InputStream inputStream;
        try {
            inputStream = assetManager.open(PROPERTIES_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
//            Timber.e(e, "Failed to close input stream");
        }
    }

    public String getApiAutoToken() {
        return apiAutoToken == null ? "" : apiAutoToken;
    }

    public void setApiAutoToken(String apiAutoToken) {
        this.apiAutoToken = apiAutoToken;
    }

    public boolean isDebug() {
        return isDebug;
    }


    public String getBaseUrl() {
        @Nullable String baseUrl = isDebug ? properties.getProperty(Property.BASE_URL_DEV.getPropertyKey())
                : properties.getProperty(Property.BASE_URL.getPropertyKey());
        Preconditions.checkNotNull(baseUrl);
        return baseUrl;
    }

    private enum Property {

        DRIBBLE_CLIENT_ACCESS_TOKEN("dribbleClientAccessToken"),
        BASE_URL("dribbleBaseUrl"),
        BASE_URL_DEV("dribbleBaseUrlDev");

        private final String propertyKey;

        Property(String authToken) {
            this.propertyKey = authToken;
        }

        public String getPropertyKey() {
            return propertyKey;
        }
    }

}
