package com.zuby.zubydriverdemo.view.Registration.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zuby.zubydriverdemo.view.DocumentUpload.View.LegalDocActivity;
import com.zuby.zubydriverdemo.view.Login.Presenter.LoginPresenter;
import com.zuby.zubydriverdemo.view.Login.View.LoginActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.Registration.presenter.RegisterPresenter;
import com.zuby.zubydriverdemo.view.SetPassword.Presenter.SetPasswordPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private boolean mPasswordValue;

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

        mPasswordValue = isValidPassword(et_password.getText().toString());

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
                if (isValidPassword(et_password.getText().toString()) && et_firstname.getText().toString().length()>0 && et_lastname.getText().toString().length()>0 && et_city.getText().toString().length()>0)
                {
                    progressBar.setVisibility(View.VISIBLE);
                new RegisterPresenter().show(new ResultInterface() {
                    @Override
                    public void onSuccess(String object) {

                    }

                    @Override
                    public void onSuccess(Object object) {
                        progressBar.setVisibility(View.VISIBLE);

                        new SetPasswordPresenter().show(new ResultInterface() {
                            @Override
                            public void onSuccess(String object) {

                            }

                            @Override
                            public void onSuccess(Object object) {
                                Log.e("Em", "on success in setpasswrd");


                                new LoginPresenter().show(new ResultInterface() {
                                    @Override
                                    public void onSuccess(String object) {

                                    }

                                    @Override
                                    public void onSuccess(Object object) {
                                        Intent intent = new Intent(RegistrationActivity.this, LegalDocActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("tokenid", mTokenid);
                                        bundle.putString("user_id", mDriverid);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailed(Object string) {

                                    }
                                }, RegistrationActivity.this, "+91", mMobileno, et_password.getText().toString(), "Android", "driver_android", "7", "driver_session", "1", "Asia/Calcutta", mTokenid, "device_id");
                            }

                            @Override
                            public void onFailed(Object string) {

                            }
                        }, RegistrationActivity.this, "+91", mMobileno, et_password.getText().toString(), mTokenid);
                    }

                    @Override
                    public void onFailed(Object string) {

                    }
                }, RegistrationActivity.this, et_code.getText().toString(), et_phonenumber.getText().toString(), et_firstname.getText().toString(), et_lastname.getText().toString(), "driver", "Asia/Calcutta", mTokenid);
            }
            else
                {
//                    dialogBox();
                    et_password.setError( "Password not valid! Please enter 1 special character,one Uppercase letter, one number and length of password must be more than 8" );
                    if( et_firstname.getText().toString().length() == 0 )
                        et_firstname.setError( "First name is required!" );
                    if( et_lastname.getText().toString().length() == 0 )
                        et_lastname.setError( "Last name is required!" );
//                    if( et_password.getText().toString().length() == 0 )
                    if( et_city.getText().toString().length() == 0 )
                        et_city.setError( "City name is required!" );

                }
            }
        });


    }

    private void dialogBox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Password not valid! Please enter 1 special character,one Uppercase letter, one number and length of password should be more than 8")
                .setTitle("Warning");



        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked cancel button
//            }
//        });

        AlertDialog alert =builder.create();
        alert.show();
    }

    public static boolean isValidPassword(final String password)
    {
        Pattern pattern;
        Matcher matcher;
        //(?=.*[0-9])=must contain one number at starting
        //(?=.*[A-Z])=must contain an uppercase character
        //(?=.*[@#$%^&+=!]) must contain special character

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{7,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        Log.e("Em","matcher"+" "+matcher+" "+matcher.matches());

        return matcher.matches();

    }
}
