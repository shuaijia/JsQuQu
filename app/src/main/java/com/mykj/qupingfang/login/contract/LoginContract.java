package com.mykj.qupingfang.login.contract;

import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.domain.login.SanFangLogin;

/**
 * Describtion: 登录契约类
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginContract {

    public interface LoginView {

        //正常登录回调
        void loginSuccess(Login login);

        void loginError(String errorMsg);

        //三方登录回调
        void uploadSuccess(SanFangLogin result);

        void uploadFail(SanFangLogin result);

        void uploadNoNet(String eerorMsg);

    }

    public interface LoginModel {

        void login(String name, String password, LoginCallBack callBack);

        void uploadSanFangUserInfo(String type, String openid, String nickname, String gender, String headUrl, UploadCallBack uploadCallBack);
    }

    public interface LoginCallBack {
        void onSuccess(Login login);

        void onFail(String errorMsg);
    }

    public interface UploadCallBack {
        void onSuccess(SanFangLogin result);

        void onFail(String errorMsg);
    }
}
