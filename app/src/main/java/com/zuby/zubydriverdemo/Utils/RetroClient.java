package com.zuby.zubydriverdemo.Utils;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient()
    {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(4000, TimeUnit.SECONDS)
//                .readTimeout(4000,TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


    public static Retrofit getClientForOtp()
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(4000, TimeUnit.SECONDS)
                .readTimeout(4000,TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.GENERATE_OTP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getClientForDocService()
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(4000, TimeUnit.SECONDS)
                .readTimeout(4000,TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.DOCUMENT_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

//    public static Retrofit getoken() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.TOKEN_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit send_otp() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.SEND_OTP_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit ridermanagementurl() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.RIDER_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit carClient() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.CAR_SERVICE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit nearbyCab() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.NEARBY_DRIVER_URL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit feedData() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.FEED_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
//
//    public static Retrofit tripManagement() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(ApiKeys.TRIP_MANAGEMENT_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }
}