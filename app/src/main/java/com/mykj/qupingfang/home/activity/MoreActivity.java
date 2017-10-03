package com.mykj.qupingfang.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mykj.qupingfang.MainActivity;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.home.HomeMoreAdapter;
import com.mykj.qupingfang.adapter.home.HomeMoreSpAdapter;
import com.mykj.qupingfang.base.BaseViewActivity;
import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.home.contract.MoreContract;
import com.mykj.qupingfang.home.presenter.MorePresenter;
import com.mykj.qupingfang.listener.OnRecyclerItemClickListener;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;

public class MoreActivity extends BaseViewActivity<MoreContract.MoreView, MorePresenter> implements MoreContract.MoreView, View.OnClickListener {

    private static final String TAG = "MoreActivity";

    private ImageView bt_more_fanhui;//返回按钮
    private TextView tx_more_title;//头部title
    private RecyclerView rv_home_more;
    private TwinklingRefreshLayout tr_more_refresh;//下拉加载布局

    private Context context = this;

    private HomeMoreAdapter homeMoreAdapter;//RecyclerView的课程适配器
    private HomeMoreSpAdapter homeMoreSpAdapter;//RecyclerView的视频适配器
    private List<HomeLesson.DataBean.ResourceBean> lessonList = new ArrayList<>();//网络请求返回的课程数据
    private List<HomeSp.DataBean.FeatureBean> SpList = new ArrayList<>();//网络请求返回的课程数据

    private String title;//标题
    private String type;//视频类型
    private int pageNum = 1;//当前要加载的
    private int pageSize = 8;//每页返回的个数
    private LinearLayoutManager manager;//给RecyclerView设置线性
    private int adapterFlag = 0;//标志RecyclerView的适配器是哪个，0表示HomeMoreAdapter，1表示HomeMoreSpAdapter

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_more);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.theme_color_primary));
    }

    @Override
    protected void findViewById() {
        bt_more_fanhui = (ImageView) findViewById(R.id.bt_more_fanhui);
        tx_more_title = (TextView) findViewById(R.id.tx_more_title);
        rv_home_more = (RecyclerView) findViewById(R.id.rv_home_more);
        tr_more_refresh = (TwinklingRefreshLayout) findViewById(R.id.tr_more_refresh);

        setSomeStyle();
    }

    @Override
    protected MorePresenter createPresenter() {
        return new MorePresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_more_fanhui:
                finish();
                this.overridePendingTransition(0, R.anim.activity_close);
        }
    }

    @Override
    public void getLessonDataSuccess(HomeLesson homeLesson) {
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

    @Override
    public void getLessonDataError(String errorMsg) {
        Toast.makeText(this, "获取课程数据失败" + errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSpDataSuccess(HomeSp homeSp) {
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

    @Override
    public void getSpDataError(String errorMsg) {
        Toast.makeText(this, "获取视频数据失败" + errorMsg, Toast.LENGTH_SHORT).show();
    }

    public void setSomeStyle() {
        bt_more_fanhui.setOnClickListener(this);
        //设置下拉刷新的头部和尾部
        tr_more_refresh.setHeaderView(new SinaRefreshView(this));//设置为新浪模式
        tr_more_refresh.setBottomView(new LoadingView(this));

        //设置刷新事件
        tr_more_refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                //下拉刷新时走此回调
                pageNum = 1;
                if (title.equals("最近更新") | title.equals("课程精品")) {
                    mPresenter.initLessonData(type, pageSize + "", pageNum + "");
                } else if (title.equals("视频专题")) {
                    mPresenter.initSpData(type, pageSize + "", pageNum + "");
                }

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //上拉加载时走此回调
                pageNum++;
                if (title.equals("最近更新") || title.equals("课程精品")) {
                    mPresenter.initLessonData(type, pageSize + "", pageNum + "");
                } else if (title.equals("视频专题")) {
                    mPresenter.initSpData(type, pageSize + "", pageNum + "");
                }
            }
        });

        setTitleAndRv();

        //一进activity就下拉刷新一次
        tr_more_refresh.startRefresh();
    }

    public void setTitleAndRv() {
        //取出上个activity传来的数据
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        //设置并显示title
        tx_more_title.setText(title);

        type = intent.getStringExtra("type");
        manager = new LinearLayoutManager(MoreActivity.this);

        if (title.equals("最近更新") || title.equals("课程精品")) {
            homeMoreAdapter = new HomeMoreAdapter(MoreActivity.this);
            rv_home_more.setAdapter(homeMoreAdapter);
        } else if (title.equals("视频专题")) {
            homeMoreSpAdapter = new HomeMoreSpAdapter(MoreActivity.this);
            rv_home_more.setAdapter(homeMoreSpAdapter);
        }
        rv_home_more.setLayoutManager(manager);

        //给RecyclerView设置触摸监听
        rv_home_more.addOnItemTouchListener(new OnRecyclerItemClickListener(rv_home_more) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                if (title.equals("最近更新") || title.equals("课程精品")) {
                    Toast.makeText(context, lessonList.get(viewHolder.getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                } else if (title.equals("视频专题")) {
                    Toast.makeText(context, SpList.get(viewHolder.getPosition()).getImg_name(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                if (title.equals("最近更新") || title.equals("课程精品")) {
                    Toast.makeText(context, "长按了" + lessonList.get(viewHolder.getPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                } else if (title.equals("视频专题")) {
                    Toast.makeText(context, "长按了" + SpList.get(viewHolder.getPosition()).getImg_name(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //主要就要使用到 ItemTouchHelper，ItemTouchHelper 一个帮助开发人员处理拖拽和滑动删除的实现类，它能够让你非常容易实现侧滑删除、拖拽的功能。
        ItemTouchHelper itemTouchHelper
                = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            //getMovementFlags() 用于设置是否处理拖拽事件和滑动事件，以及拖拽和滑动操作的方向
            //如果是列表类型的 RecyclerView，拖拽只有 UP、DOWN 两个方向
            //如果是网格类型的则有 UP、DOWN、LEFT、RIGHT 四个方向
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //拖动的item的下标
                int fromPosition = viewHolder.getAdapterPosition();
                //目标item的下标，目标item就是当拖拽过程中，不断和拖动的item做位置交换的条目
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        if (title.equals("最近更新") || title.equals("课程精品")) {
                            Collections.swap(lessonList, i, i + 1);
                        } else if (title.equals("视频专题")) {
                            Collections.swap(SpList, i, i + 1);
                        }
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        if (title.equals("最近更新") || title.equals("课程精品")) {
                            Collections.swap(lessonList, i, i - 1);
                        } else if (title.equals("视频专题")) {
                            Collections.swap(SpList, i, i + 1);
                        }
                    }
                }
                if (title.equals("最近更新") || title.equals("课程精品")) {
                    homeMoreAdapter.notifyItemMoved(fromPosition,toPosition);
                } else if (title.equals("视频专题")) {
                    homeMoreSpAdapter.notifyItemMoved(fromPosition,toPosition);
                }
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(rv_home_more);
    }

    /**
     * @param context
     * @param title
     * @param type
     */
    public static void actionMoreActivity(Context context, String title, String type) {
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.setClass(context, MoreActivity.class);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.activity_open, 0);
    }
}
