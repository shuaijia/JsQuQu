package com.mykj.qupingfang.home.model;

import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.contract.HomeContract;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import rx.Subscriber;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class HomeModelImpl implements HomeContract.HomeModel {
    @Override
    public void getHomeJp(final HomeContract.HomeJpCallBack homeJpCallBack) {
        HttpMethod.getInstance().getHomeJp(new Subscriber<HomeJp>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                homeJpCallBack.onError(e.toString());
            }

            @Override
            public void onNext(HomeJp homeJp) {
                homeJpCallBack.onSuccess(homeJp);
            }
        });
    }
}
