package com.boostcamp.mytwitter.mytwitter.login.presenter;


import android.content.Intent;

import com.boostcamp.mytwitter.mytwitter.login.model.LoginModel;

import java.util.concurrent.ExecutionException;


/**
 * Created by DaHoon on 2017-02-09.
 */

public class LoginPresenterImpl implements LoginPresenter.Presenter {

    private LoginPresenter.View view;
    private LoginModel model;
    private String TAG = "LoginPresentImpl";

    @Override
    public void setView(LoginPresenter.View view) {
        this.view = view;
        model = new LoginModel();
        model.setPresenter(this);
    }

    @Override
    public void getLoginAuth(String id, String password) throws ExecutionException, InterruptedException {
        model.loginCheck(id, password);
    }

    public void finishLoginAuth() {
        view.displayConnectSuccess();
        view.moveToTimeLine();
    }

    public void failLoginAuth() {
        view.displayConnectFailure();
    }

    @Override
    public void getAccessToken(Intent resultIntent) {
    }
}
