package com.mykj.qupingfang.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 这里的BaseActivity是之具有初始化界面操作的Activity 关于BaseActivity存在两个分支，
 * 1、BaseNetActivity，具有创建的时候访问网络的操作 2、BaseViewActivity，只具有创建的时候初始化界面的操作
 * AutoLayoutActivity----->屏幕适配
 *
 * @author hm
 */
public abstract class BaseViewActivity<V, T extends BasePresenter<V>> extends Activity {

    private String className = getClass().getSimpleName() + "";

    protected T mPresenter;
    // window管理器
    private WindowManager wm;
    // 屏幕宽度
    private int screenWidth;
    // 屏幕高度
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
        mPresenter = createPresenter();//创建presenter
        // 图片的宽度
        // 图片的宽度
        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight=wm.getDefaultDisplay().getHeight();

        initActivityView(savedInstanceState);
        findViewById();
        getData();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null!=mPresenter) {
            mPresenter.attachView((V) this);
        }

        resume();
    }



    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);
    /**
     * 加载页面元素
     */
    protected abstract void findViewById();
    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();
    protected abstract void getData();

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(null!=mPresenter) {
            mPresenter.detachView();
        }
        destroy();
    }

    /**
     * 填写需要在onResume()方法中执行的操作
     */
    public void resume(){

    }

    /**
     * 填写需要在onPause()方法中执行的操作
     */
    public void pause(){

    }

    /**
     * 填写需要在onDestroy()方法中执行的操作
     */
    public void destroy(){

    }

    /**
     * 获取屏幕宽度
     */
    public int getScreenWidth(){
        return this.screenWidth;
    }

    /**
     * 获取屏幕高度
     */
    public int getScreenHeight(){
        return this.screenHeight;
    }


}
