package com.mykj.qupingfang.lesson.model;

import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.lesson.contract.LessonContract;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import rx.Subscriber;

/**
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class LessonModelImpl implements LessonContract.LessonModel {
    @Override
    public void getLesson(String grade_id, String course_type, String page, String size, final LessonContract.LessonCallBack lessonCallBack) {
        HttpMethod.getInstance().getLesson(grade_id, course_type, page, size, new Subscriber<Lesson>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                lessonCallBack.onError(e.toString());
            }

            @Override
            public void onNext(Lesson lesson) {
                lessonCallBack.onSuccess(lesson);
            }
        });
    }
}
