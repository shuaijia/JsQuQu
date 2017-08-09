package com.mykj.qupingfang.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.net.retrofit.UrlConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Describtion: 收藏界面适配器
 * Created by jia on 2017/8/8.
 * 人之所以能，是相信能
 */
public class MineCollectionAdapter extends RecyclerView.Adapter<MineCollectionAdapter.CollectionViewHolder> {

    private Context context;

    private List<CollectionLog.Data> list = new ArrayList<>();

    private OnDeleteListener onDeleteListener;
    private OnItemClickListener onItemClickListener;

    public MineCollectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mine_collection, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, final int position) {
        holder.tv_mine_collection_title.setText(list.get(position).getResource().getTitle() + "");
        Glide.with(context)
                .load(UrlConfig.IMAGE_BASE_URL + list.get(position).getResource().getImg_url() + "")
                .placeholder(R.mipmap.frgament_lesson_video_bg)
                .into(holder.iv_mine_collection_img);

        // 条目点击
        holder.rl_mine_collection_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
            }
        });

        // 删除
        holder.tv_mine_collection_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteListener!=null) {
                    onDeleteListener.delete(position);
                }
            }
        });
    }

    public CollectionLog.Data getItem(int position){
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<CollectionLog.Data> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void deleteData(int position){
        if(list.size()>0){
            list.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }
    }

    public interface OnDeleteListener {
        void delete(int position);
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl_mine_collection_content;
        ImageView iv_mine_collection_img;
        TextView tv_mine_collection_title;
        TextView tv_mine_collection_delete;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            iv_mine_collection_img = (ImageView) itemView.findViewById(R.id.iv_mine_collection_img);
            tv_mine_collection_title = (TextView) itemView.findViewById(R.id.tv_mine_collection_title);
            tv_mine_collection_delete = (TextView) itemView.findViewById(R.id.tv_mine_collection_delete);
            rl_mine_collection_content = (RelativeLayout) itemView.findViewById(R.id.rl_mine_collection_content);
        }
    }
}
