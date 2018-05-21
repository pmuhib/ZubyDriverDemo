package com.zuby.zubydriverdemo.Login.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zuby.zubydriverdemo.DocumentUpload.View.DocumentUploadActivity;
import com.zuby.zubydriverdemo.Login.Model.LoginModel;
import com.zuby.zubydriverdemo.Login.Presenter.LoginPresenter;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Registration.View.EnterOtp;
import com.zuby.zubydriverdemo.Registration.View.EnterPhoneNumberActivity;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;

import java.util.HashMap;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public class LoginActivity extends Activity
{
    private TextView mGetmoving,mButtoncontinue,mForgot_password;
    private EditText mEnterphone,mEnterpassword;
    private String mTokenid,mAndroidVersion,mMacAddress,mDriverid;
    private Bundle mBundle;
    private PreferenceManager mPreferencemanager;
    private HashMap<String,String>map;

    @Override
    public void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mButtoncontinue=findViewById(R.id.buttoncontinue);
        mGetmoving=findViewById(R.id.getmoving);
        mEnterphone=findViewById(R.id.enterphone);
        mEnterpassword=findViewById(R.id.enterpassword);
        mForgot_password=findViewById(R.id.forgot_password);

        mGetmoving.setText(Html.fromHtml("Enter your phone <br>"+"number").toString());

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        mMacAddress = wInfo.getMacAddress();


        mPreferencemanager = new PreferenceManager(LoginActivity.this);

        map = mPreferencemanager.getDriverId();
        mDriverid = map.get("userid");

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
        }


        // Android version
        mAndroidVersion = android.os.Build.VERSION.RELEASE;


        mForgot_password.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(LoginActivity.this, EnterOtp.class);
                Bundle bundle = new Bundle();
                bundle.putInt("flag",1);
                bundle.putString("tokenid", mTokenid);
                bundle.putString("mobilenumber", mEnterphone.getText().toString());
                bundle.putString("userid", mDriverid);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        mButtoncontinue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(mEnterphone.getText().toString().length()>0 && mEnterpassword.getText().toString().length()>0)
                {
                    new LoginPresenter().show(new ResultInterface()
                    {
                        @Override
                        public void onSuccess(String object)
                        {

                        }

                        @Override
                        public void onSuccess(Object object)
                        {
                            LoginModel loginmodel = (LoginModel)object;

                            Intent intent = new Intent(LoginActivity.this, DocumentUploadActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("tokenid",mTokenid);
                            intent.putExtras(bundle);
                            startActivity(intent);


                        //    Log.e("Em",":::::::::::loginmodel::::::::::"+" "+loginmodel.getData().getSessionId());

                        }

                        @Override
                        public void onFailed(Object string)
                        {

                        }
                    },LoginActivity.this,"+91",mEnterphone.getText().toString(),mEnterpassword.getText().toString(),"Android","driver_android",mAndroidVersion,"driver_session","1","Asia/Calcutta",mTokenid,mMacAddress);
                }

            }
        });




    }
}
