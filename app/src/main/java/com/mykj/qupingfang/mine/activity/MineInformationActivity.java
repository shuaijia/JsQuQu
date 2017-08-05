package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.base.BaseViewActivity;

/**
 * Describtion：
 * Created by kangbai on 2017/8/5.
 * 滴水穿石，非一日之功
 */

public class MineInformationActivity extends BaseViewActivity implements View.OnClickListener{
    private TextView tx_my_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_information);
        tx_my_title = (TextView) findViewById(R.id.tx_my_title);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tx_my_title.setText(title);
    }

    @Override
    protected void initActivityView(Bundle savedInstanceState) {

    }

    @Override
    protected void findViewById() {

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
    }
}
