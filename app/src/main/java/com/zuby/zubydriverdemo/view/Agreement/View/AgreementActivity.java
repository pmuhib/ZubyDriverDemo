package com.zuby.zubydriverdemo.view.Agreement.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.view.DashBoard.View.DashBoardActivity;
import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

public class AgreementActivity extends Activity
{
    private Button mDocument_submit;
    private Bundle mBundle;
    private String mTokenid;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agreement);
        mDocument_submit=findViewById(R.id.document_submit);

        mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
        }

        mDocument_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AgreementActivity.this, DashBoardActivity.class);
                startActivity(intent);
            }
        });
    }

}
