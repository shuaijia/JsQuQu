package com.mykj.qupingfang.home.presenter;

import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.home.contract.MoreContract;
import com.mykj.qupingfang.home.model.MoreModelImpl;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class MorePresenter extends BasePresenter<MoreContract.MoreView> {

    private MoreModelImpl model;

    private MoreContract.MoreView view;

    public MorePresenter(MoreContract.MoreView view) {
        model = new MoreModelImpl();
        this.view = view;
    }

    public void initLessonData(String resource_type, String size, String page) {
        model.initLessonData(resource_type, size, page, new MoreContract.MoreLessonCallBack() {
            @Override
            public void onSuccess(HomeLesson homeLesson) {
                view.getLessonDataSuccess(homeLesson);
            }

            @Override
            public void onError(String errorMsg) {
                view.getLessonDataError(errorMsg);
            }
        });
    }

    public void initSpData(String resource_type, String size, String page){
        model.initSpData(resource_type, size, page, new MoreContract.MoreSpCallBack() {
            @Override
            public void onSuccess(HomeSp homeSp) {
                view.getSpDataSuccess(homeSp);
            }

            @Override
            public void onError(String errorMsg) {
                view.getSpDataError(errorMsg);
            }
        });
    }
}
