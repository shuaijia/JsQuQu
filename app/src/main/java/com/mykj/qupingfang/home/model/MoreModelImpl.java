package com.mykj.qupingfang.home.model;

import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.home.contract.MoreContract;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import rx.Subscriber;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MoreModelImpl implements MoreContract.MoreModel {
    @Override
    public void initLessonData(String resource_type, String size, String page, final MoreContract.MoreLessonCallBack moreCallBack) {
        HttpMethod.getInstance().getHomeLesson(resource_type, size, page, new Subscriber<HomeLesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                moreCallBack.onError(e.toString());
            }

            @Override
            public void onNext(HomeLesson homeLesson) {
                moreCallBack.onSuccess(homeLesson);
            }
        });
    }

    @Override
    public void initSpData(String resource_type, String size, String page, final MoreContract.MoreSpCallBack moreSpCallBack) {
        HttpMethod.getInstance().getHomeSp(resource_type, size, page, new Subscriber<HomeSp>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                moreSpCallBack.onError(e.toString());
            }

            @Override
            public void onNext(HomeSp homeSp) {
                moreSpCallBack.onSuccess(homeSp);
            }
        });
    }
}
