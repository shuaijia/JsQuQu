package com.mykj.qupingfang.discover.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.DiscoverAdapter;
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
    private DiscoverAdapter discoverBannerAdapter;
    private List<HomeJp.DataBean.BannerBean> homeBannerList;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                vp_discover_banner.setCurrentItem(vp_discover_banner.getCurrentItem()+1);
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp_discover_banner = (ViewPager) view.findViewById(R.id.vp_discover_banner);
        intiData();
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
                homeBannerList = homeJp.getData().getBanner();
                discoverBannerAdapter = new DiscoverAdapter(context,homeBannerList);
                vp_discover_banner.setAdapter(discoverBannerAdapter);
                vp_discover_banner.setCurrentItem(2000);

                handler.sendEmptyMessageDelayed(1,3000);
            }
        });
    }
}
