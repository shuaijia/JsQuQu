package com.mykj.qupingfang.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeJpAdapter;
import com.mykj.qupingfang.adapter.home.HomeZjAdapter;
import com.mykj.qupingfang.base.BaseFragment;
import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HomeFragment extends Fragment {
    private Context context;
    private RecyclerView rv_home_kcjp;
    private RecyclerView rv_home_zjgx;
    private List<HomeJp.DataBean.ResourceJpBean> homeJpList;
    private List<HomeJp.DataBean.ResourceZxBean> homeZxList;
    private HomeJpAdapter homeJpAdapter;
    private HomeZjAdapter homeZjAdapter;

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
        rv_home_kcjp = (RecyclerView) view.findViewById(R.id.rv_home_kcjp);
        rv_home_zjgx = (RecyclerView) view.findViewById(R.id.rv_home_zjgx);
        intiData();
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
                homeJpList = homejp.getData().getResource_jp();
                homeJpAdapter = new HomeJpAdapter(context,homeJpList);
                GridLayoutManager layoutManager = new GridLayoutManager(context,2);
                rv_home_kcjp.setLayoutManager(layoutManager);
                rv_home_kcjp.setAdapter(homeJpAdapter);

                homeZxList = homejp.getData().getResource_zx();
                homeZjAdapter = new HomeZjAdapter(context,homeZxList);
                GridLayoutManager layoutManager2 = new GridLayoutManager(context,2);
                rv_home_zjgx.setLayoutManager(layoutManager2);
                rv_home_zjgx.setAdapter(homeZjAdapter);
            }
        });
    }
}
