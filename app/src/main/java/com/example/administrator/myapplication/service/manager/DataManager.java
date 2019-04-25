package com.example.administrator.myapplication.service.manager;

import android.content.Context;

import com.example.administrator.myapplication.service.entity.UserBean;
import com.example.administrator.myapplication.service.http.RetrofitHelper;
import com.example.administrator.myapplication.service.http.UserService;

import java.util.List;

import rx.Observable;




public class DataManager {
    private UserService mRetrofitService;
    public DataManager(Context context){
        mRetrofitService= RetrofitHelper.getInstance(context).getGImageServer();
    }
    public Observable<List<UserBean>> getUsers(String name, int count, int page){
        return mRetrofitService.getUsers(name,page,count);
    }
}
