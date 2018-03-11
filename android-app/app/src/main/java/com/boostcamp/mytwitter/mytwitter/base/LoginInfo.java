package com.boostcamp.mytwitter.mytwitter.base;

import java.text.SimpleDateFormat;

import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by DaHoon on 2017-02-09.
 */

public class LoginInfo {

    public static final String TWIT_API_KEY = "3AyuRwmWaCdyy0wDnZ5Ar3rrK";
    public static final String TWIT_CONSUMER_KEY = "3AyuRwmWaCdyy0wDnZ5Ar3rrK";
    public static final String TWIT_CONSUMER_SECRET = "RvlHIbMrbjHrUZS0c89aMMOmlFa2HOJ3yOwgKyzlSnv1her2PQ";
    public static final String TWIT_CALLBACK_URL = "http://mytwitter.boostcamp.com";
    public static User TwitUser = null;

    public static final int REQ_CODE_TWIT_LOGIN = 1001;

    public static boolean isLogin = false;

    public static String MEMBER_ID = "";
    public static String MEMBER_NAME = "";
    public static String MEMBER_SEX = "";
    public static int MEMBER_AGE = 0;
    public static String MEMBER_PHONE_NUMBER = "";
    public static String MEMBER_ADDRESS = "";

    public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

}
