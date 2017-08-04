package com.mykj.qupingfang.lesson.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.lesson.LessonAdapter;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Describtion：
 * Created by kangbai on 2017/7/22.
 * 滴水穿石，非一日之功
 */

public class LessonFragment extends Fragment {

    private static final String TAG = "LessonFragment";

    private RelativeLayout rl_lesson_grade;
    private RelativeLayout rl_lesson_type;
    private TextView tv_lesson_grade;
    private TextView tv_lesson_type;
    private ImageView iv_lesson_grade;
    private ImageView iv_lesson_type;
    private RecyclerView rv_lesson_content;
    private TwinklingRefreshLayout refreshLayout;//下拉加载布局

    private Context context;//Activity上下文

    private List<Lesson.DataBean> list = new ArrayList<>();//数据源

    private LessonAdapter adapter;
    private int pageNum = 1;//当前要加载的
    private int pageSize = 8;//每页返回的个数
    private boolean isRefresh = true;//判断是否是下拉刷新
    private GridLayoutManager manager;//设置RecyclerView的表格布局

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        refreshLayout.setHeaderView(new SinaRefreshView(context));
        refreshLayout.setBottomView(new LoadingView(context));

        //设置表格布局，2表示两列
        manager = new GridLayoutManager(context, 2);
        rv_lesson_content.setLayoutManager(manager);
        adapter = new LessonAdapter(context);
        rv_lesson_content.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lesson, container, false);//渲染Fragment
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFindViewById(view);

        //设置下拉刷新监听事件
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                isRefresh = true;
                pageNum = 1;
                initData();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                isRefresh = false;
                pageNum++;
                initData();
            }
        });
        refreshLayout.startRefresh();
    }

    private void allFindViewById(View view) {
        rl_lesson_grade = (RelativeLayout) view.findViewById(R.id.rl_lesson_grade);
        rl_lesson_type = (RelativeLayout) view.findViewById(R.id.rl_lesson_type);
        tv_lesson_grade = (TextView) view.findViewById(R.id.tv_lesson_grade);
        tv_lesson_type = (TextView) view.findViewById(R.id.tv_lesson_type);
        iv_lesson_grade = (ImageView) view.findViewById(R.id.iv_lesson_grade);
        iv_lesson_type = (ImageView) view.findViewById(R.id.iv_lesson_type);
        rv_lesson_content = (RecyclerView) view.findViewById(R.id.rv_lesson_content);
        refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setEnableOverScroll(false);

    }

    private void initData() {
        HttpMethod.getInstance().getLesson("0", "0", pageNum + "", pageSize + "", new Subscriber<Lesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Lesson lesson) {
                if (isRefresh) {
                    list.clear();
                    list.addAll(lesson.getData());
                    refreshLayout.finishRefreshing();
                } else {
                    list.addAll(lesson.getData());
                    refreshLayout.finishLoadmore();
                }

                adapter.addData(list);
            }
        });
    }
}
