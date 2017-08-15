package com.mykj.qupingfang.home.contract;

import com.mykj.qupingfang.domain.home.HomeJp;

/**
 * Describtion: Home界面的契约类
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class HomeContract {
    public interface HomeView {

        void getHomeJpSuccess(HomeJp homeJp);

        void getHomeJpError(String errorMsg);
    }

    public interface HomeModel {

        void getHomeJp(HomeJpCallBack homeJpCallBack);
    }

    public interface HomeJpCallBack {

        void onSuccess(HomeJp homeJp);

        void onError(String errorMsg);
    }
}
