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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.view.DashBoard.View.DashBoardActivity;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.AgreementAdapter;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.DataItem;
import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.GetCityModelNew2;

import java.util.ArrayList;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class AgreementActivity extends AppCompatActivity
{
    private Button mAccept;
    private Bundle mBundle;
    private String mTokenid,mDriverid;
    private AgreementAdapter mAgreementAdapter;
    private RecyclerView mRecyclerview;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<GetCityModelNew2>mList=new ArrayList<>();
    private String mDocument_name[],mDocument_id[];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandcondition);
        mAccept=findViewById(R.id.accept);
        mRecyclerview=findViewById(R.id.recycleragreement);

        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        mLinearLayoutManager = new LinearLayoutManager(AgreementActivity.this);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);

        mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            mDriverid=mBundle.getString("user_id");
            mDocument_id=mBundle.getStringArray("document_id");
            mDocument_name=mBundle.getStringArray("document_name");
            mTokenid=mBundle.getString("tokenid");
            mList= (ArrayList<GetCityModelNew2>) mBundle.getSerializable("list");

            Log.e("Em","mList"+" "+mList.size()+" "+mDocument_id.length+" "+mDocument_name[1]);
//            for(int i=0;i<mList.size();i++)
//            {
//                DataItem dataItem = (DataItem) mList.get(0).getData();
//                GetCityModelNew2 getCityModelNew2 = mList.get(i);
//               Log.e("Em","data item"+" "+ getCityModelNew2.getData());
//
//            }
        }

        mAgreementAdapter = new AgreementAdapter(AgreementActivity.this);
        mRecyclerview.setAdapter(mAgreementAdapter);


        mAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AgreementActivity.this, UploadProfilePhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user_id",mDriverid);
                bundle.putStringArray("document_id",mDocument_id);
                bundle.putStringArray("document_name",mDocument_name);
                bundle.putString("tokenid",mTokenid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
