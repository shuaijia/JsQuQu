package com.mykj.qupingfang.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeBannerAdapter;
import com.mykj.qupingfang.adapter.home.HomeJpAdapter;
import com.mykj.qupingfang.adapter.home.HomeZjAdapter;
import com.mykj.qupingfang.adapter.home.HomeZtAdapter;
import com.mykj.qupingfang.base.BaseFragment;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private Context context;
    private RecyclerView rv_home_kcjp;
    private RecyclerView rv_home_zjgx;
    private RecyclerView rv_home_spzt;
    private ViewPager vp_home_lunbo;
    private List<HomeJp.DataBean.ResourceJpBean> homeJpList;
    private List<HomeJp.DataBean.ResourceZxBean> homeZxList;
    private List<HomeJp.DataBean.ResourceZtBean> homeZtList;
    private List<HomeJp.DataBean.BannerBean> homeBannerList;
    private HomeJpAdapter homeJpAdapter;
    private HomeZjAdapter homeZjAdapter;
    private HomeZtAdapter homeZtAdapter;
    private HomeBannerAdapter homeBannerAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFindViewById(view);

        intiData();
    }

    private void allFindViewById(View view ){
        rv_home_kcjp = (RecyclerView) view.findViewById(R.id.rv_home_kcjp);
        rv_home_zjgx = (RecyclerView) view.findViewById(R.id.rv_home_zjgx);
        rv_home_spzt = (RecyclerView) view.findViewById(R.id.rv_home_spzt);
        vp_home_lunbo = (ViewPager) view.findViewById(R.id.vp_home_lunbo);
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
            public void onNext(HomeJp homejp) {

                setJpRecyclerView(homejp);

                setZjRecyclerView(homejp);

                setZtRecyclerView(homejp);

                sethomeBanner(homejp);

            }
        });
    }

    private void setJpRecyclerView(HomeJp homejp){
        homeJpList = homejp.getData().getResource_jp();
        homeJpAdapter = new HomeJpAdapter(context, homeJpList);
        GridLayoutManager homeJpLayoutManager = new GridLayoutManager(context, 2);
        rv_home_kcjp.setLayoutManager(homeJpLayoutManager);
        rv_home_kcjp.setAdapter(homeJpAdapter);
    }

    private void setZjRecyclerView(HomeJp homejp){
        homeZxList = homejp.getData().getResource_zx();
        homeZjAdapter = new HomeZjAdapter(context, homeZxList);
        GridLayoutManager homeZjLayoutManager = new GridLayoutManager(context, 2);
        rv_home_zjgx.setLayoutManager(homeZjLayoutManager);
        rv_home_zjgx.setAdapter(homeZjAdapter);
    }

    private void setZtRecyclerView(HomeJp homejp) {
        homeZtList = homejp.getData().getResource_zt();
        homeZtAdapter = new HomeZtAdapter(context, homeZtList);
        GridLayoutManager homeZtLayoutManager = new GridLayoutManager(context, 3);
        rv_home_spzt.setLayoutManager(homeZtLayoutManager);
        rv_home_spzt.setAdapter(homeZtAdapter);
    }

    private void sethomeBanner(HomeJp homejp) {
        homeBannerList = homejp.getData().getBanner();

        List<ImageView> imgs = new ArrayList<ImageView>();
        for (int i = 0; i < homeBannerList.size(); i++) {
            ImageView view = new ImageView(context);
            Glide.with(context)
                    .load(homeBannerList.get(i).getImg_curl())
                    .into(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imgs.add(view);
        }

        homeBannerAdapter = new HomeBannerAdapter(context, imgs);
        vp_home_lunbo.setAdapter(homeBannerAdapter);
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
}
