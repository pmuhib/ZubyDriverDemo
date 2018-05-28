package com.zuby.zubydriverdemo.notification.model.presenter;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * This is called whenever app receives notification
 * in background/foreground state so you can
 * apply logic for background task, but still Firebase notification
 * will be shown in notification tray
 */
public class FirebaseDataReceiver extends WakefulBroadcastReceiver {

    private final String TAG = "FirebaseDataReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "I'm in!!!");

        if (intent.getExtras() != null) {
            for (String key : intent.getExtras().keySet()) {
                Object value = intent.getExtras().get(key);
                Log.e("FirebaseDataReceiver", "Key: " + key + " Value: " + value);
            }
        }
    }
}
