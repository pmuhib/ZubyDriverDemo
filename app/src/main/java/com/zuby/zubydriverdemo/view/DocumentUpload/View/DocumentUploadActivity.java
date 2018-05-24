package com.zuby.zubydriverdemo.view.DocumentUpload.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zuby.zubydriverdemo.Utils.PreferenceManager;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.CheckDocPresenter;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.DataItem;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.DocumentAdapter;
import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.GetCityModelNew2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by citymapper-pc5 on 19/5/18.
 */

public class DocumentUploadActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerview;
    private LinearLayoutManager mLinearLayoutManager;
    private String mTokenid,mDriverid;
    private Bundle mBundle;
    private DocumentAdapter mAdapter;
    private Button document_submit;
    private ArrayList<DataItem>mListdoc;
    private ProgressBar mProgressbar;
    private GetCityModelNew2 mDataItem;
    private String mDocumnet_id[],mDocumnet_name[];
    private PreferenceManager mPreferenceManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.documentupload);
        document_submit=findViewById(R.id.document_submit);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        mLinearLayoutManager = new LinearLayoutManager(DocumentUploadActivity.this);

        mProgressbar = findViewById(R.id.progressbar);
        mRecyclerview=findViewById(R.id.list_to_show_docs);

        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mProgressbar.setVisibility(View.VISIBLE);

        mBundle = getIntent().getExtras();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            mDriverid = mBundle.getString("user_id");
            Log.e("Em","tokenid in docupload"+" "+mTokenid);
        }


        new CheckDocPresenter().show(new ResultInterface()
        {
            @Override
            public void onSuccess(String object)
            {

            }

            @Override
            public void onSuccess(Object object)
            {
                mProgressbar.setVisibility(View.GONE);
                mPreferenceManager= new PreferenceManager(DocumentUploadActivity.this);
                mDataItem=(GetCityModelNew2)object;
//                mListdoc.add(dataItem);
//                mDocument_id = String.valueOf(mDataItem.getData().get());
//                mDocument_name = dataItem.getDocumentName();
                mDocumnet_id=new String[mDataItem.getData().size()];
                mDocumnet_name= new String[mDataItem.getData().size()];
                for (int i=0;i<mDataItem.getData().size();i++){
                    mDocumnet_id[i]=String.valueOf(mDataItem.getData().get(i).getDocumentId());
                    mDocumnet_name[i]=mDataItem.getData().get(i).getDocumentName();

                    mPreferenceManager.saveArraySize(mDataItem.getData().size());
                    mPreferenceManager.saveDocumentDetails(mDocumnet_id[i],mDocumnet_name[i]);
                }
                Log.e("Zuby",":::::::::OBJECT::::::::"+" "+mDataItem.getData().size());

                for(int i=0;i<mDocumnet_id.length;i++)
                {
                    Log.e("Em","array length"+" "+mDocumnet_name[i]);
                }

                mAdapter = new DocumentAdapter(DocumentUploadActivity.this,mTokenid, (ArrayList<DataItem>) mDataItem.getData());
                mRecyclerview.setAdapter(mAdapter);
                Log.e("Em","tokenid in docupload"+" "+mTokenid);

            }

            @Override
            public void onFailed(Object string)
            {
                GetCityModelNew2 getCityModelNew2 = (GetCityModelNew2)string;
                Log.e("Em",":::::::::getCityModelNew2:::::::::"+" "+getCityModelNew2.getMessage());

                Toast.makeText(DocumentUploadActivity.this,getCityModelNew2.getMessage(),Toast.LENGTH_LONG).show();

            }
        },DocumentUploadActivity.this,"noida",mTokenid,"driver");

        document_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    Intent intent = new Intent(DocumentUploadActivity.this, AgreementActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("tokenid", mTokenid);
                    bundle.putString("user_id", mDriverid);
                    bundle.putStringArray("document_id", mDocumnet_id);
                    bundle.putStringArray("document_name", mDocumnet_name);
                    bundle.putSerializable("list", (Serializable) mDataItem.getData());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


    }
}
