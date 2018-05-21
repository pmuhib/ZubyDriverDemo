package com.zuby.zubydriverdemo.view.Registration.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.view.Registration.Model.OtpVerified;
import com.zuby.zubydriverdemo.Utils.RetroClient;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */


public class VerifyOtpPresenter
{
    private Context mContext;
    private ResultInterface mResultInterface;
    private String mTokenid,mUserid,mMobileno,mOtp;

    public void show(ResultInterface mResultInterface,Context mContext,String mTokenid,String mUserid,String mMobileno,String mOtp)
    {
        this.mContext=mContext;
        this.mResultInterface=mResultInterface;
        this.mTokenid=mTokenid;
        this.mUserid=mUserid;
        this.mMobileno=mMobileno;
        this.mOtp=mOtp;


        HashMap<String,String>map = new HashMap<>();
        map.put("tokenid",mTokenid);
        map.put("userid","CAB3325_00000004");
        map.put("mobile",mMobileno);
        map.put("otp",mOtp);

        addService(new Gson().toJson(map));

    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForOtp().create(ApiInterface.class);
        Call<OtpVerified> addService = mApiService.setVerifyOtp(requestBody);
        addService.enqueue(new Callback<OtpVerified>()
        {
            @Override
            public void onResponse(Call<OtpVerified> call,
                                   Response<OtpVerified> response)
            {
                try
                {
                    Log.e("ZUBY", new Gson().toJson(response.body()));

                    if (response.body().getType().equals("success"))
                    {
                        Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultInterface.onSuccess(response.body());
                    }
                    else
                    {
                        mResultInterface.onFailed(response.message());
                    }
                }
                catch (Exception e)
                {
                    mResultInterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<OtpVerified> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }
}
