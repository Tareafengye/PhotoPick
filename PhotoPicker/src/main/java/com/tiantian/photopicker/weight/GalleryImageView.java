package com.tiantian.photopicker.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/10
 * Descriptions: GalleryImageView 自定义ImageVIew用来兼容fresco
 */
@SuppressLint("AppCompatCustomView")
public class GalleryImageView extends ImageView {

    private OnImageViewListener mOnImageViewListener;
    public GalleryImageView(Context context) {
        super(context);
    }

    public GalleryImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setOnImageViewListener(OnImageViewListener listener){
        mOnImageViewListener=listener;
    }
    public static interface OnImageViewListener{
        void onDetach();
        void onAttach();
        boolean verifyDrawable(Drawable dr);
        void onDrawa(Canvas canvas);
        boolean onTouchEvent(MotionEvent event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mOnImageViewListener!=null){
            mOnImageViewListener.onDetach();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mOnImageViewListener!=null){
            mOnImageViewListener.onAttach();
        }
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable dr) {
        if (mOnImageViewListener!=null){
            if (mOnImageViewListener.verifyDrawable(dr));
            return true;
        }
        return super.verifyDrawable(dr);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (mOnImageViewListener!=null){
            mOnImageViewListener.onDetach();
        }
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (mOnImageViewListener!=null){
            mOnImageViewListener.onAttach();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mOnImageViewListener!=null){
            mOnImageViewListener.onDrawa(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mOnImageViewListener==null){
            return super.onTouchEvent(event);
        }
        return mOnImageViewListener.onTouchEvent(event)||super.onTouchEvent(event);

    }
}

