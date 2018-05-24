package com.zuby.zubydriverdemo.view.DocumentUpload.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Utils.RetroClient;

import java.util.ArrayList;
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
    private DataItem mDataItem;

    public void show(ResultInterface mResultinterface,Context mContext,String citycode,String tokenid,String document_for)
    {
        this.mContext=mContext;
        this.mResultinterface=mResultinterface;

        HashMap<String,String>map = new HashMap<>();
        map.put("city_code",citycode);
        map.put("tokenid",tokenid);
        map.put("document_for",document_for);

        addService(new Gson().toJson(map));
    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForDocService().create(ApiInterface.class);
        Call<GetCityModelNew2> addService = mApiService.getCityDocs(requestBody);
        addService.enqueue(new Callback<GetCityModelNew2>()
        {
            @Override
            public void onResponse(Call<GetCityModelNew2> call,
                                   Response<GetCityModelNew2> response)
            {
//                GetCityModelNew2 getCityModelNew2=new GetCityModelNew2();
//                Gson gson=new Gson();
//                getCityModelNew2=gson.fromJson(response.toString(),GetCityModelNew2.class);
//                Log.e("Em",":::::document name::::::"+" "+getCityModelNew2.getData().get(0).getDocument_id());
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));

//                    for(int i=0;i<response.body().getData().size();i++) {
//                        mDataItem = new DataItem();
//                        mDataItem.setDocumentId(response.body().getData().get(i).getDocumentId());
//                        mDataItem.setDocumentName(response.body().getData().get(i).getDocumentName());

                        Log.e("Zuby", "::::::::::::success:::::::::" + " " +response.body().getType());
                        if (response.body().getType().equalsIgnoreCase("success")) {
                            Log.e("Zuby", "type" + " " + response.body());
                            mResultinterface.onSuccess(response.body());
                        } else {
                            mResultinterface.onFailed(response.body());
                        }
//                    }
                    }
                catch (Exception e)
                {
//                    mResultinterface.onFailed();
                }

            }

            @Override
            public void onFailure(Call<GetCityModelNew2> call, Throwable t)
            {
//                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }
}
