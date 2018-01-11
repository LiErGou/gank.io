package com.example.administrator.myapplication.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/11.
 */

public class DailyAdapter extends RecyclerView.Adapter {
    static final String[] types={"Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App","福利"};
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_daily_recyclerview,parent,false);
        DailyViewHolder viewHolder=new DailyViewHolder(view) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyViewHolder dailyViewHolder=(DailyViewHolder) holder;
        dailyViewHolder.titleTextview.setText(types[position]);
        dailyViewHolder.titleImageView.setImageResource(getImageTitle(types[position]));
        dailyViewHolder.contentImageView.setImageResource(R.drawable.gank);
        dailyViewHolder.contentTextView.setText("ios经典操作");
    }
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

    @Override
    public int getItemCount() {
        return types.length;
    }

    class DailyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextview;
        ImageView titleImageView;
        ImageView contentImageView;
        TextView contentTextView;
        public DailyViewHolder(View itemView) {
            super(itemView);
            titleTextview=(TextView)itemView.findViewById(R.id.title_item_text);
            titleImageView=(ImageView)itemView.findViewById(R.id.title_item_image);
            contentImageView=(ImageView)itemView.findViewById(R.id.daily_content_image);
            contentTextView=(TextView)itemView.findViewById(R.id.daily_content_textview);
        }
    }
}
