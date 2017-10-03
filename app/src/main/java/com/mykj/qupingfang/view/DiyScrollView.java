package com.mykj.qupingfang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Describtion：
 * Created by kangbai on 2017/9/17.
 * 滴水穿石，非一日之功
 */

public class DiyScrollView extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;

    public DiyScrollView(Context context) {
        super(context);
        //触发移动事件的最短距离,判断是否滑动
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public DiyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //触发移动事件的最短距离,判断是否滑动
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public DiyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //触发移动事件的最短距离,判断是否滑动
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;//返回true,滑动事件在此被拦截,即被ScrollView消费，不再往子View传递,
                    // 使得ScollView滑动起来更流畅
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
