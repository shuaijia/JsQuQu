package com.mykj.qupingfang;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.discover.fragment.DiscoverFragment;
import com.mykj.qupingfang.home.fragment.HomeFragment;
import com.mykj.qupingfang.lesson.fragment.LessonFragment;
import com.mykj.qupingfang.mine.fragment.MineFragment;

/**
 * Description: 主界面
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private RelativeLayout rl_main_home;
    private RelativeLayout rl_main_lesson;
    private RelativeLayout rl_main_discover;
    private RelativeLayout rl_main_mine;

    private HomeFragment homeFragment;
    private LessonFragment lessonFragment;
    private DiscoverFragment discoverFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_main_home = (RelativeLayout) findViewById(R.id.rl_main_home);
        rl_main_home.setOnClickListener(this);
        rl_main_lesson = (RelativeLayout) findViewById(R.id.rl_main_lesson);
        rl_main_lesson.setOnClickListener(this);
        rl_main_discover = (RelativeLayout) findViewById(R.id.rl_main_discover);
        rl_main_discover.setOnClickListener(this);
        rl_main_mine = (RelativeLayout) findViewById(R.id.rl_main_mine);
        rl_main_mine.setOnClickListener(this);

        homeFragment = new HomeFragment();
        lessonFragment = new LessonFragment();
        discoverFragment = new DiscoverFragment();
        mineFragment = new MineFragment();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main_home:

                getSupportFragmentManager().beginTransaction().replace(R.id.rl_main_content, homeFragment)
                        .commitAllowingStateLoss();

                break;
            case R.id.rl_main_lesson:

                getSupportFragmentManager().beginTransaction().replace(R.id.rl_main_content, lessonFragment)
                        .commitAllowingStateLoss();

                break;
            case R.id.rl_main_discover:

                getSupportFragmentManager().beginTransaction().replace(R.id.rl_main_content, discoverFragment)
                        .commitAllowingStateLoss();

                break;
            case R.id.rl_main_mine:

                getSupportFragmentManager().beginTransaction().replace(R.id.rl_main_content, mineFragment)
                        .commitAllowingStateLoss();

                break;
        }
    }
}
