package com.zuby.zubydriverdemo.Registration.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.zuby.zubydriverdemo.HasPassword.Model.HassPasswordModel;
import com.zuby.zubydriverdemo.HasPassword.Presenter.HassPasswordPresenter;
import com.zuby.zubydriverdemo.Login.View.LoginActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Registration.Model.Data;
import com.zuby.zubydriverdemo.Registration.Model.Level1RegisterModel;
import com.zuby.zubydriverdemo.Registration.Model.NewRegisterModel;
import com.zuby.zubydriverdemo.Registration.presenter.AddMobileNumberPresenter;
import com.zuby.zubydriverdemo.SetPassword.View.SetPasswordActivity;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public class EnterPhoneNumberActivity extends Activity
{
    private EditText mAddnumber;
    private TextView mNext;
    private CountryCodePicker ccpLogin;
    private String mCountryCode,mTokenid,mPhonenumber;
    private Bundle mBundle;
    private ProgressBar progressBar;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterphone);
        progressBar=findViewById(R.id.progressBar);

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
        }

        mAddnumber=findViewById(R.id.addnumber);
        mNext=findViewById(R.id.next);

        mPhonenumber=mAddnumber.getText().toString();

        ccpLogin = findViewById(R.id.ccpLogin);

        ccpLogin.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener()
        {
            @Override
            public void onCountrySelected()
            {
                mCountryCode = ccpLogin.getSelectedCountryCode();
                Log.d("Country Code", mCountryCode);
            }
        });


        mNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                progressBar.setVisibility(View.VISIBLE);

                if(mAddnumber.getText().toString()!=null && mAddnumber.getText().toString().length()>0)
                {

                    new AddMobileNumberPresenter().show(new ResultInterface()
                    {
                        @Override
                        public void onSuccess(String object)
                        {

                        }

                        @Override
                        public void onSuccess(Object object)
                        {

                            Data data = (Data) object;
                            progressBar.setVisibility(View.GONE);
                            mNext.setVisibility(View.GONE);
                            Log.e("Zuby", "object" + " " + object + " " + data.getStatus() + " " + data.getUserId()/*+" "+data.getType()*/);
                            if (data!=null && data.getStatus().equalsIgnoreCase("active") )
                            {

                                new HassPasswordPresenter().show(new ResultInterface()
                                {
                                    @Override
                                    public void onSuccess(String object)
                                    {

                                    }

                                    @Override
                                    public void onSuccess(Object object)
                                    {

                                        HassPasswordModel haspassword = (HassPasswordModel)object;

                                        Log.e("Zuby","haspassword"+" "+haspassword.getMessage());
                                        if(haspassword.getMessage().equalsIgnoreCase("success"))
                                        {
                                            Intent intent = new Intent(EnterPhoneNumberActivity.this,LoginActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("tokenid",mTokenid);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Intent intent = new Intent(EnterPhoneNumberActivity.this, SetPasswordActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("tokenid",mTokenid);
                                            bundle.putString("mobile_no",mAddnumber.getText().toString());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onFailed(Object string)
                                    {

                                    }
                                },EnterPhoneNumberActivity.this,"+91",mAddnumber.getText().toString(),mTokenid);


                            }
//                            else if(data.getStatus().equalsIgnoreCase("active")/* && data.getType().equalsIgnoreCase("failure")*/)
//                            {
//                                    Intent intent = new Intent(EnterPhoneNumberActivity.this,LoginActivity.class);
//                                    startActivity(intent);
//                            }
//                            else
//                            {
//
//                            }

//                            Log.e("Em", "mobile number" + " " + data.getMobileNo());


                        }

                        @Override
                        public void onFailed(Object object)
                        {

                            progressBar.setVisibility(View.GONE);
                            mNext.setVisibility(View.GONE);

                            NewRegisterModel level1RegisterModel = (NewRegisterModel)object;
                                Log.e("Em","object failure"+" "+level1RegisterModel.getType()+" "+level1RegisterModel.getMessage()+" "+level1RegisterModel.getData().getStatus());

                                  if(level1RegisterModel.getType().equalsIgnoreCase("failure") && level1RegisterModel.getMessage().equalsIgnoreCase("Mobile no. already exists") && level1RegisterModel.getData().getStatus().equalsIgnoreCase("active"))
                                  {

                                      new HassPasswordPresenter().show(new ResultInterface()
                                      {
                                          @Override
                                          public void onSuccess(String object)
                                          {

                                          }

                                          @Override
                                          public void onSuccess(Object object)
                                          {

                                              HassPasswordModel haspassword = (HassPasswordModel)object;

                                              Log.e("Zuby","haspassword"+" "+haspassword.getMessage());
                                              if(haspassword.getMessage().equalsIgnoreCase("success"))
                                              {
                                                  Intent intent = new Intent(EnterPhoneNumberActivity.this,LoginActivity.class);
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("tokenid",mTokenid);
                                                  intent.putExtras(bundle);
                                                  startActivity(intent);
                                              }
                                              else
                                              {
                                                  Intent intent = new Intent(EnterPhoneNumberActivity.this, SetPasswordActivity.class);
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("tokenid",mTokenid);
                                                  bundle.putString("mobile_no",mAddnumber.getText().toString());
                                                  intent.putExtras(bundle);
                                                  startActivity(intent);
                                              }
                                          }

                                          @Override
                                          public void onFailed(Object string)
                                          {

                                          }
                                      },EnterPhoneNumberActivity.this,"+91",mAddnumber.getText().toString(),mTokenid);
                                  }
                                  else if(level1RegisterModel.getType().equalsIgnoreCase("success")&&  level1RegisterModel.getData().getStatus().equalsIgnoreCase("inactive"))
                                  {
                                      Log.e("Zuby", "inactive"+" "+level1RegisterModel.getData().getUser_id());

                                      Intent intent = new Intent(EnterPhoneNumberActivity.this, EnterOtp.class);
                                      Bundle bundle = new Bundle();
                                      bundle.putString("tokenid", mTokenid);
                                      bundle.putString("mobilenumber", mAddnumber.getText().toString());
                                      bundle.putString("userid", "CAB3325_00000004");
                                      intent.putExtras(bundle);
                                      startActivity(intent);
                                  }
                                  else
                                  {

                                      Log.e("Zuby", "inactive"+" "+level1RegisterModel.getData().getUser_id());
                                      Intent intent = new Intent(EnterPhoneNumberActivity.this, EnterOtp.class);
                                      Bundle bundle = new Bundle();
                                      bundle.putString("tokenid", mTokenid);
                                      bundle.putString("mobilenumber", mAddnumber.getText().toString());
                                      bundle.putString("userid", level1RegisterModel.getData().getUser_id());
                                      intent.putExtras(bundle);
                                      startActivity(intent);
                                  }


                        }
                    }, EnterPhoneNumberActivity.this, "+91", mAddnumber.getText().toString(), "Asia/Calcutta", mTokenid);

                }
                }
        });

    }
}
