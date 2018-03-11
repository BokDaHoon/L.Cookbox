package com.boostcamp.mytwitter.mytwitter.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.boostcamp.mytwitter.mytwitter.R;
import com.boostcamp.mytwitter.mytwitter.timeline.TimelineActivity;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by DaHoon on 2017-07-25.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    static PowerManager.WakeLock wakeLock;

    private String msg;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        wakeUpScreen();

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        msg = remoteMessage.getData().get("body");

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        // [END receive_message]

        Intent intent = new Intent(this, TimelineActivity.class);
        intent.putExtra("recipe", msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);

        PendingIntent contentIntent = PendingIntent.getActivity(this, new Random().nextInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("검색하신 레시피 목록입니다.")
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1, 1000});

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mBuilder.setContentIntent(contentIntent);
        notificationManager.notify(iUniqueId, mBuilder.build());
    }

    private void wakeUpScreen() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
        PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.PARTIAL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
        wakeLock.acquire(3000);
    }



}
