package com.example.administrator.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.entity.ResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/11.
 */

public class DailyAdapter extends RecyclerView.Adapter {
    static final String[] types={"Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App","福利"};
    private List<ResultBean> mResultBeans;
    private OnItemClickLitener mOnItemClickLitener;
    private OnItemChangeClickListener mOnItemChangeClickListener;
    private Context mContext;
    private int curImageNum=0;
    private Integer[] mResAndroid={R.mipmap.gank_io_day_item_android1,
            R.mipmap.gank_io_day_item_android2 ,R.mipmap.gank_io_day_item_android3 };
    private Integer[] mResIOS = {
            R.mipmap.gank_io_day_item_ios1,
            R.mipmap.gank_io_day_item_ios2,
            R.mipmap.gank_io_day_item_ios3};
    private Integer[] mResAPP={R.mipmap.gank_io_day_item_android4,
            R.mipmap.gank_io_day_item_android5 ,R.mipmap.gank_io_day_item_android6
    };
    private Integer[] mResWeb={
            R.mipmap.gank_io_day_item_web1, R.mipmap.gank_io_day_item_web2, R.mipmap.gank_io_day_item_web3
    };
    private Integer[] mResVideo={
            R.mipmap.gank_io_day_item_video1, R.mipmap.gank_io_day_item_video2, R.mipmap.gank_io_day_item_video3
    };
    private Integer[] mResDev={
            R.mipmap.gank_io_day_item_dev1,R.mipmap.gank_io_day_item_dev2,R.mipmap.gank_io_day_item_dev3
    };
    private Integer[] mResRecommond={
            R.mipmap.gank_io_day_item_android3,R.mipmap.gank_io_day_item_ios2,R.mipmap.gank_io_day_item_video3
            ,R.mipmap.gank_io_day_item_dev3
    };
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_daily_recyclerview,parent,false);
        DailyViewHolder viewHolder=new DailyViewHolder(view) ;
        return viewHolder;
    }
    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
        mOnItemClickLitener=onItemClickLitener;
    }

    public DailyAdapter(List<ResultBean> resultBeans) {
        mResultBeans = resultBeans;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        String imageurl=null;
        String type=mResultBeans.get(position).getResults().get(0).getType();
        String title=mResultBeans.get(position).getResults().get(0).getDesc();
        String url=mResultBeans.get(position).getResults().get(0).getUrl();
        final DailyViewHolder dailyViewHolder=(DailyViewHolder) holder;
        dailyViewHolder.titleTextview.setText(type);
        List images=mResultBeans.get(position).getResults().get(0).getImages();

        if(images!=null){
            imageurl=mResultBeans.get(position).getResults().get(0).getImages().get(0);
        }

        if(imageurl!=null){

        }else {
            if(type.equals("福利")){
                Glide.with(mContext)
                        .asBitmap()
                        .load(url)
                        .into(dailyViewHolder.contentImageView);
            }else {
                dailyViewHolder.contentImageView.setImageResource(getContentImage(type));
            }
        }

        dailyViewHolder.titleImageView.setImageResource(getImageTitle(type));
        dailyViewHolder.contentTextView.setText(title);
        if (mOnItemClickLitener != null)
        {
            dailyViewHolder.contentImageView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = dailyViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(dailyViewHolder.itemView, pos);
                }
            });

            dailyViewHolder.contentImageView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = dailyViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(dailyViewHolder.itemView, pos);
                    return false;
                }
            });
        }

        if (mOnItemChangeClickListener != null)
        {
            dailyViewHolder.changeTextView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = dailyViewHolder.getLayoutPosition();
                    mOnItemChangeClickListener.onItemClick(dailyViewHolder.itemView, pos);
                }
            });

        }
    }
    private int getContentImage(String resultType){
        Map<String,Integer[]> typeImagesMap=new HashMap<>();
        String[] types={"Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App"};
        Integer[][] imageids={mResAndroid,mResIOS,mResVideo,mResDev,mResWeb,mResRecommond,mResAPP};
        for(int i=0;i<types.length;i++){
            typeImagesMap.put(types[i],imageids[i]);
        }
        if(typeImagesMap.containsKey(resultType)){
            Integer[] curImages=typeImagesMap.get(resultType);
            curImageNum++;
            curImageNum=curImageNum%curImages.length;
            return curImages[curImageNum];
        }else  {
            return R.drawable.gank;
        }
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
        if(typeImageMap.containsKey(resultType)){
            return typeImageMap.get(resultType);
        }
        return  R.drawable.ic_vector_title_android;
    }

    public void setOnItemChangeClickListener(OnItemChangeClickListener onItemChangeClickListener) {
        mOnItemChangeClickListener = onItemChangeClickListener;
    }


    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public interface OnItemChangeClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }
    public void setResultBeans(List<ResultBean> resultBeans) {
        mResultBeans = resultBeans;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    class DailyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextview;
        ImageView titleImageView;
        ImageView contentImageView;
        TextView contentTextView;
        TextView changeTextView;
        public DailyViewHolder(View itemView) {
            super(itemView);
            titleTextview=(TextView)itemView.findViewById(R.id.title_item_text);
            titleImageView=(ImageView)itemView.findViewById(R.id.title_item_image);
            contentImageView=(ImageView)itemView.findViewById(R.id.daily_content_image);
            contentTextView=(TextView)itemView.findViewById(R.id.daily_content_textview);
            changeTextView=(TextView)itemView.findViewById(R.id.change_textview);
        }
    }
    public void insertItems(int timesOfRequestPic,int countOfRequestPic){
        for(int i=0;i<countOfRequestPic;i++){
            int insertPlace=(timesOfRequestPic-1)*countOfRequestPic;
            notifyItemInserted(insertPlace+i);
        }
    }
}
