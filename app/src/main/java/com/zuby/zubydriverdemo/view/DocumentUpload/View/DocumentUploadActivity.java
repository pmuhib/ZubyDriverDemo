package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.view.Agreement.View.AgreementActivity;
import com.zuby.zubydriverdemo.view.DocumentUpload.Model.GetCityModel;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.CheckDocPresenter;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.DocumentAdapter;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class DocumentUploadActivity extends Activity
{
    private RecyclerView mRecyclerview;
    private LinearLayoutManager mLinearLayoutManager;
    private String mTokenid;
    private Bundle mBundle;
    private DocumentAdapter mAdapter;
    private Button document_submit;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentupload);
        document_submit=findViewById(R.id.document_submit);
        mLinearLayoutManager = new LinearLayoutManager(DocumentUploadActivity.this);

        mRecyclerview=findViewById(R.id.list_to_show_docs);

        mRecyclerview.setLayoutManager(mLinearLayoutManager);

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            Log.e("Em","tokenid in docupload"+" "+mTokenid);
        }


        document_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DocumentUploadActivity.this, AgreementActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tokenid",mTokenid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        new CheckDocPresenter().show(new ResultInterface()
        {
            @Override
            public void onSuccess(String object)
            {

            }

            @Override
            public void onSuccess(Object object)
            {

                GetCityModel getCityModel =(GetCityModel)object;

                Log.e("Zuby",":::::::::OBJECT::::::::"+" "+getCityModel.getMessage());

            }

            @Override
            public void onFailed(Object string)
            {
                mAdapter = new DocumentAdapter(DocumentUploadActivity.this,mTokenid,"","");
                mRecyclerview.setAdapter(mAdapter);
                Log.e("Em","tokenid in docupload"+" "+mTokenid);


            }
        },DocumentUploadActivity.this,"noida",mTokenid,"driver");


    }
}
