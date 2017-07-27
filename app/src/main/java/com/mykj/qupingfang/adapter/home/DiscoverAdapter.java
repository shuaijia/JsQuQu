package com.mykj.qupingfang.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.MainActivity;
import com.mykj.qupingfang.SplashActivity;
import com.mykj.qupingfang.domain.home.HomeJp;

import java.util.List;

/**
 * Describtion：发现首页轮播图适配器
 * Created by kangbai on 2017/7/27.
 * 滴水穿石，非一日之功
 */

public class DiscoverAdapter extends PagerAdapter {

    private Context context;
    private List<HomeJp.DataBean.BannerBean> list;

    public DiscoverAdapter(Context context, List<HomeJp.DataBean.BannerBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

        ((ViewPager) container).removeView((View) object);
    }


    @Override
    public Object instantiateItem(View container, int position) {

        ImageView imageView = new ImageView(context);
        Glide.with(context)
                .load(list.get(position % list.size()).getImg_curl())
                .into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, SplashActivity.class);
//                context.startActivity(intent);
            }
        });
        ((ViewPager) container).addView(imageView);
        return imageView;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
