package com.mypig.dog;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProgressBarDog extends ViewPager {
    private float mTrans;
    private float mScale;
    private String TAG="ProgressBarDog";
    /***
     * 保存position与对于Viewe
     * */
    Map<Integer,View> mChildrenViews=new LinkedHashMap<>();



    public ProgressBarDog(@NonNull Context context) {
        super(context);
    }

    public ProgressBarDog(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);

    }
}
