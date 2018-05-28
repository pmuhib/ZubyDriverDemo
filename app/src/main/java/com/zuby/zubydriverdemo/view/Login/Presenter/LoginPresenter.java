package com.zuby.zubydriverdemo.view.Login.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.view.Login.Model.LoginModel;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;
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

public class LoginPresenter
{
    private Context mContext;
    private ResultInterface mResultInterface;
    private PreferenceManager mPreferenceManager;

    public void show(ResultInterface mResultInterface,Context mContext,String country_code,String mobileno,String passowrd,String login_device,String login_method,String os_version_name,String session_login_type,String cabi_version_used,String time_zone,String tokenid,String device_id)
    {
        this.mContext=mContext;
        this.mResultInterface=mResultInterface;

        HashMap<String,String>map = new HashMap<>();
        map.put("country_code",country_code);
        map.put("mobile_no",mobileno);
        map.put("password",passowrd);
        map.put("login_device",login_device);
        map.put("login_method",login_method);
        map.put("os_version_name",os_version_name);
        map.put("session_login_type",session_login_type);
        map.put("cabi_version_used",cabi_version_used);
        map.put("time_zone",time_zone);
        map.put("tokenid",tokenid);
        map.put("device_id","device_id");


        addService(new Gson().toJson(map));
        }




    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<LoginModel> addService = mApiService.setLogin(requestBody);
        addService.enqueue(new Callback<LoginModel>()
        {
            @Override
            public void onResponse(Call<LoginModel> call,
                                   Response<LoginModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body())+" "+response.body());
                   if (response.body().getType().equalsIgnoreCase("success"))
                    {
                    Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mPreferenceManager = new PreferenceManager(mContext);
                        mPreferenceManager.saveLoginDetails(response.body().getData().getSession_id(),response.body().getData().getSession_login_type(),response.body().getData().getUser_id());

                        mResultInterface.onSuccess(response.body());

                    } else {
                        mResultInterface.onFailed(response.body());
                    }
                }
                catch (Exception e)
                {
                    mResultInterface.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t)
            {
//                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }

}
