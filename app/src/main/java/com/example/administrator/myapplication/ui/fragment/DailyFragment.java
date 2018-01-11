package com.example.administrator.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.adapter.DailyAdapter;

/**
 * Created by Administrator on 2018/1/11.
 */

public class DailyFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_daily, container, false);
        mDataPresenter =new DataPresenter(getContext());
        mDataPresenter.setBaseFragment(this);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.daily_recyclerview);
        mRecyclerView.setAdapter(new DailyAdapter());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void initCallback() {

    }

    @Override
    public void loadMoreCallback() {

    }

    @Override
    void initListeners() {

    }
}
