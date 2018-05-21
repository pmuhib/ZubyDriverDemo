package com.zuby.zubydriverdemo.DocumentUpload.Model;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class CityModel
{

    /**
     * type : failure
     * message : No record
     * data :
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
