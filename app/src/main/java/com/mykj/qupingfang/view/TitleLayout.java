package com.mykj.qupingfang.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.R;

/**
 * Describtion：
 * Created by kangbai on 2017/8/5.
 * 滴水穿石，非一日之功
 */

public class TitleLayout extends RelativeLayout {
    private ImageView iv_my_fanhui;
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_title,this);
        iv_my_fanhui = (ImageView) findViewById(R.id.iv_my_fanhui);
        iv_my_fanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
