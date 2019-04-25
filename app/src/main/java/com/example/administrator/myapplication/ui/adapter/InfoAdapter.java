package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.app.Contants;
import com.example.administrator.myapplication.service.entity.UserBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class InfoAdapter extends RecyclerView.Adapter {
    public static final int INFO_TYPE=1;
    public static final int IMAGE_TYPE=0;


    private OnItemClickLitener mOnItemClickLitener;
    private Context mContext;
    private List<UserBean> mUserBeans;

    public InfoAdapter(List<UserBean> userBeans) {
        mUserBeans = userBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder myViewHolder;
        myViewHolder=new InfoViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_info_recycleview,parent,false));

        mContext=parent.getContext();
        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InfoViewHolder){
            UserBean curUserBean=mUserBeans.get(position/ Contants.PER_REQUEST_COUNT);
            final InfoViewHolder infoViewHolder=(InfoViewHolder)holder;
            infoViewHolder.mItemTitle.setText(curUserBean.getName());
            infoViewHolder.mStarCount.setText(curUserBean.getStargazers_count()+"");

            String image=curUserBean.getOwner().getAvatar_url();;


            if(image!=null){
                Glide.with(mContext)
                        .asBitmap()
                        .load(image)
                        .into(infoViewHolder.mInfoImage);
            }
            if (mOnItemClickLitener != null)
            {
                infoViewHolder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = infoViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(infoViewHolder.itemView, pos);
                    }
                });

                infoViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = infoViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(infoViewHolder.itemView, pos);
                        return false;
                    }
                });
            }
        }
    }


    public void setResultBeans(List<UserBean> resultBeans) {
        mUserBeans = resultBeans;
    }




    @Override
    public int getItemCount() {
        return mUserBeans.size()*Contants.PER_REQUEST_COUNT;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView mItemTitle;

        TextView mStarCount;
        ImageView mInfoImage;

        public InfoViewHolder(View itemView) {
            super(itemView);
            mItemTitle=(TextView)itemView.findViewById(R.id.item_titile);

            mStarCount=(TextView)itemView.findViewById(R.id.star_count);
            mInfoImage=(ImageView)itemView.findViewById(R.id.info_image);
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
