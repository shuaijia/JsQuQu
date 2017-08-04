package com.mykj.qupingfang.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mykj.qupingfang.MainActivity;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeMoreAdapter;
import com.mykj.qupingfang.adapter.home.HomeMoreSpAdapter;
import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class MoreActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MoreActivity";

    private Button bt_more_fanhui;//返回按钮
    private TextView tx_more_title;//头部title
    private RecyclerView rv_home_more;
    private TwinklingRefreshLayout tr_more_refresh;//下拉加载布局

    private HomeMoreAdapter homeMoreAdapter;//RecyclerView的课程适配器
    private HomeMoreSpAdapter homeMoreSpAdapter;//RecyclerView的视频适配器
    private List<HomeLesson.DataBean.ResourceBean> lessonList = new ArrayList<>();//网络请求返回的课程数据
    private List<HomeSp.DataBean.FeatureBean> SpList = new ArrayList<>();//网络请求返回的课程数据

    private String title;//标题
    private String type;//视频类型
    private int pageNum = 1;//当前要加载的
    private int pageSize = 8;//每页返回的个数
    private LinearLayoutManager manager;//给RecyclerView设置线性

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        bt_more_fanhui = (Button) findViewById(R.id.bt_more_fanhui);
        bt_more_fanhui.setOnClickListener(this);
        tx_more_title = (TextView) findViewById(R.id.tx_more_title);
        rv_home_more = (RecyclerView) findViewById(R.id.rv_home_more);
        tr_more_refresh = (TwinklingRefreshLayout) findViewById(R.id.tr_more_refresh);

        //设置下拉刷新的头部和尾部
        tr_more_refresh.setHeaderView(new SinaRefreshView(this));//设置为新浪模式
        tr_more_refresh.setBottomView(new LoadingView(this));

        //设置刷新事件
        tr_more_refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                //下拉刷新时走此回调
                pageNum = 1;
                if (title.equals("最近更新")|title.equals("课程精品")){
                    initLessonData();
                }else if (title.equals("视频专题")){
                    initSpData();
                }

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //上拉加载时走此回调
                pageNum++;
                if (title.equals("最近更新")||title.equals("课程精品")){
                    initLessonData();
                }else if (title.equals("视频专题")){
                    initSpData();
                }
            }
        });
        //取出上个activity传来的数据
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        //设置并显示title
        tx_more_title.setText(title);

        type = intent.getStringExtra("type");
        manager = new LinearLayoutManager(MoreActivity.this);

        if (title.equals("最近更新")||title.equals("课程精品")){
            homeMoreAdapter = new HomeMoreAdapter(MoreActivity.this);
            rv_home_more.setAdapter(homeMoreAdapter);
        }else if (title.equals("视频专题")){
            homeMoreSpAdapter = new HomeMoreSpAdapter(MoreActivity.this);
            rv_home_more.setAdapter(homeMoreSpAdapter);
        }
        rv_home_more.setLayoutManager(manager);

        //一进activity就下拉刷新一次
        tr_more_refresh.startRefresh();
    }

    private void initSpData(){
        HttpMethod.getInstance().getHomeSp(type, pageSize + "", pageNum + "", new Subscriber<HomeSp>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.toString());
            }

            @Override
            public void onNext(HomeSp homeSp) {
                if (pageNum == 1) {//pageNum==1表示下拉刷新
                    SpList.clear();//先清空list
                    SpList = homeSp.getData().getFeature();//获得数据
                    tr_more_refresh.finishRefreshing();//结束下拉刷新，让刷新头消失
                } else if (pageNum > 1) {//pageNum>1表示上拉加载
                    SpList.addAll(homeSp.getData().getFeature());//把之后请求的数据加到之前的数据里
                    tr_more_refresh.finishLoadmore();//结束上拉加载，让底部消失
                }
                homeMoreSpAdapter.addData(SpList);//把数据加到adapter里
            }
        });
    }

    private void initLessonData() {

            HttpMethod.getInstance().getHomeLesson(type, pageSize + "", pageNum + "", new Subscriber<HomeLesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.toString());
            }

            @Override
            public void onNext(HomeLesson homeLesson) {
                if (pageNum == 1) {//pageNum==1表示下拉刷新
                    lessonList.clear();//先清空list
                    lessonList = homeLesson.getData().getResource();//获得数据
                    tr_more_refresh.finishRefreshing();//结束下拉刷新，让刷新头消失
                } else if (pageNum > 1) {//pageNum>1表示上拉加载
                    lessonList.addAll(homeLesson.getData().getResource());//把之后请求的数据加到之前的数据里
                    tr_more_refresh.finishLoadmore();//结束上拉加载，让底部消失
                }
                homeMoreAdapter.addData(lessonList);//把数据加到adapter里
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_more_fanhui:
                finish();
        }
    }

    public static void actionMoreActivity(Context context, String title, String type) {
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.setClass(context, MoreActivity.class);
        context.startActivity(intent);
    }
}