package com.zuby.zubydriverdemo.Presenter.interfaces;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


import com.zuby.zubydriverdemo.view.DashBoard.model.DriverAvailabilityModel;
import com.zuby.zubydriverdemo.view.DocumentUpload.Model.DocumentUploadModel;
import com.zuby.zubydriverdemo.view.DocumentUpload.Model.VerifyDocumentModel;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.GetCityModelNew2;
import com.zuby.zubydriverdemo.view.HasPassword.Model.HassPasswordModel;
import com.zuby.zubydriverdemo.view.Login.Model.LoginModel;
import com.zuby.zubydriverdemo.view.Registration.Model.NewRegisterModel;
import com.zuby.zubydriverdemo.view.Registration.Model.OtpVerified;
import com.zuby.zubydriverdemo.view.Registration.Model.RegistrationModel;
import com.zuby.zubydriverdemo.view.Registration.Model.SessionModel;
import com.zuby.zubydriverdemo.view.Registration.Model.OtpModel;
import com.zuby.zubydriverdemo.view.SetPassword.Model.SetPasswordModel;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public interface ApiInterface
{

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("level1Register")
    Call<NewRegisterModel> registerMobile(@Body RequestBody title);

//    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("api/otp_send")
    Call<OtpModel> sendOtp(@Body RequestBody title);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("hasPassword")
    Call<HassPasswordModel> sendHassPassword(@Body RequestBody title);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("setPassword")
    Call<SetPasswordModel> setPassword(@Body RequestBody title);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("level2Register")
    Call<RegistrationModel> setRegistration(@Body RequestBody title);

//    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("api/otp_verification")
    Call<OtpVerified> setVerifyOtp(@Body RequestBody title);

//    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginModel> setLogin(@Body RequestBody title);

    @Headers("Content-Type: application/json")
    @POST("isSessionValid")
    Call<SessionModel> checkSession(@Body RequestBody title);


    @Headers("Content-Type: application/json")
    @POST("adddriverdocument")
    Call<DocumentUploadModel> DocUpload(@Body RequestBody title);

    @Headers("Content-Type: application/json")
    @POST("verifydocument")
    Call<VerifyDocumentModel> VerifyDoc(@Body RequestBody title);

    //For json arrays
    @Headers("Content-Type: application/json")
    @POST("getDocumentByUserTypeAndCity")
    Call<GetCityModelNew2> getCityDocs(@Body RequestBody title);

    //For json arrays
    @Headers("Content-Type: application/json")
    @POST("updateDrivingPreference")
    Call<DriverAvailabilityModel> getdriveravailibility(@Body RequestBody title);


}
