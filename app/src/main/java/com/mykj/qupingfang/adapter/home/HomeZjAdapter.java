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
import com.mykj.qupingfang.utils.GradeUtils;
import com.mykj.qupingfang.utils.TimeUtils;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/23.
 * 滴水穿石，非一日之功
 */

public class HomeZjAdapter extends RecyclerView.Adapter<HomeZjAdapter.HomeZjViewHodler> {
    private Context context;
    private List<HomeJp.DataBean.ResourceZxBean> list;

    public HomeZjAdapter(Context context, List<HomeJp.DataBean.ResourceZxBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HomeZjAdapter.HomeZjViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_first, parent, false);
        return new HomeZjViewHodler(view);
    }

    @Override
    public void onBindViewHolder(HomeZjAdapter.HomeZjViewHodler holder, int position) {
        HomeJp.DataBean.ResourceZxBean bean = list.get(position);
        Glide.with(context)
                .load("http://test.lovek12.com" + bean.getImg_url())
                .into(holder.iv_home_grade);
        if (!bean.getPrice().equals("1.00")) {
            holder.iv_home_price.setVisibility(View.GONE);
        }

        holder.tv_home_time.setText(bean.getDuration());
        holder.tv_home_grade.setText(GradeUtils.IntToGrade(bean.getGrade()+""));
        holder.tv_home_name.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class HomeZjViewHodler extends RecyclerView.ViewHolder {
        public ImageView iv_home_grade;
        public TextView tv_home_name;
        public TextView tv_home_grade;
        public TextView tv_home_time;
        public ImageView iv_home_price;

        public HomeZjViewHodler(View itemView) {
            super(itemView);
            iv_home_grade = (ImageView) itemView.findViewById(R.id.iv_home_grade);
            tv_home_name = (TextView) itemView.findViewById(R.id.tv_home_name);
            tv_home_grade = (TextView) itemView.findViewById(R.id.tv_home_grade);
            tv_home_time = (TextView) itemView.findViewById(R.id.tv_home_time);
            iv_home_price = (ImageView) itemView.findViewById(R.id.iv_home_price);
        }
    }
}
