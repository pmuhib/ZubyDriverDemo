package com.zuby.zubydriverdemo.view.DocumentUpload.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuby.zubydriverdemo.R;

/**
 * Created by citymapper-pc5 on 21/5/18.
 */

public class AgreementAdapter extends RecyclerView.Adapter<AgreementAdapter.ViewHolder>
{
    Context mContext;

    public AgreementAdapter(Context mContext)
    {
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(mContext).inflate(R.layout.agreementadapter,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {


    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
