package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<String> mUrls;
    private Context mContext;

    public InfoAdapter(List<String> urls) {
        mUrls = urls;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        InfoViewHolder myViewHolder=new InfoViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.info_recycleview_item,parent,false));

        mContext=parent.getContext();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.mItemTitle.setText(mUrls.get(position));
    }


    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView mItemTitle;
        TextView mWhoTime;
        public InfoViewHolder(View itemView) {
            super(itemView);
            mItemTitle=(TextView)itemView.findViewById(R.id.item_titile);
            mWhoTime=(TextView)itemView.findViewById(R.id.item_who_time);
        }
    }
}
