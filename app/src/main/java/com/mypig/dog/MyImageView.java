package com.mypig.dog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.mypig.R;

import java.io.ByteArrayOutputStream;

import static com.mypig.util.Constants.COMPRESS_MATRIX;
import static com.mypig.util.Constants.COMPRESS_QUALITY;
import static com.mypig.util.Constants.COMPRESS_RGB_565;
import static com.mypig.util.Constants.COMPRESS_SAMPLING;
import static com.mypig.util.Constants.COMPRESS_SCALE;


@SuppressLint("AppCompatCustomView")
public class MyImageView extends View {

    private Bitmap mSrcBitmap;
    private TextListener mListener;
    private Paint mBitPaint;
    private String mSrcSize;
    private int screenWidth;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setListener(TextListener listener) {
        mListener = listener;
    }

    private void init() {
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        compressQuality();
    }

    public void setCompressType(int type) {
        switchCompressWay(type);
        invalidate();
    }

    private void switchCompressWay(int type) {
        switch (type) {
            case COMPRESS_QUALITY:
                compressQuality();
                break;
            case COMPRESS_SAMPLING:
                compressSampling();
                break;
            case COMPRESS_MATRIX:
                compressMatrix();
                break;
            case COMPRESS_RGB_565:
                compressRGB565();
            case COMPRESS_SCALE:
                compressScaleBitmap();
            default:
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect mSrcRect = new Rect(0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight());
        Rect mDestRect = new Rect(screenWidth / 2 - 300, 0, screenWidth / 2 + 300, 900);
        if (mListener != null) {
            mListener.showText(mSrcSize, mSrcBitmap.getByteCount() + "byte");
        }
        canvas.drawBitmap(mSrcBitmap, mSrcRect, mDestRect, mBitPaint);
    }

    private void compressQuality() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mSrcSize = bm.getByteCount() + "byte";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();
        mSrcBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private void compressSampling() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
    }

    private void compressMatrix() {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mSrcBitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        bm.recycle();
    }

    private void compressRGB565() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
    }

    private void compressScaleBitmap() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mSrcBitmap = Bitmap.createScaledBitmap(bm, 600, 900, true);
        bm.recycle();
    }

    public interface TextListener {
        void showText(String srcSize, String size);
    }
}
