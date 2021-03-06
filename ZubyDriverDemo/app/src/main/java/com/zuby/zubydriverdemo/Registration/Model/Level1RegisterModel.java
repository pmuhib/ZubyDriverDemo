package com.zuby.zubydriverdemo.Registration.Model;


import com.google.gson.annotations.SerializedName;

public class Level1RegisterModel
{
	/**
	 * type : failure
	 * message : Mobile no. already exists
	 * data : {"user_id":"","mobile_no":"9742369373","registration_time":"","time_zone":"Asia/Calcutta","country_code":"91","status":"inactive"}
	 */

	private String type;
	private String message;
	@SerializedName("data")
	private DataBean Datanew;

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

	public DataBean getDatanew() {
		return Datanew;
	}

	public void setDatanew(DataBean Datanew) {
		this.Datanew = Datanew;
	}

	public static class DataBean {
		/**
		 * user_id :
		 * mobile_no : 9742369373
		 * registration_time :
		 * time_zone : Asia/Calcutta
		 * country_code : 91
		 * status : inactive
		 */

		private String user_id;
		private String mobile_no;
		private String registration_time;
		private String time_zone;
		private String country_code;
		private String status;

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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}





}