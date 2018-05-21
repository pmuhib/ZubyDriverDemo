package com.zuby.zubydriverdemo.SetPassword.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Registration.Model.Data;
import com.zuby.zubydriverdemo.Registration.Model.Level1RegisterModel;
import com.zuby.zubydriverdemo.SetPassword.Model.SetPasswordModel;
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

public class SetPasswordPresenter
{

    private Context mContext;
    private ResultInterface mResultInterface;


    public void show(ResultInterface mResultInterface,Context mContext,String country_code,String mobile_no,String password,String tokenid)
    {
        this.mResultInterface = mResultInterface;
        this.mContext = mContext;
        HashMap<String,String>map = new HashMap<>();
        map.put("country_code",country_code);
        map.put("mobile_no",mobile_no);
        map.put("password",password);
        map.put("tokenid",tokenid);

        addService(new Gson().toJson(map));
    }

    public void addService(String data)
    {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
//        Retrofit client = RetroClient.ridermanagementurl();
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<SetPasswordModel> addService = mApiService.setPassword(requestBody);
        addService.enqueue(new Callback<SetPasswordModel>()
        {
            @Override
            public void onResponse(Call<SetPasswordModel> call,
                                   Response<SetPasswordModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.code()==200)
                    {

                        // driver id will only be saved for the first time i.e, if we get mobile number already exists then we will not get anyu ddriver id

                        SetPasswordModel setPasswordModel = new SetPasswordModel();
                        setPasswordModel.setMessage(response.body().getMessage());
                        setPasswordModel.setType(response.body().getType());
                        mResultInterface.onSuccess(response.body());


                        Log.e("Em","response.body() in setpassword"+" "+response.body()+" "+response.body().getMessage());


                    } else {
                        mResultInterface.onFailed(response.message());
                    }
                } catch (Exception e) {
                    mResultInterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<SetPasswordModel> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }

}
