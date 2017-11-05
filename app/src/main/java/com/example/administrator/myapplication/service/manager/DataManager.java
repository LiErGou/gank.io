package com.example.administrator.myapplication.service.manager;

import android.content.Context;

import com.example.administrator.myapplication.service.entity.GImageBean;
import com.example.administrator.myapplication.service.http.RetrofitHelper;
import com.example.administrator.myapplication.service.http.ImageService;
import com.example.administrator.myapplication.service.entity.Book;

import rx.Observable;


/**
 * Created by Administrator on 2017/10/31.
 */

public class DataManager {
    private ImageService mRetrofitService;
    public DataManager(Context context){
        mRetrofitService= RetrofitHelper.getInstance(context).getGImageServer();
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return null;
    }
    public Observable<GImageBean> getGImage(int count,int page){
        return mRetrofitService.getGirls(count,page);
    }
}