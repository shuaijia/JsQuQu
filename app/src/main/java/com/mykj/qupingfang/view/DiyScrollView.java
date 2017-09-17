package com.mykj.qupingfang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Describtion：
 * Created by kangbai on 2017/9/17.
 * 滴水穿石，非一日之功
 */

public class DiyScrollView extends ScrollView {
    public DiyScrollView(Context context) {
        super(context);
    }

    public DiyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;//返回true,滑动事件在此被拦截,不再往子View传递,使得ScollView滑动起来更流畅
    }
}
