package com.mykj.qupingfang.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeBannerAdapter;
import com.mykj.qupingfang.adapter.home.HomeJpAdapter;
import com.mykj.qupingfang.adapter.home.HomeZjAdapter;
import com.mykj.qupingfang.adapter.home.HomeZtAdapter;
import com.mykj.qupingfang.base.BaseFragment;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.activity.MoreActivity;
import com.mykj.qupingfang.net.retrofit.HttpMethod;
import com.mykj.qupingfang.utils.ToastUtils;
import com.mykj.qupingfang.view.JsPopupWindow;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private Context context;

    private RecyclerView rv_home_kcjp;
    private RecyclerView rv_home_zjgx;
    private RecyclerView rv_home_spzt;
    private Banner vp_home_lunbo;
    private TextView tv_home_zjgx;
    private TextView tv_home_kcjp;
    private TextView tv_home_spzt;
    // +号
    private ImageView iv_home_more;
    // 搜索
    private LinearLayout ll_home_more_search;
    // 扫一扫
    private LinearLayout ll_home_more_scan;
    // ar
    private LinearLayout ll_home_more_ar;

    private List<HomeJp.DataBean.ResourceJpBean> homeJpList;
    private List<HomeJp.DataBean.ResourceZxBean> homeZxList;
    private List<HomeJp.DataBean.ResourceZtBean> homeZtList;
    private List<HomeJp.DataBean.BannerBean> homeBannerList;
    private HomeJpAdapter homeJpAdapter;
    private HomeZjAdapter homeZjAdapter;
    private HomeZtAdapter homeZtAdapter;
    private HomeBannerAdapter homeBannerAdapter;

    // +号popupWindow
    private JsPopupWindow popWindow;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
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

        initPopWindow();
    }

    private void allFindViewById(View view) {
        rv_home_kcjp = (RecyclerView) view.findViewById(R.id.rv_home_kcjp);
        rv_home_zjgx = (RecyclerView) view.findViewById(R.id.rv_home_zjgx);
        rv_home_spzt = (RecyclerView) view.findViewById(R.id.rv_home_spzt);
        vp_home_lunbo = (Banner) view.findViewById(R.id.vp_home_lunbo);
        tv_home_zjgx = (TextView) view.findViewById(R.id.tv_home_zjgx);
        tv_home_zjgx.setOnClickListener(this);
        tv_home_kcjp = (TextView) view.findViewById(R.id.tv_home_kcjp);
        tv_home_kcjp.setOnClickListener(this);
        tv_home_spzt = (TextView) view.findViewById(R.id.tv_home_spzt);
        tv_home_spzt.setOnClickListener(this);
        iv_home_more = (ImageView) view.findViewById(R.id.iv_home_more);
        iv_home_more.setOnClickListener(this);
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

                setHomeBanner(homejp);

            }
        });
    }

    private void setJpRecyclerView(HomeJp homejp) {
        homeJpList = homejp.getData().getResource_jp();
        homeJpAdapter = new HomeJpAdapter(context, homeJpList);
        GridLayoutManager homeJpLayoutManager = new GridLayoutManager(context, 2);
        rv_home_kcjp.setLayoutManager(homeJpLayoutManager);
        rv_home_kcjp.setAdapter(homeJpAdapter);
    }

    private void setZjRecyclerView(HomeJp homejp) {
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

    private void setHomeBanner(HomeJp homejp) {
        homeBannerList = homejp.getData().getBanner();

        // List<ImageView> imgs = new ArrayList<ImageView>();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < homeBannerList.size(); i++) {
            ImageView view = new ImageView(context);

//            Glide.with(context)
//                    .load(homeBannerList.get(i).getImg_curl())
//                    .into(view);
//            view.setScaleType(ImageView.ScaleType.FIT_XY);
            imgs.add(homeBannerList.get(i).getImg_curl());
        }

//        homeBannerAdapter = new HomeBannerAdapter(context, imgs);
//        vp_home_lunbo.setAdapter(homeBannerAdapter);
        //运用github依赖库实现无限轮播
        vp_home_lunbo.setImageLoader(new GlideImageLoader());
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_zjgx:
                MoreActivity.actionMoreActivity(context, "最近更新", "zx_more");
                break;
            case R.id.tv_home_kcjp:
                MoreActivity.actionMoreActivity(context, "课程精品", "jp_more");
                break;
            case R.id.tv_home_spzt:
                MoreActivity.actionMoreActivity(context, "视频专题", "zt_more");
                break;
            case R.id.iv_home_more:

                if(popWindow.isShowing()){
                    popWindow.dismiss();
                }else {
                    popWindow.showAsLocation(iv_home_more, Gravity.BOTTOM |Gravity.RIGHT, 30, 0);
                }

                break;
            // 搜索
            case R.id.ll_home_more_search:

                ToastUtils.showToastSafe(context,"搜索");
                popWindow.dismiss();

                break;
            // 扫一扫
            case R.id.ll_home_more_scan:

                ToastUtils.showToastSafe(context,"扫一扫");
                popWindow.dismiss();

                break;
            // AR
            case R.id.ll_home_more_ar:

                ToastUtils.showToastSafe(context,"敬请期待");
                popWindow.dismiss();

                break;
        }
    }

    /**
     * 设置弹出框
     */
    private void initPopWindow() {
        popWindow = new JsPopupWindow.Builder()
                .setContentViewId(R.layout.dialog_home_more)
                .setContext(context)
                .setOutSideCancle(true)
                .setHeight(450)
                .setWidth(450)
                .build();

        // 搜索
        ll_home_more_search= (LinearLayout) popWindow.getItemView(R.id.ll_home_more_search);
        ll_home_more_search.setOnClickListener(this);
        // 扫一扫
        ll_home_more_scan= (LinearLayout) popWindow.getItemView(R.id.ll_home_more_scan);
        ll_home_more_scan.setOnClickListener(this);
        // ar
        ll_home_more_ar= (LinearLayout) popWindow.getItemView(R.id.ll_home_more_ar);
        ll_home_more_ar.setOnClickListener(this);
    }

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }
    }
}
