package com.zuby.zubydriverdemo.view.DocumentUpload.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.Presenter.interfaces.ApiInterface;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.Utils.RetroClient;
import com.zuby.zubydriverdemo.view.DocumentUpload.Model.DocumentUploadModel;
import com.zuby.zubydriverdemo.view.DocumentUpload.Model.VerifyDocumentModel;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by citymapper-pc5 on 22/5/18.
 */

public class VerifyPresenter
{
    Context mContext;
    ResultInterface mResultinterface;

    public void show(ResultInterface mResultinterface,Context mContext,String driver_id,String document_id,String tokenid)
    {
        this.mResultinterface=mResultinterface;
        this.mContext=mContext;

        HashMap<String,String>map= new HashMap<>();
        map.put("driver_id",driver_id);
        map.put("document_id",document_id);
        map.put("tokenid",tokenid);
    }

    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForDocService().create(ApiInterface.class);
        Call<VerifyDocumentModel> addService = mApiService.VerifyDoc(requestBody);
        addService.enqueue(new Callback<VerifyDocumentModel>()
        {
            @Override
            public void onResponse(Call<VerifyDocumentModel> call,
                                   Response<VerifyDocumentModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.body().getType().equals("success"))
                    {
                        Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultinterface.onSuccess(response.body());
                    }
                    else {
                        mResultinterface.onFailed(response.message());
                    }
                }
                catch (Exception e)
                {
                    mResultinterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<VerifyDocumentModel> call, Throwable t)
            {
                mResultinterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }

}
