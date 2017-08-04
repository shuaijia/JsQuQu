package com.mykj.qupingfang;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykj.qupingfang.login.activity.LoginActivity;

/**
 * Description: 启动页
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class SplashActivity extends Activity{

    private ImageView iv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_splash= (ImageView) findViewById(R.id.iv_splash);

        // 设置属性动画  渐变
        ObjectAnimator anim = ObjectAnimator.ofFloat(iv_splash, "alpha", 1.0f, 0f);
        anim.setDuration(3000);// 动画持续时间
//        anim.setRepeatCount(3);
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束，跳转引导页
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
