package com.mykj.qupingfang.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mykj.qupingfang.MainActivity;
import com.mykj.qupingfang.R;
import com.mykj.qupingfang.base.BaseViewActivity;
import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.domain.login.SanFangLogin;
import com.mykj.qupingfang.login.contract.LoginContract;
import com.mykj.qupingfang.login.presenter.LoginPresenter;
import com.mykj.qupingfang.utils.ToastUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Describtion: 登录界面
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginActivity extends BaseViewActivity<LoginContract.LoginView, LoginPresenter> implements LoginContract.LoginView,
        View.OnClickListener {

    public static final String TAG = "LoginActivity";
    //手机号编辑框
    private EditText et_loginactivity_phone;
    //密码编辑框
    private EditText et_loginactivity_password;
    //登录按钮
    private RelativeLayout rl_loginactivity_login;
    //微信登录
    private ImageButton ib_loginactivity_wx;
    //手机号
    private String phoneNum;
    //密码
    private String psw;
    //友盟三方登录
    private UMShareAPI mShareAPI;
    // 第三方类型
    private String type;
    // 第三方唯一标识
    private String open_id;
    // 第三方昵称
    private String nickname;
    // 性别---男为1/女为2
    private String sex = "";
    // 头像url
    private String avatar_url;

    @Override
    protected void initActivityView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findViewById() {
        et_loginactivity_phone = (EditText) findViewById(R.id.et_loginactivity_phone);
        et_loginactivity_password = (EditText) findViewById(R.id.et_loginactivity_password);
        rl_loginactivity_login = (RelativeLayout) findViewById(R.id.rl_loginactivity_login);
        rl_loginactivity_login.setOnClickListener(this);

        //mPresenter.login("13693510929","123456");

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
        Log.e(TAG, "loginSuccess: " + login.toString());
        ToastUtils.showToastSafe(this,"登录成功");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginError(String errorMsg) {
        Log.e(TAG, "loginError: " + errorMsg);
        ToastUtils.showToastSafe(this,"登录失败"+errorMsg);
    }

    @Override
    public void uploadSuccess(SanFangLogin result) {
        ToastUtils.showToastSafe(this,result.toString());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void uploadFail(SanFangLogin result) {
        ToastUtils.showToastSafe(this,result.toString());
    }

    @Override
    public void uploadNoNet(String eerorMsg) {
        ToastUtils.showToastSafe(this,eerorMsg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_loginactivity_login:
                phoneNum = et_loginactivity_phone.getText().toString().trim();
                psw = et_loginactivity_password.getText().toString().trim();
                mPresenter.login(phoneNum, psw);
                break;
            case R.id.ib_loginactivity_wx:
                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
        }
    }

    //三方授权回调
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            //授权成功后获取信息
            mShareAPI.getPlatformInfo(LoginActivity.this, share_media, umListener);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            //DialogUtils.showDismissDialog(mContext, R.mipmap.kulian, "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            //DialogUtils.showDismissDialog(mContext, R.mipmap.kulian, "取消授权");
        }
    };

    private UMAuthListener umListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        //获取信息回调
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            if (map != null) {
                if (share_media == SHARE_MEDIA.WEIXIN) {
                    // 微信用户信息
                    type = "wx";
                    open_id = map.get("openid");
                    String getsex = map.get("gender");
                    if (getsex.equals("男")) {
                        sex = "1";
                    } else {
                        sex = "2";
                    }
                    nickname = map.get("screen_name");
                    avatar_url = map.get("profile_image_url");

                } else if (share_media == SHARE_MEDIA.QQ) {
                    // qq用户信息
                    type = "qq";
                    String getsex = map.get("gender");
                    if (getsex.equals("男")) {
                        sex = "1";
                    } else {
                        sex = "2";
                    }
                    nickname = map.get("screen_name");
                    avatar_url = map.get("profile_image_url");
                    open_id = map.get("openid");
                } else if (share_media == SHARE_MEDIA.SINA) {
                    //微博用户信息
                    type = "wb";
                    nickname = map.get("screen_name");
                    avatar_url = map.get("profile_image_url");
                    String getsex = map.get("gender");
                    if (getsex.equals("m")) {
                        sex = "1";
                    } else {
                        sex = "2";
                    }
                    open_id = map.get("uid");
                }
                mPresenter.uploadSanFangUserInfo(type, open_id, nickname, sex, avatar_url);
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            //DialogUtils.showDismissDialog(mContext, R.mipmap.kulian, "登录失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            //DialogUtils.showDismissDialog(mContext, R.mipmap.kulian, "取消授权");
        }
    };
}
