package com.zuby.zubydriverdemo.view.Registration.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zuby.zubydriverdemo.view.DocumentUpload.View.DocumentUploadActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DocumentUpload.View.LegalDocActivity;
import com.zuby.zubydriverdemo.view.Registration.Model.SessionModel;
import com.zuby.zubydriverdemo.view.Registration.presenter.ManageSessionPresenter;
import com.zuby.zubydriverdemo.view.Registration.presenter.SessionValid;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;

import java.util.HashMap;


/**
 * Created by citymapper-pc5 on 17/5/18.
 */

public class SplashActivity extends AppCompatActivity implements ResultInterface
{

    ProgressBar progressBarSplash;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private PreferenceManager mPreferencemanager;
    private HashMap<String,String>map,map2;
    private HashMap<String,Integer>map3;
    private String mDriverid,mSessionLoginType,mSessionid;
    private String mDocument_id[],mDocument_name[];
    private int mArraySizeOfDoc;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        mPreferencemanager = new PreferenceManager(SplashActivity.this);

        map = mPreferencemanager.getLoginDetails();
        mSessionLoginType = map.get("session_login_type");
        mSessionid = map.get("session_id");
        mDriverid = map.get("user_id");

        map3= mPreferencemanager.getArraySize();
        mArraySizeOfDoc = map3.get("size");
        map2 = mPreferencemanager.getDocumentDetails();
//        for(int i=0;i<mArraySizeOfDoc;i++) {
//            mDocument_id[i] = map2.get("document_id");
//            mDocument_name[i] = map2.get("document_name");
//
//            Log.e("Em","::::::::document_name::::::"+" "+mDocument_id[i]);
//        }
        Log.e("Em",":::::shared pref value::::"+" "+mDriverid+" "+mSessionLoginType+" "+mSessionid+" "+mDriverid);

        if(Build.VERSION.SDK_INT < 23)
        {
            //your code here
        }
        else {
            requestContactPermission();
            isStoragePermissionGranted();

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
                    Intent intent = new Intent(SplashActivity.this,LegalDocActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("tokenid",tokenid);
                    bundle.putString("user_id",mDriverid);
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


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Zuby","Permission is granted");
                return true;
            } else {

                Log.v("Zuby","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Zuby","Permission is granted");
            return true;
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
