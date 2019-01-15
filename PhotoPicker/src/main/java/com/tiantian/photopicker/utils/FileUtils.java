package com.tiantian.photopicker.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/12
 * Descriptions: FileUtils
 */
public class FileUtils {
    private static String TAG = "FileUtils";

    private static File filesDir = null;
    private static File imageFile = null;
    private static String imagePath;

    public static String getImagePath(Context context, String dir) {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String packaName = context.getPackageName();
            path = Environment.getExternalStorageDirectory() + "/" + packaName + dir;
        }
        if (TextUtils.isEmpty(path))
            context.getCacheDir().getPath();
        exisFolder(path);
        return path;
    }

    //判断文件夹是否存在，不存在则创建文件
    public static void exisFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 创建保存文件
     *
     * @param context
     * @param dir
     * @return
     */
    public static File createImageFile(Context context, String dir) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //文件目录
            filesDir = context.getExternalFilesDir(dir).getAbsoluteFile();
        } else {
            filesDir = new File(Environment.getExternalStorageDirectory() + "/" + context.getPackageName() + dir);
        }
        if (!filesDir.exists()) filesDir.mkdirs();
        imageFile = new File(filesDir, createFileName());
        setImagePath(imageFile.getAbsolutePath());
        return imageFile;
    }

    /**
     * 创建图片路径
     *
     * @param path
     * @return
     */
    public static String setImagePath(String path) {
        imagePath = path;
        return imagePath;
    }

    /**
     * 设置创建图片文件
     *
     * @return
     */
    public static String createFileName() {
        return imagePath;
    }

    /**
     * 获取图片路径
     *
     * @return
     */
    public static String getImagePath() {
        return imagePath;
    }

    /**
     * 保存图片并通知图库更新
     *
     * @param filePath
     * @param bmp
     * @param context
     * @return
     */
    public static boolean saveImageToGallery(String filePath, Bitmap bmp, Context context) {
        //首先要保存图片
        if (bmp == null)
            return false;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();//删除原图片
        }
        String dir;
        if (!file.isFile()) {
            dir = filePath.substring(0, filePath.lastIndexOf("/"));
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                if (!dirFile.mkdirs()) {
                    return false;
                }
            }
            FileOutputStream fOut = null;
            boolean isSucces = false;
            try {
                file.createNewFile();
                fOut = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                //通知图库更新
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                context.sendBroadcast(intent);
                isSucces = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fOut != null) {
                    try {
                        fOut.flush();
                        fOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            return isSucces;
        }
        return false;
    }

    /**
     * Uri转Bitmap
     * @param uri
     * @param context
     * @return
     * @throws IOException
     */
    public static Bitmap UriToBitmap(Uri uri,Context context) throws IOException {
        InputStream inputStream=context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options options=new BitmapFactory.Options();
        Bitmap bitmap=BitmapFactory.decodeStream(inputStream,null,options);
        inputStream.close();
        return bitmap;
    }

    /**
     * 按比例压缩图片
     * @param path
     * @param w
     * @param h
     * @return
     */
    public static Bitmap getBitmap(String path,int w,int h){
        Bitmap bit=null;
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        bit=BitmapFactory.decodeFile(path,options);
        int outheight=1;
        if (options.outWidth>options.outHeight){
            outheight=options.outWidth/w;
            options.inSampleSize=outheight;//限制处理后的图片最大为w*2
        }else {
            if (options.outHeight>=h){
                outheight=options.outHeight/h;
                options.inSampleSize=outheight;//限制处理后的图片最大高为h*2
            }
        }
        options.inJustDecodeBounds=false;
        bit=BitmapFactory.decodeFile(path,options);
        return bit;
    }
}

