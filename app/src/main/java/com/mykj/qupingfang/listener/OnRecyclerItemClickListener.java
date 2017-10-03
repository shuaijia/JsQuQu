package com.mykj.qupingfang.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Describtion：
 * Created by kangbai on 2017/10/3.
 * 滴水穿石，非一日之功
 */

public abstract class OnRecyclerItemClickListener
        implements RecyclerView.OnItemTouchListener{
    //手势探测器
    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;

    public OnRecyclerItemClickListener(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),
                new ItemTouchHelperGestureListener());
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);
    public abstract void onLongClick(RecyclerView.ViewHolder viewHolder);

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener{

        //一次单独的轻触抬起手操作，就是普通的点击事件
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            //findChildViewUnder,通过这个方法获得点击的 item
            View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childViewUnder != null){
                //getChildViewHolder.获得该 item 的 ViewHolder
                RecyclerView.ViewHolder childViewHolder
                        = mRecyclerView.getChildViewHolder(childViewUnder);
                onItemClick(childViewHolder);
            }
            //return super.onSingleTapUp(e);
            return true;
        }

        //长按屏幕超过一定时长，就会触发，就是长按事件
        @Override
        public void onLongPress(MotionEvent e) {
            //findChildViewUnder,通过这个方法获得点击的 item
            View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if (childViewUnder != null){
                //getChildViewHolder.获得该 item 的 ViewHolder
                RecyclerView.ViewHolder childViewHolder
                        = mRecyclerView.getChildViewHolder(childViewUnder);
                onLongClick(childViewHolder);
            }
        }
    }

}
