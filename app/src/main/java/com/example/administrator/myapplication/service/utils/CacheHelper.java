package com.example.administrator.myapplication.service.utils;

import android.content.Context;

import com.example.administrator.myapplication.service.cache.ACache;

import java.io.Serializable;

public class CacheHelper<T extends Serializable> {
    private ACache mACache;
    public CacheHelper(Context context){
        mACache=ACache.get(context);
    }
    public synchronized void save(T t,String key){
        mACache.put(key,t);
    }

    public boolean contains(String key){
        return mACache.getAsObject(key)==null;
    }

    public T read(String key){
        T t=(T)mACache.getAsObject(key);
        return t;
    }

    public synchronized void clear(String key){
        mACache.remove(key);
    }
}
