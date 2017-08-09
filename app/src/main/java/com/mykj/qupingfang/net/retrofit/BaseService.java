package com.mykj.qupingfang.net.retrofit;

import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.domain.mine.DeleteMyCollection;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Describtion:
 * Created by jia on 2017/6/6.
 * 人之所以能，是相信能
 */
public interface BaseService {

    @GET("index.php?r=site/login&device_type=ad1")
    Observable<Login> loginWithRxjava(@Query("mobile") String name, @Query("password") String pwd);

    @GET("index.php?r=resource/get-resource-by-gradex&device_type=ad1")
    Observable<Lesson> getLesson(@Query("grade_id") String grade_id, @Query("course_type") String course_type,
                                 @Query("page") String page, @Query("size") String size);

    @GET("index.php?r=resource/index-app&device_type=ad1")
    Observable<HomeJp> getHomeJp();

    @GET("index.php?r=resource/resource-app&device_type=ad1")
    Observable<HomeLesson> getHomeLesson(@Query("resource_type") String resource_type, @Query("size") String size,
                                         @Query("page") String page);

    @GET("index.php?r=resource/resource-app&device_type=ad1")
    Observable<HomeSp> getHomeSp(@Query("resource_type") String resource_type, @Query("size") String size,
                                 @Query("page") String page);

    // 获取我的收藏
    @GET("index.php?r=user/my-collections&device_type=ad1")
    Observable<CollectionLog> getCollectionLog(@Query("user_id") String user_id);

    // 删除单个收藏记录
    @GET("index.php?r=resource/un-collection&device_type=ad1")
    Observable<DeleteMyCollection> deleteOneCollection(@Query("user_id") String user_id, @Query("resource_id") String resource_id);

    // 删除单个收藏记录
    @GET("index.php?r=resource/un-collection&device_type=ad1")
    Observable<DeleteMyCollection> deleteAllCollection(@Query("user_id") String user_id, @Query("flag") String flag);
}
