package com.mykj.qupingfang.adapter.lesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Describtion：
 * Created by kangbai on 2017/7/29.
 * 滴水穿石，非一日之功
 */

public class PopupwindowAdapter extends RecyclerView.Adapter<PopupwindowAdapter.ViewHolder>{
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
