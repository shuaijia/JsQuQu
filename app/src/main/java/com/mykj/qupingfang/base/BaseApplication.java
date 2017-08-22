package com.mykj.qupingfang.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.mykj.qupingfang.utils.NetStateUtils;
//import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * 这里是本应用的最底层的Application的封装类
 * 简单解释：BaseApplication在清单文件中已经配置了，应用运行的时候首先会运行这个Application，这个时候根据
 * 线程的管理，会生成一个主线程，Application运行在主线程中
 * @author jia
 */
public class BaseApplication extends Application{

    //应用的上下文
    private static Application mContext;
    //主线程
    private static Thread mMainThread;
    //主线程ID
    private static int mMainThreadID = -1;
    //主线程中的Looper
    private static Looper mMainThreadLooper;
    //主线程的Handler
    private static Handler mMainThreadHandler;

    // 当前网络状态
    public static int mNetWorkState;

    // 是否使用流量上网
    private static boolean isLiuliang;

    {

        PlatformConfig.setWeixin("wx06e6b6cb31893565", "1de3c150dab75bde08fface065ad4f00");
        PlatformConfig.setQQZone("1104942086", "2c92cb048198bff3d77fec5d657da9cc");
        PlatformConfig.setSinaWeibo("3185408770", "2c92cb048198bff3d77fec5d657da9cc", "https://api.weibo.com/oauth2/default.html");
    }


    @Override
    public void onCreate() {
        super.onCreate();

        this.mContext = this;
        this.mMainThread = Thread.currentThread();
        this.mMainThreadID = android.os.Process.myTid();
        this.mMainThreadLooper = getMainLooper();
        this.mMainThreadHandler = new Handler();

        // 禁止默认的页面统计方式，这样将不会再自动统计Activity,需手动添加
//        MobclickAgent.openActivityDurationTrack(false);
        //友盟分享
        UMShareAPI.get(this);

        initNetState();
    }

    private void initNetState() {
        mNetWorkState = NetStateUtils.getNetworkState(this);
    }



    /**
     * 下面的五个方法是关于定义的成员变量的获取的方法,为工具了进行封装提供方便
     */

    public static Application getMainContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMaiThredId() {
        return mMainThreadID;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Handler getMainHandler() {
        return mMainThreadHandler;
    }

    public static boolean isLiuliang() {
        return isLiuliang;
    }

    public static void setLiuliang(boolean isLiuliang) {
        BaseApplication.isLiuliang = isLiuliang;
    }

}
