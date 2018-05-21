package com.zuby.zubydriverdemo.view.Registration.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.view.Registration.Model.Data;
import com.zuby.zubydriverdemo.view.Registration.Model.NewRegisterModel;
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

public class AddMobileNumberPresenter
{
    private ResultInterface mResultInterface;
    private Context mContext;
    private PreferenceManager mPreferencemanager;

    public void show(ResultInterface mResultInterface,Context mContext,String countrycode,String mobilenumber,String timeZone,String TOKENID)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("country_code",countrycode);
        map.put("mobile_no",mobilenumber);
        map.put("time_zone",timeZone);
        map.put("tokenid",TOKENID);

        this.mResultInterface= mResultInterface;
        this.mContext=mContext;

        Log.e("Em","Gson"+" "+new Gson().toJson(map));

        addService(new Gson().toJson(map));
    }


    public void addService(String data) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), data);
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<NewRegisterModel> addService = mApiService.registerMobile(requestBody);
        addService.enqueue(new Callback<NewRegisterModel>() {
            @Override
            public void onResponse(Call<NewRegisterModel> call,
                                   Response<NewRegisterModel> response) {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.code() == 200 && response.body().getData().getStatus().equalsIgnoreCase("success") && response.body().getType().equalsIgnoreCase("success")) {

                        // driver id will only be saved for the first time i.e, if we get mobile number already exists then we will not get anyu ddriver id
                        mPreferencemanager = new PreferenceManager(mContext);
                        mPreferencemanager.saveDriverId("CAB3325_00000004");
                        Data data = new Data();
                        data.setCountryCode(response.body().getData().getCountry_code());
                        data.setMobileNo(response.body().getData().getMobile_no());
                        data.setRegistrationTime(response.body().getData().getRegistration_time());
                        data.setStatus(response.body().getData().getStatus());
                        data.setTimeZone(response.body().getData().getTime_zone());
                        data.setUserId(response.body().getData().getUser_id());
//                        data.setType(response.body().getType());

                        mResultInterface.onSuccess(response.body().getData());
//                        mResultInterface.onSuccess(response.body().getType());


                        Log.e("Em", "response.body().getData()" + " " + response.body().getData() + " " + response.body().getData().getUser_id());


                    } else {
                        mResultInterface.onFailed(response.body());
                    }
                } catch (Exception e) {
                    mResultInterface.onFailed(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewRegisterModel> call, Throwable t) {
//                mResultInterface.onFailed();
                Log.d("ZUBY", "" + t);
            }
        });

    }

}
