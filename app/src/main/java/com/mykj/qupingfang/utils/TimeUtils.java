package com.mykj.qupingfang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describtion: 时间工具类
 * Created by jia on 2017/8/7.
 * 人之所以能，是相信能
 */
public class TimeUtils {

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }
}
