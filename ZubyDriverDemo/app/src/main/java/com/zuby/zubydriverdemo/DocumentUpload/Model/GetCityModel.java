package com.zuby.zubydriverdemo.DocumentUpload.Model;

import java.util.List;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class GetCityModel
{

    /**
     * type : success
     * message : Record found
     * data : [{"document_id":123,"document_name":"Cake PHP tutorial"},{"document_id":1234,"document_name":"Yii2 Totorial"}]
     */

    private String type;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * document_id : 123
         * document_name : Cake PHP tutorial
         */

        private int document_id;
        private String document_name;

        public int getDocument_id() {
            return document_id;
        }

        public void setDocument_id(int document_id) {
            this.document_id = document_id;
        }

        public String getDocument_name() {
            return document_name;
        }

        public void setDocument_name(String document_name) {
            this.document_name = document_name;
        }
    }
}
