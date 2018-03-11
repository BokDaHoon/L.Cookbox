package com.boostcamp.mytwitter.mytwitter.base;

import android.app.Application;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.base.Observer.CustomObserver;
import com.boostcamp.mytwitter.mytwitter.base.Observer.Observable;
import com.boostcamp.mytwitter.mytwitter.base.db.StatusMigration;
import com.tsengvn.typekit.Typekit;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;

/**
 * Created by DaHoon on 2017-02-10.
 */

public class MyTwitterApplication extends Application implements Observable {

    private ArrayList<CustomObserver> observers;            // 등록할 옵저버 리스트
    private static MyTwitterApplication twitterApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        twitterApplication = this;
        observers = new ArrayList<>();

        // Realm init
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("twitterdb")
                //.migration(new StatusMigration())
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(2)
                .build();

        //Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);

        //init SharedPreferences
        SharedPreferenceHelper.getInstance(getApplicationContext());

        // 폰트 정의
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "SeoulNamsanL.otf"))
                .addBold(Typekit.createFromAsset(this, "SeoulNamsanB.otf"));

    }

    @Override
    public void addObserver(CustomObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(CustomObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object obj) {
        for (CustomObserver observer : observers) {
            if (observer != null) {
                observer.update(obj);
            }
        }
    }

    public static MyTwitterApplication getTwitterApplication() {
        return twitterApplication;
    }


}
