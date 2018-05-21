package com.zuby.zubydriverdemo.DocumentUpload.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zuby.zubydriverdemo.DocumentUpload.Model.DocumentUploadModel;
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
 * Created by citymapper-pc5 on 20/5/18.
 */

public class DocumentUploadPresenter
{
    private Context mContext;
    private ResultInterface mResultInterface;


    public void show(ResultInterface mResultInterface,Context mContext,String mDriverid,String mDocumentid,String mDocumentExpiry,String mDocumentImage,String mTokenid)
    {
        this.mResultInterface=mResultInterface;
        this.mContext=mContext;

        HashMap<String,String>map = new HashMap<>();
        map.put("driver_id",mDriverid);
        map.put("document_id",mDocumentid);
        map.put("document_expiry",mDocumentExpiry);
        map.put("document_image",mDocumentImage);
        map.put("tokenid",mTokenid);


        addService(new Gson().toJson(map));

    }



    public void addService(String data)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), data);
        ApiInterface mApiService = RetroClient.getClientForDocService().create(ApiInterface.class);
        Call<DocumentUploadModel> addService = mApiService.DocUpload(requestBody);
        addService.enqueue(new Callback<DocumentUploadModel>()
        {
            @Override
            public void onResponse(Call<DocumentUploadModel> call,
                                   Response<DocumentUploadModel> response)
            {
                try {
                    Log.e("ZUBY", new Gson().toJson(response.body()));
                    if (response.body().getType().equals("success"))
                    {
                        Log.e("Zuby","type"+ " "+response.body().getMessage());
                        mResultInterface.onSuccess(response.body());
                    }
                    else
                        {
                        mResultInterface.onFailed(response.message());
                    }
                }
                catch (Exception e)
                {
                    mResultInterface.onFailed("No Data Found");
                }
            }

            @Override
            public void onFailure(Call<DocumentUploadModel> call, Throwable t)
            {
                mResultInterface.onFailed("No Data Found");
                Log.d("ZUBY", "" + t);
            }
        });
    }


}
