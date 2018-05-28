package com.zuby.zubydriverdemo.view.Registration.Model;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class NewRegisterModel
{


    /**
     * type : success
     * message :
     * data : {"user_id":"CAB2149_00000001","mobile_no":"8279632149","registration_time":"2018-05-27 00:16:06","time_zone":"Asia/Calcutta","country_code":"91","rider_access":"no_access","driver_access":"pending","owner_access":"no_access"}
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
         * user_id : CAB2149_00000001
         * mobile_no : 8279632149
         * registration_time : 2018-05-27 00:16:06
         * time_zone : Asia/Calcutta
         * country_code : 91
         * rider_access : no_access
         * driver_access : pending
         * owner_access : no_access
         */

        private String user_id;
        private String mobile_no;
        private String registration_time;
        private String time_zone;
        private String country_code;
        private String rider_access;
        private String driver_access;
        private String owner_access;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMobile_no() {
            return mobile_no;
        }

        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        public String getRegistration_time() {
            return registration_time;
        }

        public void setRegistration_time(String registration_time) {
            this.registration_time = registration_time;
        }

        public String getTime_zone() {
            return time_zone;
        }

        public void setTime_zone(String time_zone) {
            this.time_zone = time_zone;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getRider_access() {
            return rider_access;
        }

        public void setRider_access(String rider_access) {
            this.rider_access = rider_access;
        }

        public String getDriver_access() {
            return driver_access;
        }

        public void setDriver_access(String driver_access) {
            this.driver_access = driver_access;
        }

        public String getOwner_access() {
            return owner_access;
        }

        public void setOwner_access(String owner_access) {
            this.owner_access = owner_access;
        }
    }
}
