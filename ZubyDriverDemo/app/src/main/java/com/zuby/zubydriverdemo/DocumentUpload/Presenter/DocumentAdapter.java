package com.zuby.zubydriverdemo.DocumentUpload.Presenter;

/**
 * Created by citymapper-pc5 on 20/5/18.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuby.zubydriverdemo.DocumentUpload.View.DocumentPreviewActivity;
import com.zuby.zubydriverdemo.DocumentUpload.View.DocumentUploadActivity;
import com.zuby.zubydriverdemo.R;
import com.zuby.zubydriverdemo.Registration.View.SplashActivity;

import java.util.ArrayList;

/**
 * Created by citymapper-pc5 on 30/4/18.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder>
{
    Context mContext;
//    ArrayList<BeanForDocs> mBitmapList;
    int mFlag;
    String driverid,city,mTokenid,countrycode,phoneno,sessionlogintype,mDocument_id,mDocument_name;
    private int pos;
    private int click=-1;

    public DocumentAdapter(Context context,String mTokenid,String mDocument_id,String mDocument_name/*, ArrayList<BeanForDocs>bitmaplist*//* FragmentActivity fragmentActivity,String driverid,String city,String tokenid,String countrycode,String phoneno,String sessionlogintype,String document_id,String document_name,boolean oncheck,int mFlag,int pos*//*,ClickOfViews clickOfViews*/)
    {
        mContext = context;
        this.mTokenid=mTokenid;
        this.mDocument_id=mDocument_id;
        this.mDocument_name=mDocument_name;
        Log.e("Em","tokenid in adapter"+" "+mTokenid);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(mContext).inflate(R.layout.docadapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
//        holder.document_name.setText(mBitmapList.get(position).getDoc_name());

//        if(click>=2)
//        {
//            SharedPreferences preferences =  mContext.getSharedPreferences("PREF", 0);
//            preferences.edit().remove("click").commit();
//        }

//        if(holder.okay.getVisibility()==View.VISIBLE)
//        {
//            holder.okay.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            holder.next.setVisibility(View.GONE);
//        }

        holder.document_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                holder.okay.setVisibility(View.VISIBLE);
                holder.next.setVisibility(View.GONE);

//                int pos = getAdapterPosition();


                Intent intent = new Intent(mContext,DocumentPreviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tokenid",mTokenid);
                bundle.putString("document_id",mDocument_id);
                bundle.putString("document_name",mDocument_name);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()
    {
//        return mBitmapList.size();
            return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView document_name;
        CardView document_card;
        ImageView okay,next;

        public ViewHolder(View itemView)
        {
            super(itemView);

            document_name = itemView.findViewById(R.id.document_name);
            document_card = itemView.findViewById(R.id.document_card);
            okay=itemView.findViewById(R.id.okay);
            next=itemView.findViewById(R.id.next);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();

                    Log.e("Em","adapter position"+" "+pos);
                }
            });
        }
    }



}
