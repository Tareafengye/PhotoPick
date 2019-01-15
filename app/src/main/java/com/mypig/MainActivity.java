package com.mypig;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.mypig.fragment.FragmentA;
import com.mypig.fragment.FragmentB;
import com.mypig.fragment.FragmentC;
import com.mypig.fragment.FragmentD;
import com.mypig.fragment.FragmentE;
import com.mypig.model.PhoneDto;
import com.mypig.util.PhoneUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";

    private ViewPager mViewPager;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
    }
    @OnClick(R.id.button2)
    public void turnComprocess(){
        startActivity(new Intent(MainActivity.this,ComproressActivity.class));
    }

}

