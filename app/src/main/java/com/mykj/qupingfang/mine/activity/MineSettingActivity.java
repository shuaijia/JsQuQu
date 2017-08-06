package com.mykj.qupingfang.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.utils.DataCleanManager;
import com.mykj.qupingfang.utils.ToastUtils;

/**
 * Describtion： 设置界面
 * Created by kangbai on 2017/8/6.
 * 滴水穿石，非一日之功
 */

public class MineSettingActivity extends Activity implements View.OnClickListener {

    // 返回键
    private ImageView iv_setting_back;
    // 清理缓存
    private RelativeLayout rl_mine_clear_cache;
    // 缓存大小
    private TextView tv_mine_cache_size;
    // 意见反馈
    private RelativeLayout rl_mine_yjfk;
    // 版本号
    private TextView tv_mine_version;
    // 退出登录
    private TextView tv_mine_logout;

    // 上下文
    private Context mContext;

    // 缓存大小
    private String cacheSize = "0k";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        mContext = MineSettingActivity.this;

        // 返回键
        iv_setting_back = (ImageView) findViewById(R.id.iv_setting_back);
        iv_setting_back.setOnClickListener(this);
        // 清理缓存
        rl_mine_clear_cache = (RelativeLayout) findViewById(R.id.rl_mine_clear_cache);
        rl_mine_clear_cache.setOnClickListener(this);
        // 缓存大小
        tv_mine_cache_size = (TextView) findViewById(R.id.tv_mine_cache_size);
        // 意见反馈
        rl_mine_yjfk = (RelativeLayout) findViewById(R.id.rl_mine_yjfk);
        rl_mine_yjfk.setOnClickListener(this);
        // 版本号
        tv_mine_version = (TextView) findViewById(R.id.tv_mine_version);
        // 退出登录
        tv_mine_logout = (TextView) findViewById(R.id.tv_mine_logout);
        tv_mine_logout.setOnClickListener(this);

        // 设置版本号
        tv_mine_version.setText(getAppVersionName(mContext));


        try {
            cacheSize = DataCleanManager.getTotalCacheSize(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_mine_cache_size.setText(cacheSize);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_setting_back:

                finish();

                break;
            case R.id.rl_mine_clear_cache:

                if(!cacheSize.equals("0k")){
                    DataCleanManager.clearAllCache(mContext);
                    ToastUtils.showToastSafe(mContext,"已清理"+cacheSize+"缓存数据");
                }

                break;
            case R.id.rl_mine_yjfk:

                startActivity(new Intent(mContext,MineCallBackActivity.class));

                break;
            case R.id.tv_mine_logout:

                //  退出操作，请求退出，成功跳转主界面

                break;
        }
    }

    /**
     *  
     *  返回当前程序版本名 
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }
}
