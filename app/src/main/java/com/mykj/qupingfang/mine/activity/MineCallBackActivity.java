package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykj.qupingfang.R;

/**
 * Describtion：意见反馈界面
 * Created by kangbai on 2017/8/6.
 * 滴水穿石，非一日之功
 */

public class MineCallBackActivity extends Activity implements View.OnClickListener{

    // 返回键
    private ImageView iv_callback_back;
    // 发表
    private TextView tv_callback_send;
    // 输入框
    private EditText et_callback_content;

    // 上下文
    private Context mContext;
    // 反馈内容
    private String content="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_callback);

        mContext=MineCallBackActivity.this;

        // 返回键
        iv_callback_back= (ImageView) findViewById(R.id.iv_callback_back);
        iv_callback_back.setOnClickListener(this);
        // 发表
        tv_callback_send= (TextView) findViewById(R.id.tv_callback_send);
        tv_callback_send.setOnClickListener(this);
        // 输入框
        et_callback_content= (EditText) findViewById(R.id.et_callback_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_callback_back:

                finish();

                break;
            case R.id.tv_callback_send:

                // 发表意见，发送给服务器，但前提内容满足字数要求

                break;
        }
    }
}
