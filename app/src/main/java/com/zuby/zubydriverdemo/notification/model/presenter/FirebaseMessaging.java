package com.zuby.zubydriverdemo.notification.model.presenter;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Random;

public class FirebaseMessaging extends FirebaseMessagingService {
    private String mMessage = "";
    private NotificationCompat.Builder mNotificationCompat;
    private Context mContext;
    private String mGoalName;
    private JSONObject mJsonObj;
    String TAG = "FirebaseMessaging";
    int i = 0;

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.e("data", remoteMessage.getData().toString());
        Intent broadcastIntent = new Intent("com.truiton.broadcast.string").putExtra("Data", remoteMessage.getData().toString());
//        broadcastIntent.setAction(MainActivity.mBroadcastArrayListAction);
//        broadcastIntent.putExtra("Data", remoteMessage.getData().toString());
        sendBroadcast(broadcastIntent);
    }


    public int generateRandom() {
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }
}
