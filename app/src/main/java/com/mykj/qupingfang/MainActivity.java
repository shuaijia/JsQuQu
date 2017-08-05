package com.mykj.qupingfang;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
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

    private Fragment currentFragment;//用于存放当前展示在最前面的Fragment

    private List<ImageView> imageViewList = new ArrayList<>();//用于存放导航栏中的ImageView
    private List<TextView> textViewList = new ArrayList<>();//用于存放导航栏中的TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.theme_color_primary));

        allFindViewById();

        replaceFragment("home");
        setColor(iv_main_home, tv_main_home);
    }
/**
 * 将所有的View都实例化findViewById
 * */
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
    /**
     * 根据flag决定展示在最前面的Fragment
     * 注意:事务一定要commit()
     * */
    private void replaceFragment(String flag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();//隐藏当前Fragment
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(flag);//通过flag找到要展示在最前面的Fragment
        if (currentFragment == null) {//如果找不到想要展示的Fragment，则根据flag去创建具体的Fragment
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
                    .commit();//将创建好的Fragment加入到管理类中并提交，注意:一定要commit()
        } else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();//展示找到的Fragment
        }
    }
    /**
     * 用于改变下方导航栏图标底色
     * imageView表示被点击的图标
     * textView表示被点击的字
     * */
    public void setColor(ImageView imageView, TextView textView) {
        for (ImageView iv : imageViewList) {
            if (iv == imageView) {
                iv.setBackgroundColor(Color.parseColor("#00b763"));//被点击的设置为绿色
            } else {
                iv.setBackgroundColor(Color.parseColor("#a0a0a0"));//其他的设置为灰色
            }
        }
        for (TextView tv : textViewList) {
            if (tv == textView) {
                tv.setTextColor(Color.parseColor("#00b763"));//被点击的设置为绿色
            } else {
                tv.setTextColor(Color.parseColor("#a0a0a0"));//其他的设置为灰色
            }
        }
    }
}
