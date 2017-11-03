package com.example.administrator.myapplication.service.presenter;

import android.view.View;

import com.example.administrator.myapplication.service.view.MyView;

/**
 * Created by Administrator on 2017/10/31.
 */

public interface Presenter {
    void onCreate();
    void onStart();
    void onPause();
    void onStop();
    void attachMyView(View view);
}
