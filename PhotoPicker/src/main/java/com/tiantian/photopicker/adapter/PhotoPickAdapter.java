package com.tiantian.photopicker.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.liutian.bitmapprocess.R;
import com.tiantian.photopicker.bean.Photo;
import com.tiantian.photopicker.bean.PhotoPickBean;
import com.tiantian.photopicker.controller.PhotoConfig;
import com.tiantian.photopicker.loader.ImageLoader;
import com.tiantian.photopicker.ui.PhotoPickActivity;
import com.tiantian.photopicker.utils.PhotoPick;
import com.tiantian.photopicker.utils.ToastUtil;
import com.tiantian.photopicker.utils.UCropUtils;
import com.tiantian.photopicker.weight.GalleryImageView;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: PhotoPickAdapter 本地照片所有列表
 */
public class PhotoPickAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Photo> photos = new ArrayList<>();
    private ArrayList<String> selectPhotos = new ArrayList<>();
    private int maxPickSize;
    private int pickMode;
    private int imageSize;
    private boolean cllipCircle;
    private boolean showCamera;
    private boolean isClipPhoto;
    private boolean isOriginalPictrue;
    private ImageLoader imageLoader;
    private Uri cameraUri;

    public PhotoPickAdapter(Context context, PhotoPickBean pickBean) {
        this.context = context;
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        this.imageSize = metrics.widthPixels / pickBean.getSpanCount();
        this.pickMode = pickBean.getPickMode();
        this.maxPickSize = pickBean.getMaxPickSize();
        this.cllipCircle = pickBean.getClipMode();
        this.showCamera = pickBean.isShowCamera();
        this.isClipPhoto = pickBean.isClipPhoto();
        this.isOriginalPictrue = pickBean.isOriginalPictrue();
        this.imageLoader = pickBean.getImageLoader();

    }

    public void refresh(List<Photo> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_phoyo_pick, null);

        return new PhotoPickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private Photo getItem(int position) {
        return showCamera ? photos.get(position - 1) : photos.get(position);
    }

    private class PhotoPickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GalleryImageView imageView;
        private CheckBox checkBox;

        public PhotoPickViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            checkBox = itemView.findViewById(R.id.checkbox);
            imageView.getLayoutParams().height = imageSize;
            imageView.getLayoutParams().width = imageSize;
            checkBox.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        public void showData(int position) {
            if (showCamera && position == 0) {
                checkBox.setVisibility(View.GONE);
                imageView.setImageResource(R.mipmap.take_photo);
            } else {
                Photo photo = getItem(position);
                if (isClipPhoto) {
                    checkBox.setVisibility(View.GONE);
                } else {
                    checkBox.setVisibility(View.VISIBLE);
                    checkBox.setChecked(selectPhotos.contains(photo.getPath()));
                }
                String url = photo.getPath();
                imageLoader.displayImage(context, url, imageView, true);
            }
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            if (v.getId() == R.id.checkbox) {
                if (selectPhotos.contains(getItem(position).getPath())) {
                    checkBox.setChecked(false);
                    selectPhotos.remove(getItem(position).getPath());
                } else {
                    if (selectPhotos.size() == maxPickSize) {
                        checkBox.setChecked(false);
                        return;
                    } else {
                        checkBox.setChecked(true);
                        selectPhotos.add(getItem(position).getPath());
                    }
                }
                if (onUpdateListenner != null) {
                    onUpdateListenner.updateToolBarTitle(getTitle());
                }
            } else if (v.getId() == R.id.photo_pick_rl) {
                if (showCamera && position == 0) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED) {
                        //申请权限
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, PhotoConfig.REQUEST_CODE_CAMERA);
                    } else {
                        selectPickFromCamera();
                    }
                } else if (isClipPhoto) {
                    //头像剪裁
                    startClipPic(getItem(position).getPath());
                } else {
                    //查看大图
                    //未添加diamante

                }
            }
        }
    }

    //剪裁照片
    public void startClipPic(String picPath) {
        File corpFile = com.tiantian.photopicker.utils.FileUtils.createImageFile(context, picPath);
        UCropUtils.start((Activity)context,new File(picPath),corpFile,isClipPhoto);
    }

    //启动Camera
    public void selectPickFromCamera() {
        if (!android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            ToastUtil.showToast(context, context.getString(R.string.cannot_take_pic));
            return;
        }
        File imageFile = com.tiantian.photopicker.utils.FileUtils.createImageFile(context, "/images");
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProveider创建一个content类型的Uri
            cameraUri = FileProvider.getUriForFile(context, PhotoPick.getAuthority(), imageFile);
            //对目标应用临时授权盖URi所代表的文件，私有目录的读写权限
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else {
            cameraUri=Uri.fromFile(imageFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT,cameraUri);
//        ((Activity)context).startActivityForResult(intent,);
    }
    public Uri getCameraUri(){
        return cameraUri;
    }

    //如果是多选title才会发生变化，单选则不发生变化
    private String getTitle() {
        String title = context.getString(R.string.select_photo);
        //如果不是单选更新title
        if (pickMode == PhotoConfig.MODE_PICK_MORE && selectPhotos.size() >= 1) {

            title = selectPhotos.size() + "/" + maxPickSize;
        }
        return title;
    }
    //获取已经选择了的图片
    public ArrayList<String> getSelectPhotos(){
        return  selectPhotos;
    }

    private OnUpdateListenner onUpdateListenner;

    public void setOnUpdateListenner(OnUpdateListenner listenner) {
        this.onUpdateListenner = listenner;
    }

    public interface OnUpdateListenner {
        void updateToolBarTitle(String title);
    }
}

