package com.example.administrator.myapplication.service.manager;

import android.content.Context;

import com.example.administrator.myapplication.service.entity.ResultBean;
import com.example.administrator.myapplication.service.http.RetrofitHelper;
import com.example.administrator.myapplication.service.http.ImageService;

import rx.Observable;


/**
 * Created by Administrator on 2017/10/31.
 */

public class DataManager {
    private ImageService mRetrofitService;
    public DataManager(Context context){
        mRetrofitService= RetrofitHelper.getInstance(context).getGImageServer();
    }
    public Observable<ResultBean> getGImage(int count, int page){
        return mRetrofitService.getGirls(count,page);
    }
    public Observable<ResultBean> getData(String type,int count, int page){
        return mRetrofitService.getDatas(type,count,page);
    }
}
