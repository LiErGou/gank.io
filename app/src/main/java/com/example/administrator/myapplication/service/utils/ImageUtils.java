package com.example.administrator.myapplication.service.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ImageUtils {

    public static void loadPic(Context context,String url, ImageView view){
        Glide.with(context).load(url).into(view);
    }
//    public static void loadPic(Context context, ResultBean resultBean, ImageView view){
//
//
//        Glide.with(context)
//                .asBitmap()
//                .load(resultBean.getResults().get(0).getUrl())
//
//
//                .into(view);
////                .into(new SimpleTarget<Bitmap>() {
////                    @Override
////                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
////                        view.setImageBitmap(resource);
////                    }
////
////
////                });
//
//    }
}
