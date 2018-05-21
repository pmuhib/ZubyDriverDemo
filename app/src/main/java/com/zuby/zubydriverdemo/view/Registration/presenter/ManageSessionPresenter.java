package com.zuby.zubydriverdemo.view.Registration.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.view.Registration.Model.SessionModel;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
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

public class ManageSessionPresenter
{
    private Context mContext;
    private ResultInterface mResultinterface;

    public void show(ResultInterface mResultinterface,Context mContext,String userid,String sessionlogintype,String sessionid,String tokenid)
    {
        this.mResultinterface=mResultinterface;
        this.mContext=mContext;
        HashMap<String,String>map = new HashMap<>();
        map.put("user_id",userid);
        map.put("session_login_type",sessionlogintype);
        map.put("session_id",sessionid);
        map.put("tokenid",tokenid);

        addService(new Gson().toJson(map));
    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClient().create(ApiInterface.class);
        Call<SessionModel> addService = mApiService.checkSession(requestBody);
        addService.enqueue(new Callback<SessionModel>()
        {
            @Override
            public void onResponse(Call<SessionModel> call,
                                   Response<SessionModel> response)
            {
                try
                {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.body().getType().equals("success"))
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
            public void onFailure(Call<SessionModel> call, Throwable t)
            {
                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }
}
