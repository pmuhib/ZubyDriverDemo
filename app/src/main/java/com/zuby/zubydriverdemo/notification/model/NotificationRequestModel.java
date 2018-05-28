package com.zuby.zubydriverdemo.notification.model;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationRequestModel {

    @SerializedName("data")
    private NotificationData mData;
    @SerializedName("to")
    private String mTo;

    public NotificationData getData() {
        return mData;
    }

    public void setData(NotificationData data) {
        mData = data;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }

}
