package com.tiantian.photopicker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tiantian.photopicker.bean.PhotoDirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/14
 * Descriptions: PhotoGalleryAdapter 相册列表展示
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter {

private Context context;
private int selected;
private List<PhotoDirectory> directories=new ArrayList<>();
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

