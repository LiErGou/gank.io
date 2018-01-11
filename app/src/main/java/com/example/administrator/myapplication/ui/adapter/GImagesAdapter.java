package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class GImagesAdapter extends RecyclerView.Adapter<GImagesAdapter.MyViewHolder> {

    private OnItemClickLitener mOnItemClickLitener;
    private Context mContext;
    private List<String> urls;

    public GImagesAdapter(List<String> urls) {
        this.urls = urls;
    }
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_image_recycleview,parent,false));

        mContext=parent.getContext();
        return myViewHolder;
    }



    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
        Log.d("licl","onViewRecycled excute");
        Glide.with(mContext).clear(holder.mImageView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        String url = urls.get(position);
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        StaggeredGridLayoutManager.LayoutParams layoutParams =
                                (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                        int height = resource.getHeight()/2;
                        layoutParams.height = height;
                        holder.mImageView.setImageBitmap(resource);
                        holder.itemView.setLayoutParams(layoutParams);
                    }
                });
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

    public void insertItems(int timesOfRequestPic,int countOfRequestPic){
        for(int i=0;i<countOfRequestPic;i++){
            int insertPlace=(timesOfRequestPic-1)*countOfRequestPic;
            notifyItemInserted(insertPlace+i);
        }
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        CardView mCardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.gimage);
            mCardView=(CardView)itemView.findViewById(R.id.image_cardview);
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
}
