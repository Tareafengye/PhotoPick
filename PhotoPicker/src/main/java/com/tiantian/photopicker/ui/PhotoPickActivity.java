package com.tiantian.photopicker.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liutian.bitmapprocess.R;
import com.tiantian.photopicker.adapter.PhotoPickAdapter;
import com.tiantian.photopicker.base.BaseActivity;
import com.tiantian.photopicker.bean.PhotoPickBean;
import com.tiantian.photopicker.controller.PhotoConfig;
import com.tiantian.photopicker.utils.PhotoPick;

/**
 * 照片选择器
 * Create by ruan
 * */

public class PhotoPickActivity extends BaseActivity {
public static final int REQUUST_CODE_SDCARD=100;
    private PhotoPickBean pickBean;
    private PhotoPickAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_pick,true);
        Bundle bundle=getIntent().getBundleExtra(PhotoConfig.EXTRA_PICK_BUNDLE);

        if(bundle==null){
            throw new NullPointerException("bundle is null,please init it");
        }
        pickBean=bundle.getParcelable(PhotoConfig.EXTRA_PICK_BEAN);
        if (pickBean==null){
            finish();
            return;
        }
        //申请权限
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermission();
        }else {
            init();
        }

    }

    private void init(){

        toolbar.setTitle(R.string.select_photo);
        toolbar.setBackgroundColor(PhotoPick.getToolBarBackGround());
        //全部照片列表
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,pickBean.getSpanCount()));
        adapter=new PhotoPickAdapter(this,pickBean);
        recyclerView.setAdapter(adapter);
        //相册列表
        RecyclerView gallry_rv=findViewById(R.id.gallery_rcl);
        gallry_rv.setLayoutManager(new LinearLayoutManager(this));


    }
    /**
     * 申请权限
     * */
    private void requestPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},PhotoConfig.REQUEST_CODE_SDCARD);
        }else {
            //已经申请过的
            init();
        }
    }
}
