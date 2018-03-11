package com.boostcamp.mytwitter.mytwitter.login.presenter;

import android.content.Intent;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.concurrent.ExecutionException;

import twitter4j.auth.RequestToken;


/**
 * Created by DaHoon on 2017-02-08.
 */

public interface LoginPresenter {

    interface View {
        void displayAlreadyLogin();
        void displayConnectSuccess();
        void displayConnectFailure();
        void moveToAuthorityView(RequestToken mRequestToken);
        void moveToTimeLine();
    }

    interface Presenter {
        void setView(LoginPresenter.View view);
        void getLoginAuth(String id, String password) throws ExecutionException, InterruptedException;
        void getAccessToken(Intent resultIntent);
    }

}
