package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DashBoard.View.DashBoardActivity;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class AgreementActivity extends Activity
{
    Button mAccept;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandcondition);

        mAccept=findViewById(R.id.accept);

        mAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AgreementActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}
