package com.zuby.zubydriverdemo.view.Registration.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

//import com.example.citymapper_pc5.zubydriver.View.Presenter.HttpConnectors.HttpConnectorSendJsonDataLatest;
//import com.example.citymapper_pc5.zubydriver.View.Presenter.interfaces.ResultInterface;
//import com.example.citymapper_pc5.zubydriver.databinding.SplashBinding;

import com.zuby.zubydriverdemo.Presenter.HttpConnectors.HttpConnectorSendJsonDataLatest;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.databinding.SplashBinding;

import org.json.JSONObject;

import okhttp3.Response;

import static com.zuby.zubydriverdemo.Utils.BaseUrl.BASE_URL;
import static com.zuby.zubydriverdemo.Utils.BaseUrl.TOKEN_URL;

//import static com.example.citymapper_pc5.zubydriver.View.Utils.BaseUrl.BASE_URL;
//import static com.example.citymapper_pc5.zubydriver.View.Utils.BaseUrl.TOKEN_URL;

/**
 * Created by citymapper-pc5 on 17/5/18.
 */

public class SessionValid
{
      private ResultInterface mResultInterface;
      private Context mContext;
      private JSONObject mJsonObject;
//      private SharedPrefManager sharedPrefManager;
      private String mTyperesponse,mTypemessage;
      private String mSendparams;
      private String mTokenresponse,mTokenid="",mLoginuserid,mLoginsessionid,mLoginsessionlogintype,mCountry_code,mMobileno;
      private ProgressBar mProgressBar;
      private SplashBinding mSplashbinding;
      private Response mResponseoftokenid;
      private boolean mOncheck;

      public void show(Context mContext,ResultInterface mResultInterface)
      {
            this.mContext=mContext;
            this.mResultInterface=mResultInterface;

            new HitTokenId().execute();

      }

      public class HitTokenId extends AsyncTask<String,String,String>
      {
            @Override
            protected String doInBackground(String... strings)
            {

                  String url=TOKEN_URL+"json/authenticate";
                  HttpConnectorSendJsonDataLatest httpConnectorSendJsonDataLatest = new HttpConnectorSendJsonDataLatest(url);
                  String tokenid = HttpConnectorSendJsonDataLatest.fetchTokenData(mContext);

                  String response = responseTokenId(tokenid);

                  return response;
            }

            @Override
            protected void onPostExecute(String s)
            {
                  super.onPostExecute(s);

                  Log.e("Em","tokenid"+" "+s);

                  if(s.toString().length()>0)
                  {
                        mResultInterface.onSuccess(s);
                  }
                  else
                  {
                        mResultInterface.onFailed("Token not generated");
                  }


            }
      }
      public String responseTokenId(String response)
      {
            try
            {
                  JSONObject jsonObject = new JSONObject(response);
                  mTokenresponse = jsonObject.getString("tokenId");

            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }

            return mTokenresponse;
      }









      public String checkresponse(String response)
      {
            try
            {
                  JSONObject jsonObject = new JSONObject(response);
                  mTyperesponse = jsonObject.getString("type");
                  mTypemessage =  jsonObject.getString("message");
            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }

            return mTyperesponse;
      }


}
