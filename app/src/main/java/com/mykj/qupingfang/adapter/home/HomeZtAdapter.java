package com.mykj.qupingfang.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.domain.home.HomeJp;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/23.
 * 滴水穿石，非一日之功
 */

public class HomeZtAdapter extends RecyclerView.Adapter<HomeZtAdapter.HomeZtViewHodler>{
    private Context context;
    private List<HomeJp.DataBean.ResourceZtBean> list;

    public HomeZtAdapter(Context context, List<HomeJp.DataBean.ResourceZtBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HomeZtViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_second,parent,false);
        return new HomeZtViewHodler(view);
    }

    @Override
    public void onBindViewHolder(HomeZtViewHodler holder, int position) {
        HomeJp.DataBean.ResourceZtBean bean = list.get(position);
        holder.tv_home_spzt.setText(bean.getImg_name());
        Glide.with(context)
                .load(bean.getImg_curl())
                .into(holder.iv_home_spzt);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeZtViewHodler extends RecyclerView.ViewHolder{
        public ImageView iv_home_spzt;
        public TextView tv_home_spzt;
        public HomeZtViewHodler(View itemView) {
            super(itemView);
            iv_home_spzt = (ImageView) itemView.findViewById(R.id.iv_home_spzt);
            tv_home_spzt = (TextView) itemView.findViewById(R.id.tv_home_spzt);
        }
    }
}
