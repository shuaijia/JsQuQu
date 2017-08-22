package com.mykj.qupingfang.net.retrofit;

import android.util.Log;

import com.mykj.qupingfang.domain.home.HomeJp;
import com.mykj.qupingfang.domain.home.HomeLesson;
import com.mykj.qupingfang.domain.home.HomeSp;
import com.mykj.qupingfang.domain.lesson.Lesson;
import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.domain.login.SanFangLogin;
import com.mykj.qupingfang.domain.mine.CollectionLog;
import com.mykj.qupingfang.domain.mine.DeleteMyCollection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Describtion:
 * Created by jia on 2017/6/7.
 * 人之所以能，是相信能
 */
public class HttpMethod {

    public static final String TAG = "HttpMethod";

    // 请求超时
    private static final int TIME_OUT = 5;

    private Retrofit retrofit;

    private BaseService service;


    // 私有构造
    private HttpMethod() {

        // 手动创建okhttpclient，并设置超时，添加和保存cookie
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder buidler = okHttpClient.newBuilder();

        // 设置各超时
        buidler.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        buidler.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        buidler.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        // 设置重定向 其实默认也是true
        buidler.followRedirects(true);

        // 保存cookie
        buidler.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                //这里获取请求返回的cookie
                if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                    if (originalResponse.headers("Set-Cookie").size() == 2) {
                        CookieUtils.COOKIE = originalResponse.headers("Set-Cookie").get(1);
                        Log.e(TAG, "intercept: 保存cookie" + CookieUtils.COOKIE);
                    }
                }
                return originalResponse;
            }
        });

        // 添加cookie
        buidler.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                if (null != CookieUtils.COOKIE) {
                    builder.addHeader("Cookie", CookieUtils.COOKIE);
                    Log.e(TAG, "intercept: 添加cookie" + CookieUtils.COOKIE);
                } else {
                    Log.e(TAG, "intercept: 添加空cookie");
                }

                return chain.proceed(builder.build());
            }
        });

        retrofit = new Retrofit.Builder()
                .client(buidler.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(UrlConfig.BASE_URL)
                .build();

        service = retrofit.create(BaseService.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    // 获取单例
    public static HttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getErrorCode().equals("0")) {
                throw new PreDealException(tHttpResult.getErrorMsg());
            }
            return tHttpResult.getData();
        }
    }


    //+++++++++++++++++++++++++++以下为具体网络请求方法++++++++++++++++++++++++++++++++++++++++++

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param subscriber
     */
    public void login(String name, String pwd, Subscriber<Login> subscriber) {
        service.loginWithRxjava(name, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 三方登录
     * @param type
     * @param openid
     * @param nickname
     * @param gender
     * @param headUrl
     * @param subscriber
     */
    public void uploadSanFangUserInfo(String type, String openid, String nickname, String gender, String headUrl, Subscriber<SanFangLogin> subscriber) {
        service.sanFangLoginWithRxjava(type,openid,nickname,gender,headUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取首页精品数据
     *
     * @param subscriber
     */
    public void getHomeJp(Subscriber<HomeJp> subscriber) {
        service.getHomeJp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取课程数据
     *
     * @param grade_id
     * @param course_type
     * @param page
     * @param size
     * @param subscriber
     */
    public void getLesson(String grade_id, String course_type, String page, String size, Subscriber<Lesson> subscriber) {
        service.getLesson(grade_id, course_type, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取首页更多课程数据
     *
     * @param resource_type
     * @param size
     * @param page
     * @param subscriber
     */
    public void getHomeLesson(String resource_type, String size, String page, Subscriber<HomeLesson> subscriber) {
        service.getHomeLesson(resource_type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取首页更多课程数据
     *
     * @param resource_type
     * @param size
     * @param page
     * @param subscriber
     */
    public void getHomeSp(String resource_type, String size, String page, Subscriber<HomeSp> subscriber) {
        service.getHomeSp(resource_type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取收藏记录
     *
     * @param userId
     */
    public void getCollectionLogs(String userId, Subscriber<CollectionLog> subscriber) {
        service.getCollectionLog(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除单个收藏记录
     *
     * @param userId
     */
    public void deleteOneCollection(String userId, String resourceId, Subscriber<DeleteMyCollection> subscriber) {
        service.deleteOneCollection(userId, resourceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除全部收藏记录
     *
     * @param userId
     */
    public void deleteAllCollection(String userId, String flag, Subscriber<DeleteMyCollection> subscriber) {
        service.deleteAllCollection(userId, flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
