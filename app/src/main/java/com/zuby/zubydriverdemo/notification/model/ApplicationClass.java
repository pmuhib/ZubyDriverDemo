package com.zuby.zubydriverdemo.notification.model;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;


public class ApplicationClass {

    public void newData(String devicetoken, String lati, String longi,String deviceid) throws IOException {
        Log.e("devicetoken", devicetoken);
        System.out.println("Welcome to Developine");


        final DefaultHttpClient httpClient = new DefaultHttpClient();
        final HttpPost postRequest = new HttpPost(
                "https://fcm.googleapis.com/fcm/send");

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setDetail("this is firebase push notification from java client (server)");
        notificationData.setTitle("{"+"latitute" + ":" + lati + "," + "longitute" + ":" + longi + "," + "message" + ":" + "heellllsadhakjllsg" + "}");
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(devicetoken);


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = null;
        try {
            input = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        input.setContentType("application/json");
        postRequest.addHeader("Authorization", "key=AAAA0ITQ0sU:APA91bE-fSjA6wnrA27iQhXHADt-1UbLgRWER6Mw7Sa3OSNDS97IsMw3FfKuFXWvtXrsY5pMwaCziaabaqkZ5XaeCVDwCdeNFFf2aDwv8EbJkWQBzU8jee1nwTBcabLZ8KtfAfLARQbn");
        postRequest.setEntity(input);


        System.out.println("reques:" + json);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpResponse response = null;
                try {
                    response = httpClient.execute(postRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                } else if (response.getStatusLine().getStatusCode() == 200) {

                    try {
                        System.out.println("response:" + EntityUtils.toString(response.getEntity()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }
}
