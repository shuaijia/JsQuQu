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
 * 我的收藏界面
 * Created by jia on 2017/8/5.
 */

public class MineCollectionActivity extends Activity implements View.OnClickListener {

    // 返回键
    private ImageView iv_collection_back;
    // 清除
    private TextView tv_collection_clear;
    // 内容列表
    private RecyclerView rv_collection_content;
    // 无数据界面
    private RelativeLayout rl_collection_no_data;
    // 提示
    private TextView tv_collection_no_data;

    // 上下文
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collection);

        mContext = MineCollectionActivity.this;

        // 返回键
        iv_collection_back = (ImageView) findViewById(R.id.iv_collection_back);
        // 清除
        tv_collection_clear = (TextView) findViewById(R.id.tv_collection_clear);
        // 内容列表
        rv_collection_content = (RecyclerView) findViewById(R.id.rv_collection_content);
        // 无数据界面
        rl_collection_no_data = (RelativeLayout) findViewById(R.id.rl_collection_no_data);
        // 提示
        tv_collection_no_data = (TextView) findViewById(R.id.tv_collection_no_data);

        iv_collection_back.setOnClickListener(this);
        tv_collection_clear.setOnClickListener(this);

        tv_collection_clear.setVisibility(View.GONE);
        tv_collection_no_data.setVisibility(View.VISIBLE);
        tv_collection_no_data.setText("加载中");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_collection_back:

                finish();

                break;
            case R.id.tv_collection_clear:


                break;
        }
    }
}
