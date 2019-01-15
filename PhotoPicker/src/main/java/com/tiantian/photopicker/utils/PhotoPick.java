package com.tiantian.photopicker.utils;

import android.content.Context;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: PhotoPick
 */
public class PhotoPick {

    private static PhotoPick photoPick;

    public static PhotoPick getInstance() {
        if (photoPick == null) photoPick = new PhotoPick();
        return photoPick;
    }

    private static int toolBarBackGround;
    private static String authority;
    private static Context mContext;

    public static void init(Context context) {
        init(context, android.R.color.holo_blue_light);
    }

    public static void init(Context context, int toolBarBackGroundId) {
        //这儿说明已经初始化过了，不用再重复初始化
        if (mContext != null) return;
        toolBarBackGround = toolBarBackGroundId;
        mContext = context.getApplicationContext();

    }

    /**
     * 设置标题栏背景
     *
     * @return
     */
    public static int getToolBarBackGround() {
        return mContext.getResources().getColor(toolBarBackGround);
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setToolBarBackGround(int toolBarBackGroundId) {
        toolBarBackGround = toolBarBackGroundId;
    }

    public static String getAuthority() {
        return authority;
    }

    public static String setAuthority(String authorityPath) {
        return authority;
    }

    public static void checkInit(){
        if (mContext==null){
            throw new NullPointerException("photoPicker was not initialized,please init in your Appliction");
        }
    }

}

