package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.base.BaseViewActivity;

public class MineAccountActivity extends BaseViewActivity implements View.OnClickListener{
    private TextView tx_my_title;
    private RadioButton tv_mine_account_30;
    private RadioButton tv_mine_account_100;
    private RadioButton tv_mine_account_300;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_account);

    }

    @Override
    protected void findViewById() {
        tx_my_title = (TextView) findViewById(R.id.tx_my_title);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tx_my_title.setText(title);

        tv_mine_account_30 = (RadioButton) findViewById(R.id.tv_mine_account_30);
        tv_mine_account_100= (RadioButton) findViewById(R.id.tv_mine_account_100);
        tv_mine_account_300= (RadioButton) findViewById(R.id.tv_mine_account_300);
        tv_mine_account_30.setOnClickListener(this);
        tv_mine_account_100.setOnClickListener(this);
        tv_mine_account_300.setOnClickListener(this);
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
            case R.id.tv_mine_account_30:
                Toast.makeText(this,"充30先！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_mine_account_100:
                Toast.makeText(this,"充100先！",Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_mine_account_300:
                Toast.makeText(this,"充300先！",Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
