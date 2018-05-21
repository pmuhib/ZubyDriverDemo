package com.zuby.zubydriverdemo.Registration.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Registration.Model.Data;
import com.zuby.zubydriverdemo.Registration.Model.RegistrationModel;
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

public class RegisterPresenter
{
    private Context mContext;
    private ResultInterface mResultInterface;
    private PreferenceManager mPreferencemanager;


    public void show(ResultInterface mResultInterface,Context mContext,String country_code,String mobile_no,String first_name,String last_name,String access_type,String timezone,String tokenid)
    {
        this.mResultInterface=mResultInterface;
        this.mContext=mContext;
        HashMap<String,String>map = new HashMap<>();
        map.put("country_code","+91");
        map.put("mobile_no",mobile_no);
        map.put("first_name",first_name);
        map.put("last_name",last_name);
        map.put("access_type",access_type);
        map.put("time_zone",timezone);
        map.put("tokenid",tokenid);

        addService(new Gson().toJson(map));

    }


    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
//        Retrofit client = RetroClient.ridermanagementurl();
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<RegistrationModel> addService = mApiService.setRegistration(requestBody);
        addService.enqueue(new Callback<RegistrationModel>()
        {
            @Override
            public void onResponse(Call<RegistrationModel> call,
                                   Response<RegistrationModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.code()==200)
                    {
                    Log.e("Zuby","type"+ " "+response.body().getType());

                       Data data = new Data();
                       data.setUserId(response.body().getData().getUser_id());
                       data.setStatus(response.body().getData().getStatus());
                       data.setMobileNo(response.body().getData().getMobile_no());
                       data.setCountryCode(response.body().getData().getCountry_code());
                       data.setAccesstype(response.body().getData().getAccess_type());

                        mPreferencemanager = new PreferenceManager(mContext);
                        mPreferencemanager.saveLevel2Details(response.body().getData().getCountry_code(),response.body().getData().getMobile_no(),response.body().getData().getAccess_type(),response.body().getData().getStatus(),response.body().getData().getUser_id());


                    mResultInterface.onSuccess(response.body().getData());

                    }
                    else
                        {
                        mResultInterface.onFailed(response.body().getMessage());
                    }
                }
                catch (Exception e)
                {
                    mResultInterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<RegistrationModel> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }

}
