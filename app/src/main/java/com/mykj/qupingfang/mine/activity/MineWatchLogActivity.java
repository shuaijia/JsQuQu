package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.R;

/**
 * Describtion：观看记录界面
 * Created by kangbai on 2017/8/6.
 * 滴水穿石，非一日之功
 */

public class MineWatchLogActivity extends Activity implements View.OnClickListener{

    // 返回键
    private ImageView iv_watch_log_back;
    // 清除
    private TextView tv_watch_log_clear;
    // 数据列表
    private RecyclerView rv_watch_log_content;
    // 无数据界面
    private RelativeLayout rl_collection_no_data;
    // 提示
    private TextView tv_watch_log_no_data;

    // 上下文
    private Context mContext;
    //

    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_watch_log);
        mContext=MineWatchLogActivity.this;

        // 返回键
        iv_watch_log_back= (ImageView) findViewById(R.id.iv_watch_log_back);
        // 清除
        tv_watch_log_clear= (TextView) findViewById(R.id.tv_watch_log_clear);
        // 数据列表
        rv_watch_log_content= (RecyclerView) findViewById(R.id.rv_watch_log_content);
        // 无数据界面
        rl_collection_no_data= (RelativeLayout) findViewById(R.id.rl_collection_no_data);
        // 提示
        tv_watch_log_no_data= (TextView) findViewById(R.id.tv_watch_log_no_data);

        iv_watch_log_back.setOnClickListener(this);
        tv_watch_log_clear.setOnClickListener(this);

        tv_watch_log_clear.setVisibility(View.GONE);
        rl_collection_no_data.setVisibility(View.VISIBLE);
        tv_watch_log_no_data.setText("加载中...");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_watch_log_back:

                finish();

                break;
            case R.id.tv_watch_log_clear:


                break;
        }
    }
}
