package com.zuby.zubydriverdemo.view.DashBoard.View.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class AccountFragment extends Fragment {
    View view;
     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.account_fragment,container,false);
        return view;
    }
}
