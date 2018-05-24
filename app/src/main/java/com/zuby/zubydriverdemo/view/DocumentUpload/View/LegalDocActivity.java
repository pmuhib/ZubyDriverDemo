package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class LegalDocActivity extends AppCompatActivity
{
    private TextView mContinuebutton;
    private CheckBox mCheckdoc;
    private Bundle mBundle;
    private String mDriverid,mTokenid;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legalcontent);
        mContinuebutton=findViewById(R.id.continuebutton);
        mCheckdoc=findViewById(R.id.checkdoc);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
//        actionbar.setDisplayShowTitleEnabled(false);
//        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F9F9F9")));


        mBundle= getIntent().getExtras();

        if(mBundle!=null)
        {
            mDriverid = mBundle.getString("user_id");
            mTokenid = mBundle.getString("tokenid");
        }

        mContinuebutton.setEnabled(false);

        mCheckdoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    mContinuebutton.setEnabled(true);

                }else{
                    mContinuebutton.setEnabled(false);
                }

            }
        });

        mContinuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LegalDocActivity.this,DocumentUploadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_id",mDriverid);
                bundle.putString("tokenid",mTokenid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
