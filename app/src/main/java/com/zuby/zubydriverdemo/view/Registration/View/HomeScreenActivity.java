package com.zuby.zubydriverdemo.view.Registration.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.view.Login.View.LoginActivity;
import com.zuby.zubydriverdemo.R;


/**
 * Created by citymapper-pc5 on 17/5/18.
 */

public class HomeScreenActivity extends Activity
{
    private Button mButtonsignin,mButtonregister;
    private Bundle mBundle;
    private String mTokenId;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        mButtonsignin = findViewById(R.id.sign_in);
        mButtonregister = findViewById(R.id.register);

        mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            mTokenId = mBundle.getString("tokenid");
        }


        mButtonsignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tokenid",mTokenId);
                intent.putExtras(bundle);
                startActivity(intent);



            }
        });


        mButtonregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeScreenActivity.this,EnterPhoneNumberActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tokenid",mTokenId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
