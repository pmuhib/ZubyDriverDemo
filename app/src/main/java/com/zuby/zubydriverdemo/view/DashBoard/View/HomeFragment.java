package com.zuby.zubydriverdemo.view.DashBoard.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class HomeFragment extends Fragment
{
    private ImageView mImageCity;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.homefragment,container,false);

        mImageCity=view.findViewById(R.id.city);
        mImageCity.setColorFilter(Color.parseColor("#20C1D3"));
        return view;




    }
}
