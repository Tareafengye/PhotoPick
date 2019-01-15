package com.tiantian.photopicker.controller;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: PhotoConfig 自定义配置
 */
public class PhotoConfig {
    public static int DEFAULT_CHOOSE_SIZE = 1; //默认可以选择的图片数目

    public static int MODE_PICK_SINGLE = 1; //单选模式

    public static int MODE_PICK_MORE = 2;  //多选模式

    public static int GRID_SPAN_COUNT = 3; //GridView的列列数

    public static boolean CLIP_CIRCLE=false; //裁剪方式 ，圆形

    public static boolean DEFAULT_SHOW_CAMERA=true; //默认展示相机 icon

    public static boolean DEFAULT_SHOW_CLIP=false; //默认开启剪裁图片功能

    public static int REQUEST_CODE_SDCARD=100;  //高版本权限申请读写请求码

    public static int REQUEST_CODE_CAMERA=200; //拍照权限申请

    public static String EXTRA_PICK_BUNDLE="extra_pick_bundle";

    public static String EXTRA_PICK_BEAN="extra_pick_bean";


}
