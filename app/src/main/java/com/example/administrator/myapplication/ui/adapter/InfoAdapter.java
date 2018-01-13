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
import com.example.administrator.myapplication.service.entity.ResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/7.
 */

public class InfoAdapter extends RecyclerView.Adapter {
    public static final int INFO_TYPE=1;
    public static final int IMAGE_TYPE=0;


    private OnItemClickLitener mOnItemClickLitener;
    private Context mContext;
    private List<ResultBean> mResultBeans;

    public InfoAdapter(List<ResultBean> resultBeans) {
        mResultBeans = resultBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder myViewHolder;

        if(viewType==IMAGE_TYPE){
             myViewHolder=new ImageViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_image_recycleview,parent,false));
        }else{
            myViewHolder=new InfoViewHolder(LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.item_info_recycleview,parent,false));
        }
        mContext=parent.getContext();
        return myViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InfoViewHolder){
            final InfoViewHolder infoViewHolder=(InfoViewHolder)holder;
            infoViewHolder.mItemTitle.setText(mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getDesc());
            infoViewHolder.mWhoType.setText(getWhoAndType(position));
            infoViewHolder.mTime.setText(mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getPublishedAt().substring(0,10));
            String url = mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position%Contants.PER_REQUEST_COUNT).getUrl();
            infoViewHolder.mTitleImage.setImageResource(getImageTitle(mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getType()));
            if(url!=null){
                Glide.with(mContext)
                        .asBitmap()
                        .load(url)
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
        if(holder instanceof ImageViewHolder) {
            final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;

            String url = mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getUrl();
            Glide.with(mContext)
                    .asBitmap()
                    .load(url)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            RecyclerView.LayoutParams layoutParams =
                                    (RecyclerView.LayoutParams ) imageViewHolder.itemView.getLayoutParams();
                            int height = resource.getHeight()/2;
                            layoutParams.height = height;
                            imageViewHolder.mImageView.setImageBitmap(resource);
                            imageViewHolder.itemView.setLayoutParams(layoutParams);
                        }
                    });
            if (mOnItemClickLitener != null)
            {
                imageViewHolder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = imageViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(imageViewHolder.itemView, pos);
                    }
                });

                imageViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = imageViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(imageViewHolder.itemView, pos);
                        return false;
                    }
                });
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getImageTitle(String resultType){
        Map<String,Integer> typeImageMap=new HashMap<>();
        String[] types={"Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App","福利"};
        int[] imageids={R.drawable.ic_vector_title_android,R.drawable.ic_vector_title_ios,R.drawable.ic_vector_title_video,
        R.drawable.ic_vector_title_refesh,R.drawable.ic_vector_title_front,R.drawable.ic_vector_title_refesh,
                R.drawable.ic_vector_title_android,R.drawable.ic_vector_title_welfare};
        for(int i=0;i<types.length;i++){
            typeImageMap.put(types[i],imageids[i]);
        }
        return typeImageMap.get(resultType);
    }

    public void setResultBeans(List<ResultBean> resultBeans) {
        mResultBeans = resultBeans;
    }

    private String getWhoAndType(int position){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getWho())
                .append("·").append(mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getType());
        return stringBuilder.toString();
    }
    @Override
    public int getItemViewType(int position) {
        if(!mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getType().equals("福利")){
            return INFO_TYPE;
        }else{
            return IMAGE_TYPE;
        }
    }




    @Override
    public int getItemCount() {
        return mResultBeans.size()*Contants.PER_REQUEST_COUNT;
    }

    class InfoViewHolder extends RecyclerView.ViewHolder{
        TextView mItemTitle;
        TextView mWhoType;
        TextView mTime;
        ImageView mInfoImage;
        ImageView mTitleImage;
        public InfoViewHolder(View itemView) {
            super(itemView);
            mItemTitle=(TextView)itemView.findViewById(R.id.item_titile);
            mWhoType =(TextView)itemView.findViewById(R.id.item_who_type);
            mTime=(TextView)itemView.findViewById(R.id.item_time);
            mInfoImage=(ImageView)itemView.findViewById(R.id.info_image);
            mTitleImage=(ImageView)itemView.findViewById(R.id.iv_type_item_title);
        }
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        CardView mCardView;
        public ImageViewHolder(View itemView) {
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
    public void insertItems(int timesOfRequestPic,int countOfRequestPic){
        for(int i=0;i<countOfRequestPic;i++){
            int insertPlace=(timesOfRequestPic-1)*countOfRequestPic;
            notifyItemInserted(insertPlace+i);
        }
    }
}
