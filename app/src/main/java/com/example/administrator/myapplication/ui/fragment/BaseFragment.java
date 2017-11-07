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

    public void setUrls(List<String> urls) {
        this.mUrls = urls;
    }
    public abstract void initCallback();
    public abstract void loadMoreCallback();
}
