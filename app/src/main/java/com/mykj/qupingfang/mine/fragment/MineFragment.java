package com.mykj.qupingfang.mine.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.mine.activity.MineAccountActivity;
import com.mykj.qupingfang.mine.activity.MineConsumeActivity;
import com.mykj.qupingfang.mine.activity.MineInformationActivity;
import com.mykj.qupingfang.mine.activity.MineMessageActivity;
import com.mykj.qupingfang.mine.activity.MineVersionActivity;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout rl_mine_username;
    private RelativeLayout rl_mine_version;
    private RelativeLayout rl_mine_message;
    private RelativeLayout rl_mine_account;
    private RelativeLayout rl_mine_consume;
    private RelativeLayout rl_mine_cache;
    private RelativeLayout rl_mine_collect;
    private RelativeLayout rl_mine_watch;
    private RelativeLayout rl_mine_reading;
    private RelativeLayout rl_mine_setting;

    private Context context;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rl_mine_username = (RelativeLayout) view.findViewById(R.id.rl_mine_username);
        rl_mine_username.setOnClickListener(this);
        rl_mine_version = (RelativeLayout) view.findViewById(R.id.rl_mine_version);
        rl_mine_version.setOnClickListener(this);
        rl_mine_message = (RelativeLayout) view.findViewById(R.id.rl_mine_message);
        rl_mine_message.setOnClickListener(this);
        rl_mine_account = (RelativeLayout) view.findViewById(R.id.rl_mine_account);
        rl_mine_account.setOnClickListener(this);
        rl_mine_consume = (RelativeLayout) view.findViewById(R.id.rl_mine_consume);
        rl_mine_consume.setOnClickListener(this);
        rl_mine_cache = (RelativeLayout) view.findViewById(R.id.rl_mine_cache);
        rl_mine_cache.setOnClickListener(this);
        rl_mine_collect = (RelativeLayout) view.findViewById(R.id.rl_mine_collect);
        rl_mine_collect.setOnClickListener(this);
        rl_mine_watch = (RelativeLayout) view.findViewById(R.id.rl_mine_watch);
        rl_mine_watch.setOnClickListener(this);
        rl_mine_reading = (RelativeLayout) view.findViewById(R.id.rl_mine_reading);
        rl_mine_reading.setOnClickListener(this);
        rl_mine_setting = (RelativeLayout) view.findViewById(R.id.rl_mine_setting);
        rl_mine_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_mine_username:
                Intent intent = new Intent(context, MineInformationActivity.class);
                intent.putExtra("title","个人信息");
                startActivity(intent);
                break;
            case R.id.rl_mine_version:
                Intent intent1 = new Intent(context, MineVersionActivity.class);
                intent1.putExtra("title","教材版本选择");
                startActivity(intent1);
                break;
            case R.id.rl_mine_message:
                Intent intent2 = new Intent(context, MineMessageActivity.class);
                intent2.putExtra("title","消息");
                startActivity(intent2);
                break;
            case R.id.rl_mine_account:
                Intent intent3 = new Intent(context, MineAccountActivity.class);
                intent3.putExtra("title","账户充值");
                startActivity(intent3);
                break;
            case R.id.rl_mine_consume:
                Intent intent4 = new Intent(context, MineConsumeActivity.class);
                intent4.putExtra("title","消费记录");
                startActivity(intent4);
                break;
        }
    }
}
