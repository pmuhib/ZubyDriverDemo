package com.zuby.zubydriverdemo.Registration.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
//                        AsyncForSessionLogin asyncForSessionLogin = new AsyncForSessionLogin();
//                        asyncForSessionLogin.execute(s);
                        mResultInterface.onSuccess(s);
                  }
                  else
                  {
                        mResultInterface.onFailed("Token not generated");
//                        Toast.makeText(mContext,"Token id is not generated!",Toast.LENGTH_LONG).show();
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


      public class AsyncForSessionLogin extends AsyncTask<String,String,String>
      {

            @Override
            protected String doInBackground(String... strings)
            {

                  String session = setSession(strings[0]);
                  return session;
            }

            @Override
            protected void onPostExecute(String s)
            {
                  super.onPostExecute(s);

                  if(s!=null && s.toString().length()>0 && s.equalsIgnoreCase("success") )
                  {
//                        splashbinding.progressBarCyclic.setVisibility(View.GONE);
//                        Intent intent = new Intent(Splash.this,DashBoard.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("country_code",country_code);
//                        bundle.putString("mobile_no",mobileno);
//                        bundle.putString("session_login_type",loginsessionlogintype);
//                        bundle.putString("tokenid",tokenresponse);
//                        bundle.putString("driverid",loginuserid);
//                        bundle.putBoolean("oncheck",oncheck);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        mResultInterface.onSuccessSplash("","","","","",true);
                  }
                  else
                  {
//                        Log.e("Em","mTokenId splash"+" "+tokenid);
//                        splashbinding.progressBarCyclic.setVisibility(View.GONE);
//
//                        Log.e("Em","mTokenId in splash"+" "+tokenresponse);
//
//                        Intent intent = new Intent(Splash.this,MainActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("mTokenId",tokenresponse);
//                        intent.putExtras(bundle);
//
//                        startActivity(intent);
                  }
            }
      }

      public String setSession(String tokenid)
      {
            String url=BASE_URL+"isSessionValid";

            HttpConnectorSendJsonDataLatest httpConnectorSendJsonDataLatest = new HttpConnectorSendJsonDataLatest(url);



//            SharedPreferences prfs = getSharedPreferences("PREF", Context.MODE_PRIVATE);
//            loginuserid = prfs.getString("loginuserid", "");
//            loginsessionid = prfs.getString("loginsessionid", "");
//            loginsessionlogintype = prfs.getString("loginsessionlogintype", "");
//            country_code = prfs.getString("country_code", "");
//            mobileno = prfs.getString("mobilenumber", "");
//            String registration_status= prfs.getString("registration_status",null);
//            oncheck = prfs.getBoolean("onCheck",true);
//
//            Log.e("Em","sendparams"+" "+loginuserid+" "+loginsessionid+" "+tokenid+" "+loginsessionlogintype+" "+registration_status);
//
//            if(loginuserid.toString().length()>0 && loginsessionid.toString().length()>0 && loginsessionlogintype.toString().length()>0 && tokenid.toString().length()>0 )
//            {
//                  mSendparams  = postParamsForSession(loginuserid,loginsessionlogintype, loginsessionid, tokenid).toString();
//
//                  Log.e("Em","sendparams"+" "+mSendparams);
//
//                  String response = httpConnectorSendJsonDataLatest.postData(mSendparams);
//
//                  Log.e("Em","response session"+" "+response);
//
//                  String checkverifiedpasswordresponse = checkresponse(response);
//
//                  return checkverifiedpasswordresponse;
//            }
//            else
//            {
//                  Log.e("Em","returning null");
//
//            }

            return null;
      }


      public JSONObject postParamsForSession(String userid,String session_login_type,String session_id,String tokenid)
      {
            try
            {
                  mJsonObject = new JSONObject();
                  mJsonObject.put("user_id",userid);
                  mJsonObject.put("session_login_type",session_login_type);
                  mJsonObject.put("session_id",session_id);
                  mJsonObject.put("tokenid",tokenid);
            }
            catch (Exception e)
            {
                  e.printStackTrace();
            }

            return mJsonObject;
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
