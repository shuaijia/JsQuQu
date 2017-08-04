package com.mykj.qupingfang;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.discover.fragment.DiscoverFragment;
import com.mykj.qupingfang.home.fragment.HomeFragment;
import com.mykj.qupingfang.lesson.fragment.LessonFragment;
import com.mykj.qupingfang.mine.fragment.MineFragment;

import org.zackratos.ultimatebar.UltimateBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private ImageView iv_main_home;
    private TextView tv_main_home;
    private ImageView iv_main_lesson;
    private TextView tv_main_lesson;
    private ImageView iv_main_discover;
    private TextView tv_main_discover;
    private ImageView iv_main_mine;
    private TextView tv_main_mine;

    private Fragment currentFragment;

    private List<ImageView> imageViewList = new ArrayList<>();
    private List<TextView> textViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        allFindViewById();

        replaceFragment("home");
        setColor(iv_main_home, tv_main_home);
    }

    private void allFindViewById() {
        rl_main_home = (RelativeLayout) findViewById(R.id.rl_main_home);
        rl_main_home.setOnClickListener(this);
        rl_main_lesson = (RelativeLayout) findViewById(R.id.rl_main_lesson);
        rl_main_lesson.setOnClickListener(this);
        rl_main_discover = (RelativeLayout) findViewById(R.id.rl_main_discover);
        rl_main_discover.setOnClickListener(this);
        rl_main_mine = (RelativeLayout) findViewById(R.id.rl_main_mine);
        rl_main_mine.setOnClickListener(this);

        iv_main_home = (ImageView) findViewById(R.id.iv_main_home);
        imageViewList.add(iv_main_home);
        tv_main_home = (TextView) findViewById(R.id.tv_main_home);
        textViewList.add(tv_main_home);
        iv_main_lesson = (ImageView) findViewById(R.id.iv_main_lesson);
        imageViewList.add(iv_main_lesson);
        tv_main_lesson = (TextView) findViewById(R.id.tv_main_lesson);
        textViewList.add(tv_main_lesson);
        iv_main_discover = (ImageView) findViewById(R.id.iv_main_discover);
        imageViewList.add(iv_main_discover);
        tv_main_discover = (TextView) findViewById(R.id.tv_main_discover);
        textViewList.add(tv_main_discover);
        iv_main_mine = (ImageView) findViewById(R.id.iv_main_mine);
        imageViewList.add(iv_main_mine);
        tv_main_mine = (TextView) findViewById(R.id.tv_main_mine);
        textViewList.add(tv_main_mine);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_main_home:
                replaceFragment("home");
                setColor(iv_main_home, tv_main_home);
                break;
            case R.id.rl_main_lesson:
                replaceFragment("lesson");
                setColor(iv_main_lesson, tv_main_lesson);
                break;
            case R.id.rl_main_discover:
                replaceFragment("discover");
                setColor(iv_main_discover, tv_main_discover);
                break;
            case R.id.rl_main_mine:
                replaceFragment("mine");
                setColor(iv_main_mine, tv_main_mine);
                break;
        }
    }

    private void replaceFragment(String flag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(flag);
        if (currentFragment == null) {
            switch (flag) {
                case "home":
                    currentFragment = new HomeFragment();
                    break;
                case "lesson":
                    currentFragment = new LessonFragment();
                    break;
                case "discover":
                    currentFragment = new DiscoverFragment();
                    break;
                case "mine":
                    currentFragment = new MineFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.rl_main_content, currentFragment, flag)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        }
    }

    public void setColor(ImageView imageView, TextView textView) {
        for (ImageView iv : imageViewList) {
            if (iv == imageView) {
                iv.setBackgroundColor(Color.parseColor("#00b763"));
            } else {
                iv.setBackgroundColor(Color.parseColor("#a0a0a0"));
            }
        }
        for (TextView tv : textViewList) {
            if (tv == textView) {
                tv.setTextColor(Color.parseColor("#00b763"));
            } else {
                tv.setTextColor(Color.parseColor("#a0a0a0"));
            }
        }
    }
}
