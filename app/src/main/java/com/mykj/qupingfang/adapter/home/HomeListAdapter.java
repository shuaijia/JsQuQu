package com.mykj.qupingfang.adapter.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.fragment.HomeFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Describtion：
 * Created by kangbai on 2017/9/17.
 * 滴水穿石，非一日之功
 */

public class HomeListAdapter extends RecyclerView.Adapter {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;

    private Context context;

    private HomeJp.DataBean bean;

    public HomeListAdapter(Context context, HomeJp.DataBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return FIRST;
            case 1:
            case 2:
                return SECOND;
            case 3:
                return THIRD;
        }
        return THIRD;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case FIRST:
                View bannerView = LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false);
                return new BannerViewHolder(bannerView);
            case SECOND:
                View courseView = LayoutInflater.from(context).inflate(R.layout.item_home_course, parent, false);
                return new CourseViewHolder(courseView);
            case THIRD:
                View jpView = LayoutInflater.from(context).inflate(R.layout.item_home_jp, parent, false);
                return new JpViewHolder(jpView);
        }
        View bannerView = LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false);
        return new BannerViewHolder(bannerView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            setHomeBanner(((BannerViewHolder) holder).vp_home_lunbo);
        } else if (holder instanceof CourseViewHolder) {
            if (position == 1) {
                List<HomeJp.DataBean.ResourceJpBean> jpBeen = bean.getResource_jp();
                Glide.with(context)
                        .load("http://test.lovek12.com" + jpBeen.get(0).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_first);
                Glide.with(context)
                        .load("http://test.lovek12.com" + jpBeen.get(1).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_second);
                Glide.with(context)
                        .load("http://test.lovek12.com" + jpBeen.get(2).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_third);
                Glide.with(context)
                        .load("http://test.lovek12.com" + jpBeen.get(3).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_fourth);
            } else {
                List<HomeJp.DataBean.ResourceZxBean> zxBeen = bean.getResource_zx();
                Glide.with(context)
                        .load("http://test.lovek12.com" + zxBeen.get(0).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_first);
                Glide.with(context)
                        .load("http://test.lovek12.com" + zxBeen.get(1).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_second);
                Glide.with(context)
                        .load("http://test.lovek12.com" + zxBeen.get(2).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_third);
                Glide.with(context)
                        .load("http://test.lovek12.com" + zxBeen.get(3).getImg_url())
                        .into(((CourseViewHolder) holder).iv_home_course_fourth);
            }
        } else {
            List<HomeJp.DataBean.ResourceZtBean> ztBeen = bean.getResource_zt();
            Glide.with(context)
                    .load(ztBeen.get(0).getImg_curl())
                    .into(((JpViewHolder) holder).iv_home_jp_first);
            Glide.with(context)
                    .load(ztBeen.get(1).getImg_curl())
                    .into(((JpViewHolder) holder).iv_home_jp_second);
            Glide.with(context)
                    .load(ztBeen.get(2).getImg_curl())
                    .into(((JpViewHolder) holder).iv_home_jp_third);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private void setHomeBanner(Banner vp_home_lunbo) {

        List<HomeJp.DataBean.BannerBean> homeBannerList = bean.getBanner();

        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < homeBannerList.size(); i++) {

            imgs.add(homeBannerList.get(i).getImg_curl());
        }
        //运用github依赖库实现无限轮播
        vp_home_lunbo.setImageLoader(new HomeFragment.GlideImageLoader());
        vp_home_lunbo.setImages(imgs);
        vp_home_lunbo.start();

        vp_home_lunbo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //在滑动就走这个方法，用户停止滑动就不走了
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                        Log.e(TAG, "onPageScrolled: "+position);
            }

            //滑动结束且item发生切换，position表示当前所在的item
            @Override
            public void onPageSelected(int position) {
//                        Log.e(TAG, "onPageSelected: "+position);
            }

            //state:1表示用户正在滑动ViewPage的item，2表示用户松手，item自由滑动
            //0表示滑动结束，无论item有无切换
            @Override
            public void onPageScrollStateChanged(int state) {
//                        Log.e(TAG, "onPageScrollStateChanged: "+state );
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner vp_home_lunbo;

        public BannerViewHolder(View itemView) {

            super(itemView);
            vp_home_lunbo = (Banner) itemView.findViewById(R.id.vp_home_lunbo);
        }
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_home_course_first;
        public ImageView iv_home_course_second;
        public ImageView iv_home_course_third;
        public ImageView iv_home_course_fourth;

        public CourseViewHolder(View itemView) {
            super(itemView);
            iv_home_course_first = (ImageView) itemView.findViewById(R.id.iv_home_course_first);
            iv_home_course_second = (ImageView) itemView.findViewById(R.id.iv_home_course_second);
            iv_home_course_third = (ImageView) itemView.findViewById(R.id.iv_home_course_third);
            iv_home_course_fourth = (ImageView) itemView.findViewById(R.id.iv_home_course_fourth);
        }
    }

    class JpViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_home_jp_first;
        public ImageView iv_home_jp_second;
        public ImageView iv_home_jp_third;

        public JpViewHolder(View itemView) {
            super(itemView);
            iv_home_jp_first = (ImageView) itemView.findViewById(R.id.iv_home_jp_first);
            iv_home_jp_second = (ImageView) itemView.findViewById(R.id.iv_home_jp_second);
            iv_home_jp_third = (ImageView) itemView.findViewById(R.id.iv_home_jp_third);
        }
    }
}
