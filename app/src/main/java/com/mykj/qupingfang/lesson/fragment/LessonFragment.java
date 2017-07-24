package com.mykj.qupingfang.lesson.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.adapter.lesson.LessonAdapter;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.observers.SafeSubscriber;

/**
 * Describtion：
 * Created by kangbai on 2017/7/22.
 * 滴水穿石，非一日之功
 */

public class LessonFragment extends Fragment {

    private RelativeLayout rl_lesson_grade;
    private RelativeLayout rl_lesson_type;
    private TextView tv_lesson_grade;
    private TextView tv_lesson_type;
    private ImageView iv_lesson_grade;
    private ImageView iv_lesson_type;
    private RecyclerView rv_lesson_content;

    private Context context;

    private List<Lesson.DataBean> list = new ArrayList<>();

    private LessonAdapter adapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lesson, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allFindViewById(view);

        initData();
    }

    private void allFindViewById(View view){
        rl_lesson_grade = (RelativeLayout) view.findViewById(R.id.rl_lesson_grade);
        rl_lesson_type = (RelativeLayout) view.findViewById(R.id.rl_lesson_type);
        tv_lesson_grade = (TextView) view.findViewById(R.id.tv_lesson_grade);
        tv_lesson_type = (TextView) view.findViewById(R.id.tv_lesson_type);
        iv_lesson_grade = (ImageView) view.findViewById(R.id.iv_lesson_grade);
        iv_lesson_type = (ImageView) view.findViewById(R.id.iv_lesson_type);
        rv_lesson_content = (RecyclerView) view.findViewById(R.id.rv_lesson_content);
    }

    private void initData() {
        HttpMethod.getInstance().getLesson("0", "0", "1", "8", new Subscriber<Lesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Lesson lesson) {
                list = lesson.getData();
                adapter = new LessonAdapter(context, list);

                GridLayoutManager manager=new GridLayoutManager(context,2);
                rv_lesson_content.setLayoutManager(manager);

                rv_lesson_content.setAdapter(adapter);
            }
        });
    }
}
