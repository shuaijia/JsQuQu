package com.mykj.qupingfang.net.retrofit;

import com.mykj.qupingfang.domain.login.Login;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Describtion:
 * Created by jia on 2017/6/6.
 * 人之所以能，是相信能
 */
public interface BaseService {

    @GET("index.php?r=site/login&device_type=ad1")
    Observable<Login> loginWithRxjava(@Query("mobile") String name,@Query("password") String pwd);

}
