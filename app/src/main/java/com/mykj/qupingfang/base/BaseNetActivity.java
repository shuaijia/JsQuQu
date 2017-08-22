package com.mykj.qupingfang.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

//import com.umeng.analytics.MobclickAgent;


/**
 * 这里是关于Activity的基本的封装类 关于BaseActivity存在两个分支， 1、BaseNetActivity，具有创建的时候访问网络的操作
 * 2、BaseViewActivity，只具有创建的时候初始化界面的操作
 *
 * @author hm
 */
public abstract class BaseNetActivity<V, T extends BasePresenter<V>> extends Activity {

    private boolean openLog = true;
    private String className = getClass().getSimpleName() + "";

    protected T mPresenter;

    // window管理器
    private WindowManager  wm;
    // 屏幕宽度
    private int screenWidth;
    // 屏幕高度
    private int screenHeight;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏

        //淡入淡出
//        getWindow().setEnterTransition(new Fade().setDuration(1000));
//        getWindow().setExitTransition(new Fade().setDuration(1000));
        //分解动画


        initActivityView();

        mPresenter = createPresenter();//创建presenter

        // 图片的宽度
        wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 集成友盟相关的统计API
        //统计应用时长的(也就是Session时长,当然还包括一些其他功能)
//        MobclickAgent.onResume(this);
//        MobclickAgent.onPageStart(getClass().getSimpleName() + "");

        if(null!=mPresenter) {
            mPresenter.attachView((V) this);
        }

        initActivityData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 集成友盟相关的统计API
        //统计应用时长的(也就是Session时长,当然还包括一些其他功能)
//        MobclickAgent.onPause(this);
//        MobclickAgent.onPageStart(getClass().getSimpleName() + "");
    }

    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView();

    /**
     * 关于Activity中数据获取的方法，需要子类必须实现
     */
    protected abstract void initActivityData();

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

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
        if(mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 获取屏幕宽度
     */
    public int getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * 获取屏幕高度
     */
    public int getScreenHeight() {
        return this.screenHeight;
    }

//		@Override
//		public boolean onKeyDown(int keyCode, KeyEvent event) {
//			 
//	        if (keyCode == KeyEvent.KEYCODE_BACK) {
//	             MsgDialogUtils.dismisDialog(getApplicationContext());
//	             Log.e("Tag", "zzzzzzzzzzzzzzzzzzzz");
//	              return true;
//	          }
//	          return super.onKeyDown(keyCode, event);
//	      }

    public void i(String msg) {
        if(openLog){
            Log.i("jia",className+":"+msg);
        }
    }

    public void setOpenLog(boolean openLog){
        this.openLog=openLog;
    }
}
