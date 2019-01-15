package com.tiantian.photopicker.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/12
 * Descriptions: ToastUtil
 */
public class ToastUtil {
    public static void showToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}

