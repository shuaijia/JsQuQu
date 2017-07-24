package com.mykj.qupingfang.discover.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeBannerAdapter;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/23.
 */

public class DiscoverFragment extends Fragment {
    private Context context;
    private ViewPager vp_discover_banner;
    private HomeBannerAdapter discoverBannerAdapter;
    private List<HomeJp.DataBean.BannerBean> homeBannerList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFindViewById(view);

        intiData();
    }

    private void allFindViewById(View view) {
        vp_discover_banner = (ViewPager) view.findViewById(R.id.vp_discover_banner);

    }

    private void intiData() {
        HttpMethod.getInstance().getHomeJp(new Subscriber<HomeJp>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HomeJp homeJp) {
                setDiscoverBanner(homeJp);
            }
        });
    }

    private void setDiscoverBanner(HomeJp homeJp) {
        List<ImageView> imgs = new ArrayList<ImageView>();
        homeBannerList = homeJp.getData().getBanner();
        for (int i = 0; i < homeBannerList.size(); i++) {
            ImageView img = new ImageView(context);
            Glide.with(context)
                    .load(homeBannerList.get(i).getImg_curl())
                    .into(img);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            imgs.add(img);
        }
        discoverBannerAdapter = new HomeBannerAdapter(context, imgs);
        vp_discover_banner.setAdapter(discoverBannerAdapter);
    }
}
