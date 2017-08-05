package com.mykj.qupingfang.mine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.base.BaseViewActivity;

public class MineConsumeActivity extends BaseViewActivity {
    private TextView tx_my_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_consume);
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
}
