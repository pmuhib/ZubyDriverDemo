package com.zuby.zubydriverdemo.Registration.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zuby.zubydriverdemo.Login.View.LoginActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Registration.Model.Data;
import com.zuby.zubydriverdemo.Registration.presenter.RegisterPresenter;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public class RegistrationActivity extends Activity
{
    private Bundle mBundle;
    private String mDriverid,mTokenid,mMobileno;
    private EditText et_firstname,et_lastname,et_phonenumber,et_email,et_password,et_city,et_code;
    private TextView mRegistrationclick;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar=findViewById(R.id.progressBar);
        et_firstname=findViewById(R.id.et_firstname);
        et_lastname=findViewById(R.id.et_lastname);
        et_phonenumber=findViewById(R.id.et_phonenumber);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        et_city=findViewById(R.id.et_city);
        et_code=findViewById(R.id.et_code);
        mRegistrationclick=findViewById(R.id.registrationclick);

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mDriverid = mBundle.getString("driverid");
            mTokenid = mBundle.getString("tokenid");
            mMobileno = mBundle.getString("mobileno");
            Log.e("Zuby","driverid"+" "+mDriverid+" "+mTokenid);

            et_phonenumber.setText(mMobileno);
            et_phonenumber.setEnabled(false);
        }


        mRegistrationclick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                mRegistrationclick.setVisibility(View.GONE);
                new RegisterPresenter().show(new ResultInterface()
                {
                    @Override
                    public void onSuccess(String object)
                    {

                    }

                    @Override
                    public void onSuccess(Object object)
                    {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("tokenid",mTokenid);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailed(Object string)
                    {

                    }
                },RegistrationActivity.this,et_code.getText().toString(),et_phonenumber.getText().toString(),et_firstname.getText().toString(),et_lastname.getText().toString(),"driver","Asia/Calcutta",mTokenid);
            }
        });


    }
}
