package com.mykj.qupingfang.lesson.contract;

import com.mykj.qupingfang.domain.lesson.Lesson;

/**
 * Describtion: Lesson界面的契约类
 * Created by kangbai on 2017/8/15.
 * 滴水穿石，铁杵成针
 */
public class LessonContract {
    public interface LessonView {

        void getLessonSuccess(Lesson lesson);

        void getLessonError(String errorMsg);
    }

    public interface LessonModel {

        void getLesson(String grade_id, String course_type, String page, String size, LessonCallBack lessonCallBack);
    }

    public interface LessonCallBack {

        void onSuccess(Lesson lesson);

        void onError(String errorMsg);
    }
}
