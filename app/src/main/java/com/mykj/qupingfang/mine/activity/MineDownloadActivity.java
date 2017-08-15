package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.base.BaseViewActivity;

public class MineDownloadActivity extends BaseViewActivity implements View.OnClickListener{

    // 返回键
    private ImageView iv_download_back;
    // 清除
    private TextView tv_download_clear;
    // 列表
    private RecyclerView rv_download_content;
    // 无数据界面
    private RelativeLayout rl_download_no_data;
    // 提示
    private TextView tv_download_no_data;

    // 上下文
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_download);

    }

    @Override
    protected void findViewById() {
// 返回键
        iv_download_back= (ImageView) findViewById(R.id.iv_download_back);
        // 清除
        tv_download_clear= (TextView) findViewById(R.id.tv_download_clear);
        // 列表
        rv_download_content= (RecyclerView) findViewById(R.id.rv_download_content);
        // 无数据界面
        rl_download_no_data= (RelativeLayout) findViewById(R.id.rl_download_no_data);
        // 提示
        tv_download_no_data= (TextView) findViewById(R.id.tv_download_no_data);

        iv_download_back.setOnClickListener(this);
        tv_download_clear.setOnClickListener(this);

        tv_download_clear.setVisibility(View.GONE);
        rl_download_no_data.setVisibility(View.VISIBLE);
        tv_download_no_data.setText("加载中");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_download_back:

                finish();

                break;
            case R.id.tv_download_clear:


                break;
        }
    }
}
