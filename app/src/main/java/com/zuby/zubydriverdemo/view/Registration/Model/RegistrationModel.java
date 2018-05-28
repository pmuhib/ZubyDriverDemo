package com.zuby.zubydriverdemo.view.Registration.Model;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class RegistrationModel
{
    /**
     * type : failure
     * message :
     * data : {"status":"active","user_id":"CAB3325_00000001"}
     * rider_access : no_access
     * driver_access : active
     * owner_access : no_access
     */

    private String type;
    private String message;
    private DataBean data;
    private String rider_access;
    private String driver_access;
    private String owner_access;

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

    public static class DataBean {
        /**
         * status : active
         * user_id : CAB3325_00000001
         */

        private String status;
        private String user_id;

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


//    /**
//     * type : success
//     * message :
//     * data : {"country_code":"91","mobile_no":"9876543210","first_name":"Aasif","last_name":"Ali","access_type":"rider","status":1,"user_id":"CAB3210_00000001","rider_access":"active","driver_access":"no_access","owner_access":"no_access"}
//     */
//
//    private String type;
//    private String message;
//    private DataBean data;
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * country_code : 91
//         * mobile_no : 9876543210
//         * first_name : Aasif
//         * last_name : Ali
//         * access_type : rider
//         * status : 1
//         * user_id : CAB3210_00000001
//         * rider_access : active
//         * driver_access : no_access
//         * owner_access : no_access
//         */
//
//        private String country_code;
//        private String mobile_no;
//        private String first_name;
//        private String last_name;
//        private String access_type;
//        private int status;
//        private String user_id;
//        private String rider_access;
//        private String driver_access;
//        private String owner_access;
//
//        public String getCountry_code() {
//            return country_code;
//        }
//
//        public void setCountry_code(String country_code) {
//            this.country_code = country_code;
//        }
//
//        public String getMobile_no() {
//            return mobile_no;
//        }
//
//        public void setMobile_no(String mobile_no) {
//            this.mobile_no = mobile_no;
//        }
//
//        public String getFirst_name() {
//            return first_name;
//        }
//
//        public void setFirst_name(String first_name) {
//            this.first_name = first_name;
//        }
//
//        public String getLast_name() {
//            return last_name;
//        }
//
//        public void setLast_name(String last_name) {
//            this.last_name = last_name;
//        }
//
//        public String getAccess_type() {
//            return access_type;
//        }
//
//        public void setAccess_type(String access_type) {
//            this.access_type = access_type;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public void setStatus(int status) {
//            this.status = status;
//        }
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getRider_access() {
//            return rider_access;
//        }
//
//        public void setRider_access(String rider_access) {
//            this.rider_access = rider_access;
//        }
//
//        public String getDriver_access() {
//            return driver_access;
//        }
//
//        public void setDriver_access(String driver_access) {
//            this.driver_access = driver_access;
//        }
//
//        public String getOwner_access() {
//            return owner_access;
//        }
//
//        public void setOwner_access(String owner_access) {
//            this.owner_access = owner_access;
//        }
//    }

//    /**
//     * type : success
//     * message :
//     * data : {"country_code":"+91","mobile_no":"8447823325","first_name":"shravani","last_name":"hui","access_type":"driver","status":"active","user_id":"CAB3325_00000002"}
//     */
//
//    private String type;
//    private String message;
//    private DataBean data;
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * country_code : +91
//         * mobile_no : 8447823325
//         * first_name : shravani
//         * last_name : hui
//         * access_type : driver
//         * status : active
//         * user_id : CAB3325_00000002
//         */
//
//        private String country_code;
//        private String mobile_no;
//        private String first_name;
//        private String last_name;
//        private String access_type;
//        private String status;
//        private String user_id;
//
//        public String getCountry_code() {
//            return country_code;
//        }
//
//        public void setCountry_code(String country_code) {
//            this.country_code = country_code;
//        }
//
//        public String getMobile_no() {
//            return mobile_no;
//        }
//
//        public void setMobile_no(String mobile_no) {
//            this.mobile_no = mobile_no;
//        }
//
//        public String getFirst_name() {
//            return first_name;
//        }
//
//        public void setFirst_name(String first_name) {
//            this.first_name = first_name;
//        }
//
//        public String getLast_name() {
//            return last_name;
//        }
//
//        public void setLast_name(String last_name) {
//            this.last_name = last_name;
//        }
//
//        public String getAccess_type() {
//            return access_type;
//        }
//
//        public void setAccess_type(String access_type) {
//            this.access_type = access_type;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//    }



}
