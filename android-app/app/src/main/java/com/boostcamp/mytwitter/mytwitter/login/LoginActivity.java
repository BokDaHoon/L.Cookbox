package com.boostcamp.mytwitter.mytwitter.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.base.SharedPreferenceHelper;
import com.boostcamp.mytwitter.mytwitter.base.LoginInfo;
import com.boostcamp.mytwitter.mytwitter.login.presenter.LoginPresenter;
import com.boostcamp.mytwitter.mytwitter.login.presenter.LoginPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.boostcamp.mytwitter.mytwitter.timeline.TimelineActivity;

import java.util.concurrent.ExecutionException;

import twitter4j.auth.RequestToken;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.login_id)
    EditText mEditTextId;
    @BindView(R.id.login_password)
    EditText mEditTextPassword;

    private LoginPresenterImpl presenter;

    private static final String LOGIN_FAILURE = "로그인에 실패하였습니다.";
    private static final String LOGIN_SUCCESS = "로그인에 성공했습니다.";
    private static final String LOGIN_ALREADY_STATE = "이미 로그인되어 있습니다.";

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferenceHelper.getInstance(this).loadProperties();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferenceHelper.getInstance(this).saveProperties();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    void init() {
        presenter = new LoginPresenterImpl();
        presenter.setView(this);

        SharedPreferenceHelper.getInstance(this).loadProperties();

        if (LoginInfo.isLogin) {
            presenter.finishLoginAuth();
        }
    }


    @OnClick(R.id.btn_login)
    void signInClick() {
        String id = mEditTextId.getText().toString();
        String password = mEditTextPassword.getText().toString();

        try {
            presenter.getLoginAuth(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 로그인에 실패했다는 내용 출력.
     */
    @Override
    public void displayConnectFailure() {
        Toast.makeText(getBaseContext(), LOGIN_FAILURE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToAuthorityView(RequestToken mRequestToken) {
        Intent intent = new Intent(getApplicationContext(), LoginAuthorityActivity.class);
        intent.putExtra("authUrl", mRequestToken.getAuthorizationURL());
        startActivityForResult(intent, LoginInfo.REQ_CODE_TWIT_LOGIN);
    }

    /**
     * 이미 로그인했다는 내용 출력
     */
    @Override
    public void displayAlreadyLogin() {
        Toast.makeText(getBaseContext(), LOGIN_ALREADY_STATE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToTimeLine() {
        Intent intent = new Intent(this, TimelineActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 로그인 성공했다는 메세지 출력.
     */
    @Override
    public void displayConnectSuccess() {
        Toast.makeText(getBaseContext(), LOGIN_SUCCESS, Toast.LENGTH_SHORT).show();
    }


    /**
     * 인증 결과를 LoginButton에 전달하기 위해 사용.
     * @param requestCode : 트위터 로그인인지 확인하는 코드
     * @param resultCode : 성공적으로 받았는지 상태코드
     * @param resultIntent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        if (resultCode == RESULT_OK) {
            if (requestCode == LoginInfo.REQ_CODE_TWIT_LOGIN) {
                presenter.getAccessToken(resultIntent);
            }
        }

    }

}
