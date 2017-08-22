package com.mykj.qupingfang.login.presenter;

import com.mykj.qupingfang.base.BasePresenter;
import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.domain.login.SanFangLogin;
import com.mykj.qupingfang.login.contract.LoginContract;
import com.mykj.qupingfang.login.model.LoginModelImpl;

/**
 * Describtion: 登录界面主持类
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> {

    private LoginModelImpl model;
    private LoginContract.LoginView view;

    public LoginPresenter(LoginContract.LoginView view) {
        model = new LoginModelImpl();
        this.view = view;
    }

    /**
     * 登录方法
     *
     * @param name 用户名
     * @param pwd  密码
     */
    public void login(String name, String pwd) {
        model.login(name, pwd, new LoginContract.LoginCallBack() {
            @Override
            public void onSuccess(Login login) {
                view.loginSuccess(login);
            }

            @Override
            public void onFail(String errorMsg) {
                view.loginError(errorMsg);
            }
        });
    }

    public void uploadSanFangUserInfo(String type, String openid, String nickname, String gender, String headUrl) {
        model.uploadSanFangUserInfo(type, openid, nickname, gender, headUrl, new LoginContract.UploadCallBack() {
            @Override
            public void onSuccess(SanFangLogin result) {
                if (null != result){
                    view.uploadSuccess(result);
                }else {
                    view.uploadFail(result);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                view.uploadNoNet(errorMsg);
            }
        });
    }
}
