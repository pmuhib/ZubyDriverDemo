package com.zuby.zubydriverdemo.view.Login.Model;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

public class DeviceTokenModel
{

    /**
     * type : success
     * message : found
     * data : device_id
     */

    private String type;
    private String message;
    private String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
