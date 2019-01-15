package com.tiantian.photopicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: Photo 全部照片
 */
public class Photo implements Parcelable{
    private int id;
    private String path;

    protected Photo(Parcel in) {
        id = in.readInt();
        path = in.readString();
        size = in.readLong();
    }
    public Photo(int id,String path){
        this.id=id;
        this.path=path;
    }
    public Photo(int id,String path,long size){
        this.id=id;
        this.path=path;
        this.size=size;
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (this==obj)return true;
        Photo photo= (Photo) obj;
        return id==photo.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private long size;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(path);
        dest.writeLong(size);
    }
}

