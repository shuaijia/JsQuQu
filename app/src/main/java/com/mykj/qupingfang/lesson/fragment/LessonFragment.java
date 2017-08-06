package com.mykj.qupingfang.lesson.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
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

public class LessonFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "LessonFragment";

    private RelativeLayout rl_lesson_version;
    private RelativeLayout rl_lesson_grade;
    private RelativeLayout rl_lesson_type;
    private TextView tv_lesson_version;
    private TextView tv_lesson_grade;
    private TextView tv_lesson_type;
    private ImageView iv_lesson_version;
    private ImageView iv_lesson_grade;
    private ImageView iv_lesson_type;
    private RecyclerView rv_lesson_content;
    private TwinklingRefreshLayout refreshLayout;//下拉加载布局

    //年级popwindow
    private View classView;
    private PopupWindow classPopwindow;
    private Button bt_class_one;
    //课程popwindow
    private View typeView;
    private PopupWindow typePopwindow;
    private Button bt_lession_yuwen;
    //课程popwindow
    private View versonView;
    private PopupWindow versonPopwindow;

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
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);//渲染Fragment

        //年级
        // 设置popwindow填充的view
        classView = inflater.inflate(R.layout.popwindow_lession_class, null);
        bt_class_one = (Button) classView.findViewById(R.id.bt_class_one);
        bt_class_one.setOnClickListener(this);
        classPopwindow = new PopupWindow(classView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        classPopwindow.setOutsideTouchable(true);
        classPopwindow.setFocusable(false);
        classPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_lesson_grade.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
                iv_lesson_grade.setImageResource(R.mipmap.lesson_version_down_sanjiao);
                classPopwindow.setFocusable(true);
            }
        });
        //全部课程
        typeView = inflater.inflate(R.layout.popwindow_lession_type, null);

        typePopwindow = new PopupWindow(typeView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        typePopwindow.setOutsideTouchable(true);
        typePopwindow.setFocusable(false);
        typePopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_lesson_type.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
                iv_lesson_type.setImageResource(R.mipmap.lesson_version_down_sanjiao);
            }
        });
        //版本
        //versonView = inflater.inflate(R.layout.p)

        versonPopwindow = new PopupWindow(typeView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        versonPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_lesson_version.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
                iv_lesson_version.setImageResource(R.mipmap.lesson_version_down_sanjiao);
            }
        });
        return view;
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
        rl_lesson_version = (RelativeLayout) view.findViewById(R.id.rl_lesson_version);
        rl_lesson_version.setOnClickListener(this);
        rl_lesson_grade = (RelativeLayout) view.findViewById(R.id.rl_lesson_grade);
        rl_lesson_grade.setOnClickListener(this);
        rl_lesson_type = (RelativeLayout) view.findViewById(R.id.rl_lesson_type);
        rl_lesson_type.setOnClickListener(this);
        tv_lesson_version = (TextView) view.findViewById(R.id.tv_lesson_version);
        tv_lesson_grade = (TextView) view.findViewById(R.id.tv_lesson_grade);
        tv_lesson_type = (TextView) view.findViewById(R.id.tv_lesson_type);
        iv_lesson_version = (ImageView) view.findViewById(R.id.iv_lesson_version);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_lesson_version:

                break;
            case R.id.rl_lesson_grade:
                if (classPopwindow.isShowing()) {
                    classPopwindow.dismiss();
                } else {
                    showClassPopWindow();
                }
                break;
            case R.id.rl_lesson_type:
                if (typePopwindow.isShowing()) {
                    typePopwindow.dismiss();
                } else {
                    showTypePopWindow();
                }
                break;
        }
    }

    /**
     * 展示classPopwindow
     */
    public void showClassPopWindow() {
        tv_lesson_type.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
        iv_lesson_type.setImageResource(R.mipmap.lesson_version_down_sanjiao);
        tv_lesson_grade.setTextColor(getResources().getColor(R.color.green_title_backgroundcolor));
        iv_lesson_grade.setImageResource(R.mipmap.lesson_version_up_sanjiao);
        classPopwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        classPopwindow.showAsDropDown(rl_lesson_version);
    }

    /**
     * 展示classPopwindow
     */
    public void showTypePopWindow() {
        tv_lesson_grade.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
        iv_lesson_grade.setImageResource(R.mipmap.lesson_version_down_sanjiao);
        tv_lesson_type.setTextColor(getResources().getColor(R.color.green_title_backgroundcolor));
        iv_lesson_type.setImageResource(R.mipmap.lesson_version_up_sanjiao);
        typePopwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        typePopwindow.showAsDropDown(rl_lesson_version);
    }
}
