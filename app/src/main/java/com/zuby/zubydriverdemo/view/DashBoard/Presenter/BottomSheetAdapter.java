package com.zuby.zubydriverdemo.view.DashBoard.Presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zuby.zubydriverdemo.view.DocumentUpload.Presenter.DocumentAdapter;

/**
 * Created by citymapper-pc5 on 22/5/18.
 */

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
