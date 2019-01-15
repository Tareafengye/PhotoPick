package com.tiantian.photopicker.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/12
 * Descriptions: UCropUtils
 */
public class UCropUtils {

    public static void start(Activity mActivity, File sourceFile,File destinationFile,boolean showClipCircle){
        UCrop uCrop=UCrop.of(Uri.fromFile(sourceFile),Uri.fromFile(destinationFile));
        UCrop.Options options=new UCrop.Options();
        options.useSourceImageAspectRatio();//设置图片原始宽高比
        //设置剪裁图片可操作收拾
        options.setAllowedGestures(UCropActivity.SCALE,UCropActivity.ROTATE,UCropActivity.ALL);
        options.setCompressionQuality(100);//设置剪裁图片的质量
        options.setCompressionFormat(Bitmap.CompressFormat.PNG);//设置剪裁出来的图片格式
        options.setFreeStyleCropEnabled(true);//调整剪裁框
        if (showClipCircle==true){
            options.setCircleDimmedLayer(true);//设置剪裁框圆形
            options.setShowCropFrame(false);//设置是否展示矩形剪裁框
            options.setShowCropGrid(false);//设置是否显示剪裁框网格
        }else {
            options.setShowCropGrid(true);
            options.setShowCropFrame(true);
        }

        options.setToolbarWidgetColor(PhotoPick.getToolBarBackGround());
        options.setStatusBarColor(PhotoPick.getToolBarBackGround());
        uCrop.withOptions(options);
        uCrop.start(mActivity);
    }
}

