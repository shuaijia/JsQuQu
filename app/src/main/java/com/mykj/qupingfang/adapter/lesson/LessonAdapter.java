package com.mykj.qupingfang.adapter.lesson;

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
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.domain.login.Login;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/22.
 * 滴水穿石，非一日之功
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHodler> {

    private List<Lesson.DataBean> list;

    private Context context;

    public LessonAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<Lesson.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lesson_first, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        Lesson.DataBean dataBean = list.get(position);
        holder.tv_lesson_grade2.setText(dataBean.getGrade());
        holder.tv_lesson_name.setText(dataBean.getTitle());
        holder.tv_lesson_time.setText(dataBean.getCtime());

        // Glide加载图片必须步骤
        Glide.with(context)
                .load("http://test.lovek12.com" + dataBean.getImg_url())
                .into(holder.iv_lesson_img);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {

        public TextView tv_lesson_name;
        public TextView tv_lesson_grade2;
        public TextView tv_lesson_time;
        public ImageView iv_lesson_img;

        public ViewHodler(View itemView) {
            super(itemView);
            tv_lesson_name = (TextView) itemView.findViewById(R.id.tv_lesson_name);
            tv_lesson_grade2 = (TextView) itemView.findViewById(R.id.tv_lesson_grade2);
            tv_lesson_time = (TextView) itemView.findViewById(R.id.tv_lesson_time);
            iv_lesson_img = (ImageView) itemView.findViewById(R.id.iv_lesson_img);
        }
    }
}
