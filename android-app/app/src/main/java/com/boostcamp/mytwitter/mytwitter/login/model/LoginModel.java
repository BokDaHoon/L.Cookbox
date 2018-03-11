package com.boostcamp.mytwitter.mytwitter.login.model;


import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.login.presenter.LoginPresenter;
import com.boostcamp.mytwitter.mytwitter.login.presenter.LoginPresenterImpl;
import com.boostcamp.mytwitter.mytwitter.timeline.adapter.contract.RecipeStatusDTO;
import com.boostcamp.mytwitter.mytwitter.timeline.model.TimelineModel;
import com.boostcamp.mytwitter.mytwitter.util.Define;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by DaHoon on 2017-02-09.
 */

public class LoginModel {

    LoginPresenterImpl presenter;

    public void setPresenter(LoginPresenterImpl presenter) {
        this.presenter = presenter;
    }

    public interface LoginService {
        @GET("/login/memberInfo")
        Call<LoginDTO> getMemberInfo(@Query("id") String id);
    }

    public void loginCheck(String id, String password) throws ExecutionException, InterruptedException {
        new LoginCheckTask().execute(id, password);
    }

    class LoginCheckTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            final String id = params[0];
            final String password = params[1];

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Define.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginService service = retrofit.create(LoginService.class);
                Call<LoginDTO> call = service.getMemberInfo(id);

                call.enqueue(new Callback<LoginDTO>() {

                    @Override
                    public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                        if (response.isSuccessful()) {
                            LoginDTO result = response.body();

                            if (id.equals(result.getMbrId()) && password.equals(result.getPassword())) {
                                LoginInfo.isLogin = true;
                                LoginInfo.MEMBER_ID = result.getMbrId();
                                LoginInfo.MEMBER_NAME = result.getName();
                                LoginInfo.MEMBER_ADDRESS = result.getAddress();
                                LoginInfo.MEMBER_AGE = result.getAge();
                                LoginInfo.MEMBER_PHONE_NUMBER = result.getPhoneNumber();
                                LoginInfo.MEMBER_SEX = result.getSex();
                                presenter.finishLoginAuth();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginDTO> call, Throwable t) {
                        Log.d("ERROR CODE", t.toString());
                        presenter.failLoginAuth();
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("Timeline Activity", "실패");

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }


    }
}
