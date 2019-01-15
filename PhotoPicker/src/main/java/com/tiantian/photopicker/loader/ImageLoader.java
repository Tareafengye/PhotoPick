package com.tiantian.photopicker.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: ImageLoader 自定义图片加载框架
 */
public interface ImageLoader {

    void displayImage(Context context, String path, ImageView imageView,boolean resize);

    void clearMemoryCache();
}

