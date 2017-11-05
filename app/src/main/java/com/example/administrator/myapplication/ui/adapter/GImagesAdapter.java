package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.app.GlideApp;
import com.example.administrator.myapplication.ui.fragment.ImageFragment;

import java.util.Collections;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Administrator on 2017/11/1.
 */

public class GImagesAdapter extends RecyclerView.Adapter<GImagesAdapter.MyViewHolder> implements
        ListPreloader.PreloadModelProvider{
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
                .from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false));

        mContext=parent.getContext();

        return myViewHolder;
    }



    @Override
    public void onViewRecycled(MyViewHolder holder) {
        super.onViewRecycled(holder);
//        Glide.with(mContext).clear(holder.mImageView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        String url = urls.get(position);
        Glide.with(mContext)
                .load(url)
                .transition(withCrossFade())
                .into(holder.mImageView);

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

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = urls.get(position);
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder getPreloadRequestBuilder(Object item) {
        return GlideApp.with(mContext)
                .load((String)item);
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
