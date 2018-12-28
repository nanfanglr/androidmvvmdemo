package com.rui.common.imageloader;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

public class StringSignature implements Key {
    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
