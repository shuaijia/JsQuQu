package com.mykj.qupingfang.login.contract;

import com.mykj.qupingfang.domain.login.Login;

/**
 * Describtion: 登录契约类
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginContract {

    public interface LoginView{

        void loginSuccess(Login login);

        void loginError(String errorMsg);

    }

    public interface LoginModel{

        void login(String name,String password,LoginCallBack callBack);

    }

    public interface LoginCallBack{
        void onSuccess(Login login);

        void onFail(String errorMsg);
    }
}
