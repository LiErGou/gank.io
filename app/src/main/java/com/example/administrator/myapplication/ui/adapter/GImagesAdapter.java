package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.GImagePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class GImagesAdapter extends RecyclerView.Adapter<GImagesAdapter.MyViewHolder> {
    private OnItemClickLitener mOnItemClickLitener;
    private List<Integer> heights;
    private GImagePresenter mGImagePresenter;
    public int length=10;
    public int picHeight;
    private int screenWidth;
    private Context mContext;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false));
        mGImagePresenter=new GImagePresenter(parent.getContext());
        mContext=parent.getContext();
        initSize(parent);
        return myViewHolder;
    }

    private void initSize(ViewGroup parent){
        getRandomHeight(length);
        Resources resources = parent.getContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenWidth = dm.widthPixels;
    }
    private void getRandomHeight(int length){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            heights.add((int)(400+Math.random()*400));
        }
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.clear(holder.mImageView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        mGImagePresenter.attachMyView(holder.mImageView);
        mGImagePresenter.getGImage(1,position+1);
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


    @Override
    public int getItemCount() {
        return length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.gimage);
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
