package com.zuby.zubydriverdemo.view.DashBoard.Presenter;

import android.content.Context;

import com.zuby.zubydriverdemo.Presenter.interfaces.ResultInterface;

public class AccountFragmentPresenter {

    Context mContext;
    private ResultInterface mResultInterface;
    public void show(ResultInterface mResultInterface,Context mContext)
    {
        this.mResultInterface=mResultInterface;
        this.mContext=mContext;
    }

}
