package com.tiantian.photopicker.controller;

import android.app.Activity;

import com.tiantian.photopicker.bean.PhotoPickBean;
import com.tiantian.photopicker.loader.ImageLoader;

import android.os.Build;
import android.os.Bundle;

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

    public static boolean CLIP_CIRCLE = false; //裁剪方式 ，圆形

    public static boolean DEFAULT_SHOW_CAMERA = true; //默认展示相机 icon

    public static boolean DEFAULT_SHOW_CLIP = false; //默认开启剪裁图片功能

    public static ImageLoader imageLoader;//图片加载方式

    public static PhotoPickBean photoPickBean;

    public static int REQUEST_CODE_SDCARD = 100;  //高版本权限申请读写请求码

    public static int REQUEST_CODE_CAMERA = 200; //拍照权限申请

    public static String EXTRA_STRING_ARRAYLIST = "extra_string_array_list";

    public static String EXTRA_SINGLE_PHOTO = "extra_single_photo";

    public static String EXTRA_CLIP_PHOTO = "extra_clip_photo";

    public static String EXTRA_PICK_BUNDLE = "extra_pick_bundle";

    public static String EXTRA_PICK_BEAN = "extra_pick_bean";

    public static final int PICK_SINGLE_REQUEST_CODE = 10001;

    public static final int PICK_MORE_REQUEST_CODE = 10002;

    public static final int PICK_CLIP_REQUEST_CODE = 10003;

    public PhotoConfig(Activity activity, Builder builder) {
    }

    public static class Builder {
        private Activity activity;
        private PhotoPickBean pickBean;
        private ImageLoader imageLoader;

        public Builder(Activity activity) {
            if (activity == null) {
                throw new NullPointerException(" context is null");
            }
            this.activity = activity;

            photoPickBean = pickBean = new PhotoPickBean();
            pickBean.setSpanCount(GRID_SPAN_COUNT);//默认gradview列数-》3
            pickBean.setMaxPickSize(DEFAULT_CHOOSE_SIZE);//默认可以选择的图片数目->1
            pickBean.setPickMode(MODE_PICK_SINGLE);//默认图片单选
            pickBean.setShowCamera(DEFAULT_SHOW_CAMERA);//默认展示拍照icon
            pickBean.setClipPhoto(DEFAULT_SHOW_CLIP);//默认关闭图片剪裁
            pickBean.setClipMode(CLIP_CIRCLE);//默认关闭剪裁方式的矩形
        }

        /**
         * 设置图片加载方式
         *
         * @param imageLoader
         * @return
         */
        public Builder imageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
            pickBean.setImageLoader(imageLoader);
            return this;
        }

        /**
         * 手动设置GridView列数
         * 默认为9
         *
         * @param spanCount
         * @return
         */
        public Builder spanCount(int spanCount) {
            pickBean.setSpanCount(spanCount);
            //当手动设置列数为0时，默认列数为3
            if (pickBean.getSpanCount() == 0) pickBean.setSpanCount(GRID_SPAN_COUNT);
            return this;
        }

        /**
         * 手动设置照片多选还是单选
         * 默认为单选
         *
         * @param pickMode
         * @return
         */
        public Builder pickMode(int pickMode) {
            pickBean.setPickMode(pickMode);
            if (pickMode == MODE_PICK_SINGLE) pickBean.setMaxPickSize(1);
            else if (pickMode == MODE_PICK_MORE) {
                pickBean.setShowCamera(false);
                pickBean.setClipPhoto(false);
                pickBean.setMaxPickSize(9);
            } else throw new IllegalArgumentException("unKnow_pickMode :" + pickMode);
            return this;
        }

        public Builder maxPickSize(int maxPickSize) {
            pickBean.setMaxPickSize(maxPickSize);
            if (maxPickSize == 0 || maxPickSize == 1) {
                pickBean.setMaxPickSize(1);
                pickBean.setPickMode(MODE_PICK_SINGLE);
            } else if (pickBean.getPickMode() == MODE_PICK_SINGLE) {
                pickBean.setMaxPickSize(1);
            } else pickBean.setPickMode(MODE_PICK_MORE);
            return this;
        }

        /**
         * 是否显示拍照icon
         * 默认显示
         *
         * @param showCamera
         * @return
         */
        public Builder showCamera(boolean showCamera) {
            pickBean.setShowCamera(showCamera);
            return this;
        }

        /**
         * 是否开启照片剪裁功能
         * 默认关闭
         *
         * @param clipPhoto
         * @return
         */
        public Builder clipPhoto(boolean clipPhoto) {
            pickBean.setClipPhoto(clipPhoto);
            return this;
        }

        /**
         * 设置剪裁方式(圆形，矩形)
         * 默认矩形
         * @param showClipCircle
         * @return
         */
        public Builder clipCircle(boolean showClipCircle) {
            pickBean.setClipMode(showClipCircle);
            return this;
        }
        public Builder setPhotoPickBean(PhotoPickBean bean){
            this.pickBean=bean;
            return this;
        }
        public PhotoConfig build(){
            if (pickBean.isClipPhoto()){
                pickBean.setMaxPickSize(1);
                pickBean.setPickMode(MODE_PICK_SINGLE);
            }
            return new PhotoConfig(activity,this);
        }
    }


}
