package com.boostcamp.mytwitter.mytwitter.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DaHoon on 2017-02-21.
 */

public class SharedPreferenceHelper{
    private static final String SETTINGS_NAME = "TWIT";
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferenceHelper mHelper;
    private static Context mContext;
    private SharedPreferences.Editor mEditor;

    private SharedPreferenceHelper() {
    }

    public static SharedPreferenceHelper getInstance(Context context) {
        if(mHelper == null) {
            mContext = context;
            mHelper = new SharedPreferenceHelper();
        }

        return mHelper;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences;
    }

    public void saveProperties() {
        mSharedPreferences = mContext.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        mEditor.putBoolean("isLogin", LoginInfo.isLogin);
        mEditor.putString("MEMBER_ID", LoginInfo.MEMBER_ID);
        mEditor.putString("MEMBER_NAME", LoginInfo.MEMBER_NAME);
        mEditor.putString("MEMBER_SEX", LoginInfo.MEMBER_SEX);
        mEditor.putInt("MEMBER_AGE", LoginInfo.MEMBER_AGE);
        mEditor.putString("MEMBER_PHONE_NUMBER", LoginInfo.MEMBER_PHONE_NUMBER);
        mEditor.putString("MEMBER_ADDRESS", LoginInfo.MEMBER_ADDRESS);

        mEditor.commit();
    }

    public void loadProperties() {
        mSharedPreferences = mContext.getSharedPreferences(SETTINGS_NAME, mContext.MODE_PRIVATE);

        LoginInfo.isLogin = mSharedPreferences.getBoolean("isLogin", false);
        LoginInfo.MEMBER_ID = mSharedPreferences.getString("MEMBER_ID", "");
        LoginInfo.MEMBER_NAME = mSharedPreferences.getString("MEMBER_NAME", "");
        LoginInfo.MEMBER_SEX = mSharedPreferences.getString("MEMBER_SEX", "");
        LoginInfo.MEMBER_AGE = mSharedPreferences.getInt("MEMBER_AGE", 0);
        LoginInfo.MEMBER_PHONE_NUMBER = mSharedPreferences.getString("MEMBER_PHONE_NUMBER", "");
        LoginInfo.MEMBER_ADDRESS = mSharedPreferences.getString("MEMBER_ADDRESS", "");
    }

    public void clearProperties() {
        mSharedPreferences = mContext.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }
}