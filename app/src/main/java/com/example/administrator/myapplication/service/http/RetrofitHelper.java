package com.example.administrator.myapplication.service.http;

import android.content.Context;

import com.example.administrator.myapplication.app.Contants;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/30.
 */

public class RetrofitHelper {

    private static RetrofitHelper instance=null;
    private Context mContext;
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient= new OkHttpClient();;
    private GsonConverterFactory mFactory=GsonConverterFactory.create(new GsonBuilder().create());;

    public static RetrofitHelper getInstance(Context context){
        if(instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context context){
        mContext=context;
        init();
    }
    private void init(){
        resetApp();
    }
    private void resetApp(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl(Contants.BASE_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(mFactory)
                .build();
    }
    public Retrofit getRetrofit() {
        if(mRetrofit==null){
            synchronized(RetrofitHelper.class){
                mRetrofit=new Retrofit.Builder()
                        .baseUrl(Contants.BASE_URL)
                        .client(mOkHttpClient)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(mFactory)
                        .build();
            }
        }
        return mRetrofit;
    }

    public ImageService getGImageServer(){
        return mRetrofit.create(ImageService.class);
    }
}
