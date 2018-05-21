package com.zuby.zubydriverdemo.Registration.Model;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class RegistrationModel
{

    /**
     * type : success
     * message :
     * data : {"country_code":"+91","mobile_no":"8447823325","first_name":"shravani","last_name":"hui","access_type":"driver","status":"active","user_id":"CAB3325_00000002"}
     */

    private String type;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * country_code : +91
         * mobile_no : 8447823325
         * first_name : shravani
         * last_name : hui
         * access_type : driver
         * status : active
         * user_id : CAB3325_00000002
         */

        private String country_code;
        private String mobile_no;
        private String first_name;
        private String last_name;
        private String access_type;
        private String status;
        private String user_id;

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAccess_type() {
            return access_type;
        }

        public void setAccess_type(String access_type) {
            this.access_type = access_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
