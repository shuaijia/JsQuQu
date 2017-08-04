package com.mykj.qupingfang;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.discover.fragment.DiscoverFragment;
import com.mykj.qupingfang.home.fragment.HomeFragment;
import com.mykj.qupingfang.lesson.fragment.LessonFragment;
import com.mykj.qupingfang.mine.fragment.MineFragment;

import java.util.HashMap;

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

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allFindViewById();

        replaceFragment("home");

        Student stu=null;
        HashMap<String,Student> map=new HashMap<String,Student>();
        for(int i=0;i<10;i++){
            stu=new Student("AA",i);
            map.put("aa",stu);
        }

        Log.e("TAG", "onCreate: "+map.toString() );

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
                break;
            case R.id.rl_main_lesson:
                replaceFragment("lesson");
                break;
            case R.id.rl_main_discover:
                replaceFragment("discover");
                break;
            case R.id.rl_main_mine:
                replaceFragment("mine");
                break;
        }
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
    }

    private void replaceFragment(String flag) {
        if (currentFragment != null){
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

    class Student {
        private String name;
        private int age;
        public Student(String name,int age){
            this.name=name;
            this.age=age;
        }
    }
}
