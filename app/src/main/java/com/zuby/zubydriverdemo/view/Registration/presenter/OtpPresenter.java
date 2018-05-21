package com.zuby.zubydriverdemo.view.Registration.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.view.Registration.Model.OtpModel;
import com.zuby.zubydriverdemo.Utils.RetroClient;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public class OtpPresenter
{
    Context mContext;
    ResultInterface mResultInterface;

    public void show(ResultInterface mResultInterface,Context mContext,String tokenid,String userid,String mobile)
    {
        this.mContext=mContext;
        this.mResultInterface=mResultInterface;
        HashMap<String,String>map= new HashMap<>();
        map.put("tokenid",tokenid);
        map.put("userid","CAB3325_00000004");
        map.put("mobile",mobile);

        addService(new Gson().toJson(map));

    }


    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForOtp().create(ApiInterface.class);
        Call<OtpModel> addService = mApiService.sendOtp(requestBody);
        addService.enqueue(new Callback<OtpModel>()
        {
            @Override
            public void onResponse(Call<OtpModel> call,
                                   Response<OtpModel> response)
            {
                try
                {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.body().getType().equals("success"))
                    {
                        Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultInterface.onSuccess(response.body());
                    } else {
                        mResultInterface.onFailed(response.message());
                    }
                }
                catch (Exception e)
                {
                    mResultInterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<OtpModel> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }
}
