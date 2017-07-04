package com.mykj.qupingfang.login.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BaseViewActivity;
import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.login.contract.LoginContract;
import com.mykj.qupingfang.login.presenter.LoginPresenter;

/**
 * Describtion: 登录界面
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginActivity extends BaseViewActivity<LoginContract.LoginView,LoginPresenter> implements LoginContract.LoginView{

    public static final String TAG="LoginActivity";

    private EditText et_loginactivity_phone;

    private EditText et_loginactivity_password;

    private RelativeLayout rl_loginactivity_login;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findViewById() {
        et_loginactivity_phone= (EditText) findViewById(R.id.et_loginactivity_phone);
        et_loginactivity_password= (EditText) findViewById(R.id.et_loginactivity_password);
        rl_loginactivity_login= (RelativeLayout) findViewById(R.id.rl_loginactivity_login);

        mPresenter.login("13693510929","123456");
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    public void loginSuccess(Login login) {
        Log.e(TAG, "loginSuccess: "+login.toString());
    }

    @Override
    public void loginError(String errorMsg) {
        Log.e(TAG, "loginError: "+errorMsg );
    }
}
