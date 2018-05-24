package com.zuby.zubydriverdemo.view.DashBoard.View.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class HomeFragment extends Fragment
{
    private ImageView mImageCity;
    private LinearLayout mShowing_inactive;
    private String mTokenid,mDriverid,mFlag;
    private Bundle mBundle;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.homefragment,container,false);

        mBundle = getArguments();

        if(mBundle!=null)
        {
            mTokenid = mBundle.getString("tokenid");
            mDriverid = mBundle.getString("user_id");
            mFlag = mBundle.getString("flag");

            Log.e("Em",":::::::::flag::::::::"+" "+mFlag+" "+mDriverid+" "+mTokenid);
        }

        mImageCity=view.findViewById(R.id.city);
        mImageCity.setColorFilter(Color.parseColor("#20C1D3"));
        return view;




    }
}
