package com.zuby.zubydriverdemo.notification.model;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("detail")
    private String mDetail;

    @SerializedName("title")
    private String mTitle;

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
