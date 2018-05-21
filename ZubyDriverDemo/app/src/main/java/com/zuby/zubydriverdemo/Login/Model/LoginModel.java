package com.zuby.zubydriverdemo.Login.Model;


import com.google.gson.annotations.SerializedName;

public class LoginModel
{
	/**
	 * type : success
	 * message :
	 * data : {"user_id":"CAB3325_00000004","session_id":"vYACSZddE8M77YDoMGz9pgYZuLjFAqsbAhL9Xm6c","session_login_type":"driver_session"}
	 */

	private String type;
	private String message;
	@SerializedName("data")
	private DataBean Datademo;

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

	public DataBean getDatademo() {
		return Datademo;
	}

	public void setDatademo(DataBean Datademo) {
		this.Datademo = Datademo;
	}

	public static class DataBean {
		/**
		 * user_id : CAB3325_00000004
		 * session_id : vYACSZddE8M77YDoMGz9pgYZuLjFAqsbAhL9Xm6c
		 * session_login_type : driver_session
		 */

		private String user_id;
		private String session_id;
		private String session_login_type;

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getSession_id() {
			return session_id;
		}

		public void setSession_id(String session_id)
		{
			this.session_id = session_id;
		}

		public String getSession_login_type() {
			return session_login_type;
		}

		public void setSession_login_type(String session_login_type) {
			this.session_login_type = session_login_type;
		}
	}
}