package com.tiantian.photopicker.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.tiantian.photopicker.bean.PhotoDirectory;
import com.tiantian.photopicker.data.Data;

import java.util.List;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/16
 * Descriptions: MediaStroreHelper
 */
public class MediaStroreHelper {
    public static void getPhotoDirs(final Activity context,final PhotosResultCallback resultCallback){

    }
    public static void getPhtoDirs(final Activity activity,final  PhotosResultCallback resultCallback,final boolean checkImageStatus){
        new Thread(new Runnable() {
            @Override
            public void run() {
                PhotoCursorLoader loader=new PhotoCursorLoader();
                ContentResolver contentResolver=activity.getContentResolver();
                Cursor cursor=contentResolver.query(loader.getUri(),loader.getProjecttion(),loader.getSelection(),loader.getSelectionArgs(),loader.getSortOrder());
                if (cursor==null)return;
                List<PhotoDirectory> directories= Data.getDataFromCursor(activity,cursor,checkImageStatus);
                cursor.close();
                if (resultCallback!=null){
                    resultCallback.onResultCallback(directories);
                }

            }
        }).start();
    }
public static void getPhotoDirs(){}
    public interface PhotosResultCallback{
        void onResultCallback(List<PhotoDirectory> directories);
    }
}
