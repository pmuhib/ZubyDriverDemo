package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class LegalDocActivity extends Activity
{
    private TextView mContinuebutton;
    private CheckBox mCheckdoc;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legalcontent);
        mContinuebutton=findViewById(R.id.continuebutton);
        mCheckdoc=findViewById(R.id.checkdoc);

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
                startActivity(intent);
            }
        });
    }
}
