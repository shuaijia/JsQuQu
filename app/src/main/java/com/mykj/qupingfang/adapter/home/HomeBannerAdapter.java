package com.mykj.qupingfang.adapter.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.domain.home.HomeJp;

import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/7/23.
 * 滴水穿石，非一日之功
 */

public class HomeBannerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView> list;

    public HomeBannerAdapter(Context context, List<ImageView> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

        ((ViewPager) container).removeView(list.get(position));

    }


    @Override
    public Object instantiateItem(View container, int position) {

        ImageView imageView = list.get(position);

        ((ViewPager) container).addView(imageView);

        return imageView;

    }


    @Override

    public int getCount() {

        return list == null ? 0 : list.size();

    }


    @Override

    public boolean isViewFromObject(View view, Object object) {

        return view == object;

    }
}
