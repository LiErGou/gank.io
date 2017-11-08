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

    private OnItemClickLitener mOnItemClickLitener;
    private List<String> mUrls;
    private Context mContext;
    protected List<String> mWhos;
    protected List<String> mTime;
    protected List<String> mTitles;
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
    public void onBindViewHolder(final InfoViewHolder holder, int position) {
        holder.mItemTitle.setText(mTitles.get(position));
        holder.mWhoTime.setText(mWhos.get(position));
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
    }

    public void setWhos(List<String> whos) {
        mWhos = whos;
    }

    public void setTime(List<String> time) {
        mTime = time;
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
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
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener)
    {
        this.mOnItemClickLitener = onItemClickLitener;
    }
    public void insertItems(int timesOfRequestPic,int countOfRequestPic){
        for(int i=0;i<countOfRequestPic;i++){
            int insertPlace=(timesOfRequestPic-1)*countOfRequestPic;
            notifyItemInserted(insertPlace+i);
        }
    }
}
