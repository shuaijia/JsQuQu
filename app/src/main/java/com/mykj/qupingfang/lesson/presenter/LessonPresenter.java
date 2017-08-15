package com.mykj.qupingfang.lesson.presenter;

import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.lesson.contract.LessonContract;
import com.mykj.qupingfang.lesson.model.LessonModelImpl;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class LessonPresenter extends BasePresenter<LessonContract.LessonView> {

    private LessonModelImpl model;

    private LessonContract.LessonView view;

    public LessonPresenter(LessonContract.LessonView view) {
        model = new LessonModelImpl();
        this.view = view;
    }

    public void getLesson(String grade_id, String course_type, String page, String size) {
        model.getLesson(grade_id, course_type, page, size, new LessonContract.LessonCallBack() {
            @Override
            public void onSuccess(Lesson lesson) {
                view.getLessonSuccess(lesson);
            }

            @Override
            public void onError(String errorMsg) {
                view.getLessonError(errorMsg);
            }
        });
    }
}
