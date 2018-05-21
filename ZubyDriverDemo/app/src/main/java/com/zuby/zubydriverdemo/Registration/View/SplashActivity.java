package com.zuby.zubydriverdemo.Registration.View;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zuby.zubydriverdemo.DocumentUpload.View.DocumentUploadActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Registration.Model.SessionModel;
import com.zuby.zubydriverdemo.Registration.presenter.ManageSessionPresenter;
import com.zuby.zubydriverdemo.Registration.presenter.SessionValid;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;

import java.util.HashMap;

//import com.example.citymapper_pc5.zubydriver.R;
//import com.example.citymapper_pc5.zubydriver.View.Presenter.interfaces.ResultInterface;

/**
 * Created by citymapper-pc5 on 17/5/18.
 */

public class SplashActivity extends Activity implements ResultInterface
{

    ProgressBar progressBarSplash;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private PreferenceManager mPreferencemanager;
    private HashMap<String,String>map,map2;
    private String mDriverid,mSessionLoginType,mSessionid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        mPreferencemanager = new PreferenceManager(SplashActivity.this);

//        map2 = mPreferencemanager.getDriverId();
//        mDriverid = map2.get("userid");

        map = mPreferencemanager.getLoginDetails();
        mSessionLoginType = map.get("session_login_type");
        mSessionid = map.get("session_id");
        mDriverid = map.get("user_id");

        Log.e("Em",":::::shared pref value::::"+" "+mDriverid+" "+mSessionLoginType+" "+mSessionid+" "+mDriverid);

        if(Build.VERSION.SDK_INT < 23)
        {
            //your code here
        }else {
            requestContactPermission();
        }


        progressBarSplash = findViewById(R.id.progressBarSplash);


        new SessionValid().show(SplashActivity.this,this);




    }

    @Override
    public void onSuccess(final String tokenid)
    {
        Log.e("Em","tokenid"+" "+tokenid);

        progressBarSplash.setVisibility(View.GONE);

        new ManageSessionPresenter().show(new ResultInterface()
        {
            @Override
            public void onSuccess(String object)
            {

            }

            @Override
            public void onSuccess(Object object)
            {
                SessionModel sessionModel = (SessionModel)object;

                Log.e("Em","sessionModel.getMessage()"+" "+sessionModel.getMessage());

                if(sessionModel.getMessage().equalsIgnoreCase("valid"))
                {
                    Toast.makeText(SplashActivity.this,"session valid!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SplashActivity.this,DocumentUploadActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("tokenid",tokenid);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                else
                {
                    Intent intent = new Intent(SplashActivity.this,HomeScreenActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("tokenid",tokenid);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailed(Object string)
            {
                Intent intent = new Intent(SplashActivity.this,HomeScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tokenid",tokenid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },SplashActivity.this,mDriverid,mSessionLoginType,mSessionid,tokenid);


    }

    @Override
    public void onSuccess(Object object)
    {

    }

    @Override
    public void onFailed(Object string)
    {
        Toast.makeText(SplashActivity.this,"Token not generated!!",Toast.LENGTH_LONG).show();

    }

    private void requestContactPermission()
    {

        int hasContactPermission = ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.RECEIVE_SMS);

        if(hasContactPermission != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]   {android.Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_ASK_PERMISSIONS);
        }else {
            //Toast.makeText(AddContactsActivity.this, "Contact Permission is already granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[]           permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                // Check if the only required permission has been granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("Permission", "Contact permission has now been granted. Showing result.");
                    Toast.makeText(this,"Contact Permission is Granted",Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Permission", "Contact permission was NOT granted.");
                }
                break;
        }
    }
}
