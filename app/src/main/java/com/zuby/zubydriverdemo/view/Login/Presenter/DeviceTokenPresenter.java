package com.zuby.zubydriverdemo.view.Login.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;
import com.zuby.zubydriverdemo.Utils.RetroClient;
import com.zuby.zubydriverdemo.view.Login.Model.DeviceTokenModel;
import com.zuby.zubydriverdemo.view.Login.Model.LoginModel;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 26/5/18.
 */

public class DeviceTokenPresenter
{
    ResultInterface mResultInterface;
    Context mContext;
    public void show(ResultInterface mResultInterface,Context mContext,String user_id,String session_login_type,String tokenid )
    {
        this.mResultInterface=mResultInterface;
        this.mContext=mContext;
        HashMap<String,String>map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("session_login_type",session_login_type);
        map.put("tokenid",tokenid);
        addService(new Gson().toJson(map));
    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<DeviceTokenModel> addService = mApiService.getDeviceToken(requestBody);
        addService.enqueue(new Callback<DeviceTokenModel>()
        {
            @Override
            public void onResponse(Call<DeviceTokenModel> call,
                                   Response<DeviceTokenModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body())+" "+response.body());
                    if (response.body().getType().equalsIgnoreCase("success"))
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
            public void onFailure(Call<DeviceTokenModel> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }


}
