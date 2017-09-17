package com.mykj.qupingfang.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeListAdapter;
import com.mykj.qupingfang.base.BaseViewActivity;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.home.contract.HomeContract;
import com.mykj.qupingfang.home.presenter.HomePresenter;

public class NewLayoutActivity extends BaseViewActivity<HomeContract.HomeView, HomePresenter> implements HomeContract.HomeView {
    private RecyclerView rv_home_list;

    private HomeListAdapter homeListAdapter;

    private LinearLayoutManager manager;//给RecyclerView设置线性

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_layout);
    }

    @Override
    protected void findViewById() {
        rv_home_list = (RecyclerView) findViewById(R.id.rv_home_list);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void getData() {
        mPresenter.getHomeJp();
    }

    @Override
    public void getHomeJpSuccess(HomeJp homeJp) {
        manager = new LinearLayoutManager(NewLayoutActivity.this);
        homeListAdapter = new HomeListAdapter(NewLayoutActivity.this,homeJp.getData());
        rv_home_list.setAdapter(homeListAdapter);
        rv_home_list.setLayoutManager(manager);
    }

    @Override
    public void getHomeJpError(String errorMsg) {

    }
}
