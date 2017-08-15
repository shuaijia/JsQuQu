package com.mykj.qupingfang.home.presenter;

import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.contract.HomeContract;
import com.mykj.qupingfang.home.model.HomeModelImpl;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class HomePresenter extends BasePresenter<HomeContract.HomeView> {

    private HomeModelImpl model;

    private HomeContract.HomeView view;

    public HomePresenter(HomeContract.HomeView view) {
        model = new HomeModelImpl();
        this.view = view;
    }

    public void getHomeJp() {
        model.getHomeJp(new HomeContract.HomeJpCallBack() {
            @Override
            public void onSuccess(HomeJp homeJp) {
                view.getHomeJpSuccess(homeJp);
            }

            @Override
            public void onError(String errorMsg) {
                view.getHomeJpError(errorMsg);
            }
        });
    }
}
