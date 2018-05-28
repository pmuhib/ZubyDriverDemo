package com.zuby.zubydriverdemo.view.DashBoard.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Utils.RetroClient;
import com.zuby.zubydriverdemo.view.DashBoard.model.DriverAvailabilityModel;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

public class UpdateLocation
{
    Context mContext;
    ResultInterface mResultinterface;

    public void show(ResultInterface mResultinterface,Context mContext)
    {
        this.mResultinterface=mResultinterface;
        this.mContext=mContext;


    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getAvailibilityForDriver().create(ApiInterface.class);
        Call<DriverAvailabilityModel> addService = mApiService.getdriveravailibility(requestBody);
        addService.enqueue(new Callback<DriverAvailabilityModel>()
        {
            @Override
            public void onResponse(Call<DriverAvailabilityModel> call,
                                   Response<DriverAvailabilityModel> response)
            {
                try
                {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.body().getType().equals("success"))
                    {
                        Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultinterface.onSuccess(response.body());
                    } else {
                        mResultinterface.onFailed(response.body());
                    }
                }
                catch (Exception e)
                {
                    mResultinterface.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<DriverAvailabilityModel> call, Throwable t)
            {
//                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }



}
