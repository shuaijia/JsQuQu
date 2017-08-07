package com.mykj.qupingfang.utils;

/**
 * Describtion: 年级转化工具类
 * Created by jia on 2017/8/7.
 * 人之所以能，是相信能
 */
public class GradeUtils {

    public static String IntToGrade(String i){
        String grade="一年级";
        switch (i){
            case "1":
                grade="一年级";
                break;
            case "2":
                grade="二年级";
                break;
            case "3":
                grade="三年级";
                break;
            case "4":
                grade="四年级";
                break;
            case "5":
                grade="五年级";
                break;
            case "6":
                grade="六年级";
                break;
        }
        return grade;
    }
}
