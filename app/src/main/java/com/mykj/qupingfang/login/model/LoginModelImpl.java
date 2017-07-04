package com.mykj.qupingfang.login.model;

import com.mykj.qupingfang.domain.login.Login;
import com.mykj.qupingfang.login.contract.LoginContract;
import com.mykj.qupingfang.net.retrofit.HttpMethod;

import rx.Subscriber;

/**
 * Describtion: 登录界面model实现类
 * Created by jia on 2017/7/4.
 * 人之所以能，是相信能
 */
public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public void login(String name, String password, final LoginContract.LoginCallBack callBack) {
        HttpMethod.getInstance().login(name, password, new Subscriber<Login>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBack.onFail(e.toString());
            }

            @Override
            public void onNext(Login login) {
                callBack.onSuccess(login);
            }
        });
    }
}
