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

}
