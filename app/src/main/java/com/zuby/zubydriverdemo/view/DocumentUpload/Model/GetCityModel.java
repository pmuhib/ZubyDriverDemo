package com.zuby.zubydriverdemo.view.DocumentUpload.Model;

import java.util.List;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class GetCityModel
{


    /**
     * data : [{"document_id":1,"document_name":"Test"},{"document_id":2,"document_name":"Test2"}]
     * message : Record found
     * type : success
     */

    private String message;
    private String type;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * document_id : 1
         * document_name : Test
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
