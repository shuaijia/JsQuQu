package com.mykj.qupingfang.home.contract;

import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;

/**
 * Describtion: 更多界面的契约类
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MoreContract {

    public interface MoreView {

        void getLessonDataSuccess(HomeLesson homeLesson);

        void getLessonDataError(String errorMsg);

        void getSpDataSuccess(HomeSp homeSp);

        void getSpDataError(String errorMsg);
    }

    public interface MoreModel {
        void initLessonData(String resource_type, String size, String page, MoreLessonCallBack moreCallBack);

        void initSpData(String resource_type, String size, String page,MoreSpCallBack moreSpCallBack);
    }

    public interface MoreLessonCallBack {

        void onSuccess(HomeLesson homeLesson);

        void onError(String errorMsg);
    }

    public interface MoreSpCallBack {

        void onSuccess(HomeSp homeSp);

        void onError(String errorMsg);
    }
}
