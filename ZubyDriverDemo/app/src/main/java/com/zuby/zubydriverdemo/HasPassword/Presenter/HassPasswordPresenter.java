package com.zuby.zubydriverdemo.HasPassword.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.HasPassword.Model.HassPasswordModel;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Registration.Model.Data;
import com.zuby.zubydriverdemo.Registration.Model.Level1RegisterModel;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;
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

public class HassPasswordPresenter
{
    private Context mContext;
    private ResultInterface mResultinterface;

    public void show(ResultInterface mResultinterface,Context mContext,String country_code,String mobile_number,String tokenid)
    {
        this.mResultinterface = mResultinterface;
        this.mContext = mContext;

        HashMap<String,String>map = new HashMap<>();
        map.put("country_code",country_code);
        map.put("mobile_no",mobile_number);
        map.put("tokenid",tokenid);
        addService(new Gson().toJson(map));
    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<HassPasswordModel> addService = mApiService.sendHassPassword(requestBody);
        addService.enqueue(new Callback<HassPasswordModel>()
        {
            @Override
            public void onResponse(Call<HassPasswordModel> call,
                                   Response<HassPasswordModel> response)
            {
                try
                {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.code()==200)
                    {

                        // driver id will only be saved for the first time i.e, if we get mobile number already exists then we will not get anyu ddriver id

                        HassPasswordModel hassPasswordModel = new HassPasswordModel();
                        hassPasswordModel.setMessage(response.body().getMessage());
                        hassPasswordModel.setType(response.body().getType());

                        mResultinterface.onSuccess(response.body());

                        Log.e("Em","response in haspassword"+" "+response.body()+" "+response.body().getMessage());


                    } else {
                        mResultinterface.onFailed(response.message());
                    }
                } catch (Exception e) {
                    mResultinterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<HassPasswordModel> call, Throwable t)
            {
                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }

}
