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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeBannerAdapter;
import com.mykj.qupingfang.adapter.home.HomeJpAdapter;
import com.mykj.qupingfang.adapter.home.HomeZjAdapter;
import com.mykj.qupingfang.adapter.home.HomeZtAdapter;
import com.mykj.qupingfang.base.BaseFragment;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.activity.MoreActivity;
import com.mykj.qupingfang.home.contract.HomeContract;
import com.mykj.qupingfang.home.presenter.HomePresenter;
import com.mykj.qupingfang.net.retrofit.HttpMethod;
import com.mykj.qupingfang.utils.ToastUtils;
import com.mykj.qupingfang.view.JsPopupWindow;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Describtion：首页碎片
 * Created by Administrator on 2017/7/23.
 */

public class HomeFragment extends BaseFragment<HomeContract.HomeView, HomePresenter> implements HomeContract.HomeView, View.OnClickListener {

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
    //分享的popupWindow
    private JsPopupWindow sharePopWindow;
    //微信朋友圈
    private RelativeLayout ll_video_complete_share_weixin_pengyouquan;
    //微信好友
    private RelativeLayout ll_video_complete_share_weixin;
    //QQ好友
    private RelativeLayout ll_video_complete_share_qq;
    //微博
    private RelativeLayout ll_video_complete_share_sina;
    //QQ空间
    private RelativeLayout ll_video_complete_share_qzone;
    //返回
    private TextView tv_video_complete_share_cancel;

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null, false);
    }

    @Override
    protected void initFragmentChildView(View view) {
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

        mPresenter.getHomeJp();
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        context = getActivity();
        initPopWindow();
        initSharePopWindow();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
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

                if (popWindow.isShowing()) {
                    popWindow.dismiss();
                } else {
                    popWindow.showAsLocation(iv_home_more, Gravity.BOTTOM | Gravity.RIGHT, 30, 0);
                }

                break;
            // 搜索
            case R.id.ll_home_more_search:

                ToastUtils.showToastSafe(context, "搜索");
                popWindow.dismiss();

                break;
            // 扫一扫
            case R.id.ll_home_more_scan:

                ToastUtils.showToastSafe(context, "扫一扫");
                popWindow.dismiss();

                break;
            // AR
            case R.id.ll_home_more_ar:

                ToastUtils.showToastSafe(context, "敬请期待");
                popWindow.dismiss();

                break;
            case R.id.ll_video_complete_share_weixin_pengyouquan:
                sharePopWindow.dismiss();
                // 开始分享
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(getShareWeb())
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.ll_video_complete_share_weixin:
                sharePopWindow.dismiss();
                // 开始分享
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(getShareWeb())
                        .setCallback(shareListener)//回调监听器
                        .share();
            case R.id.ll_video_complete_share_qq:
                sharePopWindow.dismiss();
                // 开始分享
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(getShareWeb())
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.ll_video_complete_share_sina:
                sharePopWindow.dismiss();
                // 开始分享
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .withMedia(getShareWeb())
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.ll_video_complete_share_qzone:
                sharePopWindow.dismiss();
                // 开始分享
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withMedia(getShareWeb())
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.tv_video_complete_share_cancel:
                if (sharePopWindow.isShowing()){
                    sharePopWindow.dismiss();
                }
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
        ll_home_more_search = (LinearLayout) popWindow.getItemView(R.id.ll_home_more_search);
        ll_home_more_search.setOnClickListener(this);
        // 扫一扫
        ll_home_more_scan = (LinearLayout) popWindow.getItemView(R.id.ll_home_more_scan);
        ll_home_more_scan.setOnClickListener(this);
        // ar
        ll_home_more_ar = (LinearLayout) popWindow.getItemView(R.id.ll_home_more_ar);
        ll_home_more_ar.setOnClickListener(this);
    }

    private void initSharePopWindow() {
        sharePopWindow = new JsPopupWindow.Builder()
                .setContentViewId(R.layout.pop_video_detail_share)
                .setContext(context)
                .setOutSideCancle(true)
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setWidth(WindowManager.LayoutParams.MATCH_PARENT)
                .build();

        ll_video_complete_share_weixin_pengyouquan = (RelativeLayout) sharePopWindow.getItemView(R.id.ll_video_complete_share_weixin_pengyouquan);
        ll_video_complete_share_weixin_pengyouquan.setOnClickListener(this);

        ll_video_complete_share_weixin = (RelativeLayout) sharePopWindow.getItemView(R.id.ll_video_complete_share_weixin);
        ll_video_complete_share_weixin.setOnClickListener(this);

        ll_video_complete_share_qq = (RelativeLayout) sharePopWindow.getItemView(R.id.ll_video_complete_share_qq);
        ll_video_complete_share_qq.setOnClickListener(this);

        ll_video_complete_share_sina = (RelativeLayout) sharePopWindow.getItemView(R.id.ll_video_complete_share_sina);
        ll_video_complete_share_sina.setOnClickListener(this);

        ll_video_complete_share_qzone = (RelativeLayout) sharePopWindow.getItemView(R.id.ll_video_complete_share_qzone);
        ll_video_complete_share_qzone.setOnClickListener(this);

        tv_video_complete_share_cancel = (TextView) sharePopWindow.getItemView(R.id.tv_video_complete_share_cancel);
        tv_video_complete_share_cancel.setOnClickListener(this);
    }

    private UMWeb getShareWeb() {
        UMImage image = new UMImage(getActivity(), R.mipmap.aa_discover);

        UMWeb web = new UMWeb("http://blog.csdn.net/jiashuai94");
        web.setTitle("欢迎光临我的CSDN");//标题
        web.setThumb(image);  //缩略图
        return web;
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void getHomeJpSuccess(HomeJp homeJp) {
        setJpRecyclerView(homeJp);

        setZjRecyclerView(homeJp);

        setZtRecyclerView(homeJp);

        setHomeBanner(homeJp);
    }

    @Override
    public void getHomeJpError(String errorMsg) {
        Toast.makeText(context, "获取首页数据失败" + errorMsg, Toast.LENGTH_SHORT).show();
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

        homeZjAdapter.setOnItemClickListener(new HomeZjAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                sharePopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            }
        });
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

    class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }
    }
}
