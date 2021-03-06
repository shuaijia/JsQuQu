package com.mykj.qupingfang.lesson.fragment;

import android.animation.ObjectAnimator;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.mykj.qupingfang.MainActivity;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.lesson.LessonAdapter;
import com.mykj.qupingfang.base.BaseFragment;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.lesson.contract.LessonContract;
import com.mykj.qupingfang.lesson.presenter.LessonPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import rx.Subscriber;

/**
 * Describtion：课程碎片
 * Created by kangbai on 2017/7/22.
 * 滴水穿石，非一日之功
 */

public class LessonFragment extends BaseFragment<LessonContract.LessonView, LessonPresenter> implements LessonContract.LessonView, View.OnClickListener {

    private static final String TAG = "LessonFragment";

    private LinearLayout ll_lesson_choice;
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
    private long classTimer;//年级时间戳
    private long typeTimer;//课程时间戳
    private long versonTimer;//版本时间戳

    private BackgroundAlphaListener backgroundAlphaListener;

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

    @Override
    protected View initFragmentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_lesson, null, false);//渲染Fragment

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
                classTimer = System.currentTimeMillis();
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
                typeTimer = System.currentTimeMillis();
            }
        });
        //版本
        versonView = inflater.inflate(R.layout.popwindow_lession_verson, null);

        versonPopwindow = new PopupWindow(versonView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        versonPopwindow.setOutsideTouchable(true);
        versonPopwindow.setFocusable(false);

        versonPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_lesson_version.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
                iv_lesson_version.setImageResource(R.mipmap.lesson_version_down_sanjiao);
                versonTimer = System.currentTimeMillis();
                backgroundAlphaListener.backgroundAlpha(1f);
            }
        });
        return view;
    }

    @Override
    protected void initFragmentChildView(View view) {
        allFindViewById(view);
        //设置下拉刷新监听事件
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                isRefresh = true;
                pageNum = 1;
                mPresenter.getLesson("0", "0", pageNum + "", pageSize + "");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                isRefresh = false;
                pageNum++;
                mPresenter.getLesson("0", "0", pageNum + "", pageSize + "");
            }
        });
        refreshLayout.startRefresh();
    }

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        context = getActivity();
        backgroundAlphaListener = (MainActivity)context;
        refreshLayout.setHeaderView(new SinaRefreshView(context));
        refreshLayout.setBottomView(new LoadingView(context));

        //设置表格布局，2表示两列
        manager = new GridLayoutManager(context, 2);
        rv_lesson_content.setLayoutManager(manager);
        adapter = new LessonAdapter(context);
        rv_lesson_content.setAdapter(adapter);
    }

    @Override
    protected LessonPresenter createPresenter() {
        return new LessonPresenter(this);
    }

    private void allFindViewById(View view) {
        ll_lesson_choice = (LinearLayout) view.findViewById(R.id.ll_lesson_choice);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_lesson_version:
                if (versonPopwindow.isShowing()) {
                    versonPopwindow.dismiss();
                } else {
                    if ((System.currentTimeMillis() - versonTimer) > 50) {
                        showVersonPopWindow();
                    }
                }
                break;
            case R.id.rl_lesson_grade:
                if (classPopwindow.isShowing()) {
                    classPopwindow.dismiss();
                } else {
                    if ((System.currentTimeMillis() - classTimer) > 50) {
                        showClassPopWindow();
                    }
                }
                break;
            case R.id.rl_lesson_type:
                if (typePopwindow.isShowing()) {
                    typePopwindow.dismiss();
                } else {
                    if ((System.currentTimeMillis() - typeTimer) > 50) {
                        showTypePopWindow();
                    }
                }
                break;
        }
    }

    /**
     * 展示classPopwindow
     */
    public void showClassPopWindow() {
        //tv_lesson_type.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
        //iv_lesson_type.setImageResource(R.mipmap.lesson_version_down_sanjiao);
        tv_lesson_grade.setTextColor(getResources().getColor(R.color.green_title_backgroundcolor));
        iv_lesson_grade.setImageResource(R.mipmap.lesson_version_up_sanjiao);
        classPopwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        classPopwindow.showAsDropDown(ll_lesson_choice);
    }

    /**
     * 展示typePopwindow
     */
    public void showTypePopWindow() {
        //tv_lesson_grade.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
        //iv_lesson_grade.setImageResource(R.mipmap.lesson_version_down_sanjiao);
        tv_lesson_type.setTextColor(getResources().getColor(R.color.green_title_backgroundcolor));
        iv_lesson_type.setImageResource(R.mipmap.lesson_version_up_sanjiao);
        typePopwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        typePopwindow.showAsDropDown(ll_lesson_choice);
    }

    /**
     * 展示versonPopwindow
     */
    public void showVersonPopWindow() {
        //tv_lesson_version.setTextColor(getActivity().getResources().getColor(R.color.gray_text_color));
        //iv_lesson_version.setImageResource(R.mipmap.lesson_version_down_sanjiao);
        tv_lesson_version.setTextColor(getResources().getColor(R.color.green_title_backgroundcolor));
        iv_lesson_version.setImageResource(R.mipmap.lesson_version_up_sanjiao);
        versonPopwindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        versonPopwindow.showAsDropDown(ll_lesson_choice);
    }

    @Override
    public void getLessonSuccess(Lesson lesson) {
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

    @Override
    public void getLessonError(String errorMsg) {
        Toast.makeText(context, "获取课程数据失败" + errorMsg, Toast.LENGTH_SHORT).show();
    }

    public interface BackgroundAlphaListener{
        void backgroundAlpha(float bgAlpha);
    }
}
