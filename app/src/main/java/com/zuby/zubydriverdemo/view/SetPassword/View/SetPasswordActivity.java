package com.zuby.zubydriverdemo.view.SetPassword.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zuby.zubydriverdemo.view.Login.View.LoginActivity;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.SetPassword.Model.SetPasswordModel;
import com.zuby.zubydriverdemo.view.SetPassword.Presenter.SetPasswordPresenter;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class SetPasswordActivity extends AppCompatActivity
{
    private Bundle mBundle;
    private String mTokenid,mPhonenumber;;
    private EditText mSet_password;
    private Button mReset;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        Log.e("Zuby","setpassword");

        mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            mPhonenumber = mBundle.getString("mobile_no");
        }

        mSet_password=findViewById(R.id.set_password);
        mReset=findViewById(R.id.reset);

        mReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                new SetPasswordPresenter().show(new ResultInterface()
                {
                    @Override
                    public void onSuccess(String object)
                    {

                    }

                    @Override
                    public void onSuccess(Object object)
                    {

                        SetPasswordModel setPasswordModel =(SetPasswordModel)object;
                        if(setPasswordModel.getType().equalsIgnoreCase("success"))
                        {
                            Intent intent = new Intent(SetPasswordActivity.this, LoginActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("tokenid",mTokenid);
                            startActivity(intent);
                        }
                        else
                        {

                            // Yahan activity back pr jani chahiye

                            Toast.makeText(SetPasswordActivity.this,setPasswordModel.getMessage(),Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailed(Object string)
                    {

                    }
                },SetPasswordActivity.this,"+91",mPhonenumber,mSet_password.getText().toString(),mTokenid);


            }
        });



    }

}
