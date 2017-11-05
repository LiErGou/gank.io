package com.example.administrator.myapplication.service.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.service.entity.GImageBean;
import com.example.administrator.myapplication.ui.adapter.GImagesAdapter;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ImageUtils {

    public static void loadPic(Context context,String url, ImageView view){
        Glide.with(context).load(url).into(view);
    }
    public static void loadPic(Context context, GImageBean gImageBean, ImageView view){


        Glide.with(context)
                .asBitmap()
                .load(gImageBean.getResults().get(0).getUrl())


                .into(view);
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        view.setImageBitmap(resource);
//                    }
//
//
//                });

    }
}
