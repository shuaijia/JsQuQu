package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.base.BaseViewActivity;

public class MineMessageActivity extends BaseViewActivity {
    private TextView tx_my_title;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_message);

    }

    @Override
    protected void findViewById() {
        tx_my_title = (TextView) findViewById(R.id.tx_my_title);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tx_my_title.setText(title);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void getData() {

    }
}
