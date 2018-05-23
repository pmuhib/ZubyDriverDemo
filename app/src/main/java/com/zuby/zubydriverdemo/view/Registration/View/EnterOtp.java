package com.zuby.zubydriverdemo.view.Registration.View;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.Registration.presenter.OtpPresenter;
import com.zuby.zubydriverdemo.view.Registration.presenter.VerifyOtpPresenter;
import com.zuby.zubydriverdemo.view.SetPassword.View.SetPasswordActivity;
import com.zuby.zubydriverdemo.Utils.PreferenceManager;

import java.util.HashMap;

/**
 * Created by citymapper-pc5 on 18/5/18.
 */

public class EnterOtp extends Activity
{
    private Bundle mBundle;
    private String mTokenid,mDriverId,mMobilenumber;
    private PreferenceManager mPreferenceManager;
    private HashMap<String,String>map;
    private TextView mTimer,mResend,mNext,mEnter_otp;
    private  BroadcastReceiver receiver;
    private int flag = 0;
    private EditText pin_first_edittext,pin_second_edittext,pin_third_edittext,pin_forth_edittext;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        progressBar=findViewById(R.id.progressBar);
        mTimer=findViewById(R.id.timer);
        mResend=findViewById(R.id.resend);
        mNext=findViewById(R.id.next);
        mEnter_otp=findViewById(R.id.enter_otp);
        final EditText pin_first_edittext = findViewById(R.id.pin_first_edittext);
        final EditText pin_second_edittext = findViewById(R.id.pin_second_edittext);
        final EditText pin_third_edittext = findViewById(R.id.pin_third_edittext);
        final EditText pin_forth_edittext = findViewById(R.id.pin_forth_edittext);


        Log.e("Em","enter otp");

        pin_first_edittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    pin_second_edittext.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        pin_second_edittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    pin_third_edittext.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

        pin_third_edittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (s.length() ==1) {
                    pin_forth_edittext.requestFocus();
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });



        mPreferenceManager = new PreferenceManager(EnterOtp.this);

        map=mPreferenceManager.getDriverId();
        mDriverId =  map.get("userid");

        Log.e("Em",":::mDriverId::::"+" "+mDriverId);

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            mMobilenumber = mBundle.getString("mobilenumber");
            flag=mBundle.getInt("flag");
        }


        mEnter_otp.setText(Html.fromHtml("Enter the 4-digit code sent to <br>"+ mMobilenumber).toString());
        receiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    final String message = intent.getStringExtra("message");

                    char[] charArray = message.toCharArray();

                    Log.e("Zuby","charArray"+" "+charArray+" "+charArray[charArray.length - 5]);

                    pin_first_edittext.setText(charArray[charArray.length - 5]);
                    pin_second_edittext.setText(charArray[charArray.length -4]);
                    pin_third_edittext.setText(charArray[charArray.length - 3]);
                    pin_forth_edittext.setText(charArray[charArray.length - 2]);
                }
            }
        };

        new OtpPresenter().show(new ResultInterface()
        {
            @Override
            public void onSuccess(String object)
            {

                Log.e("Em",":::::object::::"+" "+object);
            }

            @Override
            public void onSuccess(Object object) {


                mNext.setVisibility(View.VISIBLE);

                Toast.makeText(EnterOtp.this,"Success",Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailed(Object string)
            {

            }
        },EnterOtp.this,mTokenid,"CAB3325_00000004",mMobilenumber);


        mResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                mResend.setVisibility(View.GONE);
                mTimer.setVisibility(View.VISIBLE);

                new OtpPresenter().show(new ResultInterface()
                {
                    @Override
                    public void onSuccess(String object)
                    {

                        Log.e("Em",":::::object::::"+" "+object);
//                        mNext.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onSuccess(Object object)
                    {
                        mNext.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onFailed(Object string) {

                    }
                },EnterOtp.this,mTokenid,"CAB3325_00000004",mMobilenumber);


                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished)
                    {
                        mTimer.setText("" + millisUntilFinished / 1000);
                        //here you can have your logic to set text to edittext
                    }

                    public void onFinish()
                    {
                        mTimer.setVisibility(View.GONE);
                        mResend.setVisibility(View.VISIBLE);
                        mResend.setText("Resend Code");
                        mResend.setClickable(true);

                    }

                }.start();



            }
        });

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished)
            {
                mTimer.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish()
            {
                mTimer.setVisibility(View.GONE);
                mResend.setVisibility(View.VISIBLE);
                mResend.setText("Resend Code");
                mResend.setClickable(true);

            }

        }.start();


        mNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                progressBar.setVisibility(View.VISIBLE);
                new VerifyOtpPresenter().show(new ResultInterface() {
                    @Override
                    public void onSuccess(String object) {

                    }

                    @Override
                    public void onSuccess(Object object) {
                        Log.e("Em", "object" + " " + object);
                        progressBar.setVisibility(View.GONE);
                        if (flag == 0) {
                            Intent intent = new Intent(EnterOtp.this, RegistrationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("driverid", mDriverId);
                            bundle.putString("tokenid", mTokenid);
                            bundle.putString("mobileno", mMobilenumber);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(EnterOtp.this, SetPasswordActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("tokenid", mTokenid);
                            bundle.putString("mobileno", mMobilenumber);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailed(Object string)
                    {

                    }
                },EnterOtp.this,mTokenid,mDriverId,mMobilenumber,(pin_first_edittext.getText().toString()+pin_second_edittext.getText().toString()+pin_third_edittext.getText().toString()+pin_forth_edittext.getText().toString()));

            }
        });

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
