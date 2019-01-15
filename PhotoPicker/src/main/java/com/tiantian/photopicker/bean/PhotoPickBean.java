package com.tiantian.photopicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.tiantian.photopicker.loader.ImageLoader;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: PhotoPickBean 照片选择器Bean类
 */
public class PhotoPickBean  implements Parcelable{

    private int maxPickSize; //最多可以选择多少张图片
    private int pickMode; //单选还是多选
    private int spanCount; //Recycleview有多少列
    private boolean showClipCircle;// 圆形裁剪方式
    private boolean showCamera; //是否展示拍照icon
    private boolean clipPhoto; //是否启动裁剪照片
    private boolean originalPictrue; //是否选择的是原图

    protected PhotoPickBean(Parcel in) {
        maxPickSize = in.readInt();
        pickMode = in.readInt();
        spanCount = in.readInt();
        showClipCircle = in.readByte() != 0;
        showCamera = in.readByte() != 0;
        clipPhoto = in.readByte() != 0;
        originalPictrue = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maxPickSize);
        dest.writeInt(pickMode);
        dest.writeInt(spanCount);
        dest.writeByte((byte) (showClipCircle ? 1 : 0));
        dest.writeByte((byte) (showCamera ? 1 : 0));
        dest.writeByte((byte) (clipPhoto ? 1 : 0));
        dest.writeByte((byte) (originalPictrue ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoPickBean> CREATOR = new Creator<PhotoPickBean>() {
        @Override
        public PhotoPickBean createFromParcel(Parcel in) {
            return new PhotoPickBean(in);
        }

        @Override
        public PhotoPickBean[] newArray(int size) {
            return new PhotoPickBean[size];
        }
    };

    public int getMaxPickSize() {
        return maxPickSize;
    }

    public void setMaxPickSize(int maxPickSize) {
        this.maxPickSize = maxPickSize;
    }

    public int getPickMode() {
        return pickMode;
    }

    public void setPickMode(int pickMode) {
        this.pickMode = pickMode;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public boolean getClipMode() {
        return showClipCircle;
    }

    public void setClipMode(boolean showClipCircle) {
        this.showClipCircle = showClipCircle;
    }

    public boolean isShowCamera() {
        return showCamera;
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    public boolean isClipPhoto() {
        return clipPhoto;
    }

    public void setClipPhoto(boolean clipPhoto) {
        this.clipPhoto = clipPhoto;
    }

    public boolean isOriginalPictrue() {
        return originalPictrue;
    }

    public void setOriginalPictrue(boolean originalPictrue) {
        this.originalPictrue = originalPictrue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    private ImageLoader imageLoader;//加载方式


}

