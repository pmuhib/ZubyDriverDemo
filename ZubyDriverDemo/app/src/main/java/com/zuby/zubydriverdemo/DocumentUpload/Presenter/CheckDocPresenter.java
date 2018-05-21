package com.zuby.zubydriverdemo.DocumentUpload.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.DocumentUpload.Model.CityModel;
import com.zuby.zubydriverdemo.DocumentUpload.Model.GetCityModel;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Registration.Model.OtpModel;
import com.zuby.zubydriverdemo.Utils.RetroClient;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class CheckDocPresenter
{
    private Context mContext;
    private ResultInterface mResultinterface;


    public void show(ResultInterface mResultinterface,Context mContext,String citycode,String tokenid,String document_for)
    {
        this.mContext=mContext;
        this.mResultinterface=mResultinterface;

        HashMap<String,String>map= new HashMap<>();
        map.put("city_code",citycode);
        map.put("tokenid",tokenid);
        map.put("document_for",document_for);

        addService(new Gson().toJson(map));
    }


    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForDocService().create(ApiInterface.class);
        Call<CityModel> addService = mApiService.getCityDocs(requestBody);
        addService.enqueue(new Callback<CityModel>()
        {
            @Override
            public void onResponse(Call<CityModel> call,
                                   Response<CityModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.code()==200)
                    {
                    Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultinterface.onSuccess(response.body());
                    } else {
                        mResultinterface.onFailed(response.message());
                    }
                }
                catch (Exception e)
                {
                    mResultinterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t)
            {
                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }
}
