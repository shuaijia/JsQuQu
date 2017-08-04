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
import com.mykj.qupingfang.domain.home.HomeSp;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/29.
 * 滴水穿石，非一日之功
 */

public class HomeMoreSpAdapter extends RecyclerView.Adapter<HomeMoreSpAdapter.ViewHolder> {
    private Context context;
    private List<HomeSp.DataBean.FeatureBean> list;

    public HomeMoreSpAdapter(Context context) {
        this.context = context;
    }

    //通过这个方法把数据源传入
    public void addData(List<HomeSp.DataBean.FeatureBean> list) {
        this.list = list;
        notifyDataSetChanged();//通知数据发生改变，adapter会更新
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_more, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeSp.DataBean.FeatureBean bean = list.get(position);
        Glide.with(context)//用Glide加载图片
                .load(bean.getImg_curl())//拼接url
                .into(holder.iv_more_pic);//把图片放到ImageView上
        holder.tv_more_name.setText("《" + bean.getImg_name() + "》");//把视频名称放到TextView上
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();//返回Item个数
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_more_pic;
        public TextView tv_more_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_more_pic = (ImageView) itemView.findViewById(R.id.iv_more_pic);
            tv_more_name = (TextView) itemView.findViewById(R.id.tv_more_name);
        }
    }

}
