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
    private Button mAccept;
    private Bundle mBundle;
    private String mDocument_id,mDocument_name,mTokenid,mDriverid;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandcondition);
        mAccept=findViewById(R.id.accept);

        mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            mDriverid=mBundle.getString("user_id");
            mDocument_id=mBundle.getString("document_id");
            mDocument_name=mBundle.getString("document_name");
            mTokenid=mBundle.getString("tokenid");
        }


        mAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AgreementActivity.this, DashBoardActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_id",mDriverid);
                bundle.putString("document_id",mDocument_id);
                bundle.putString("document_name",mDocument_name);
                bundle.putString("tokenid",mTokenid);
                startActivity(intent);
            }
        });
    }
}
