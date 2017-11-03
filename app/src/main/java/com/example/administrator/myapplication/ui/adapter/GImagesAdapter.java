package com.example.administrator.myapplication.ui.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.GImagePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/1.
 */

public class GImagesAdapter extends RecyclerView.Adapter<GImagesAdapter.MyViewHolder> {
    private List<Integer> heights;
    private GImagePresenter mGImagePresenter;
    public int length=10;
    private int screenWidth;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false));
        mGImagePresenter=new GImagePresenter(parent.getContext());
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
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height = heights.get(position);//把随机的高度赋予item布局
        params.width=screenWidth/2;
        holder.itemView.setLayoutParams(params);//把params设置给item布局

        mGImagePresenter.attachMyView(holder.mImageView);
        mGImagePresenter.getGImage(1,position+1);
//        holder.mImageView.setImageResource(R.drawable.test);
    }


    @Override
    public int getItemCount() {
        return length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.gimage);
        }
    }
}
