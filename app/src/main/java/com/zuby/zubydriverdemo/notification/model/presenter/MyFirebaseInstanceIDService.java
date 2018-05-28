package com.zuby.zubydriverdemo.notification.model.presenter;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;

/**
 * Created by Belal on 03/11/16.
 */


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    PreferenceManager mPreferencemanager;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Em", "Refreshed token: " + refreshedToken);
        mPreferencemanager = new PreferenceManager(getApplicationContext());
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
        //saving the token on shared preferences
//        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
        mPreferencemanager.setDeviceToken(token);
    }
}
