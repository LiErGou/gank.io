package com.example.administrator.myapplication.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myapplication.service.presenter.DataPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public abstract class BaseFragment extends Fragment {
    protected List<String> mUrls=new ArrayList<>();
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected int countOfRequestInfo;
    protected int timesOfRequestInfo;
    protected DataPresenter mDataPresenter;
    protected List<String> mTitles;
    protected List<String> mResultTypes;
    protected List<String> mImageUrls;
    protected List<String> mWhos;
    protected List<String> mTimes;

    public void setUrls(List<String> urls) {
        this.mUrls = urls;
    }
    public abstract void initCallback();
    public abstract void loadMoreCallback();
    public void setTitles(List<String> titles) {
        mTitles=titles;
    }

    public void setWhos(List<String> whos) {
        mWhos=whos;
    }

    public void setResultTypes(List<String> resultTypes) {
        mResultTypes=resultTypes;
    }

    public void setImageUrls(List<String> imageUrls) {
        mImageUrls=imageUrls;
    }

    public void setTimes(List<String> times) {
        mTimes=times;
    }

    abstract  void initListeners();
}
