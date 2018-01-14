package com.example.administrator.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.activity.WebActivity;
import com.example.administrator.myapplication.ui.adapter.DailyAdapter;

/**
 * Created by Administrator on 2018/1/11.
 */

public class DailyFragment extends BaseFragment {
    DailyAdapter mDailyAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_daily, container, false);
        mDataPresenter =new DataPresenter(getContext());
        mDataPresenter.setBaseFragment(this);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.daily_recyclerview);
        mDataPresenter.getInitDailyUrls(mResultBeans);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        timesOfRequestInfo=1;
        return view;
    }

    @Override
    public void initCallback() {
        if(mDailyAdapter==null){
            mDailyAdapter=new DailyAdapter(mResultBeans);
        }
        mDailyAdapter.setResultBeans(mResultBeans);
        mDailyAdapter.setContext(getContext());
        mRecyclerView.setAdapter(mDailyAdapter);
        initListeners();
    }
    @Override
    public void initDailCallback(){

    }

    @Override
    public void loadMoreCallback() {
        mDailyAdapter.setResultBeans(mResultBeans);
        mDailyAdapter.insertItems(timesOfRequestInfo++,1);
    }

    @Override
    public void changeCallback(int position) {
        mResultBeans.set(position,currentResultBean);
        mDailyAdapter.setResultBeans(mResultBeans);
        mDailyAdapter.notifyItemChanged(position);
    }

    @Override
    void initListeners() {
        mDailyAdapter.setOnItemClickLitener(new DailyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",mResultBeans.get(position).getResults().get(0).getUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mDailyAdapter.setOnItemChangeClickListener(new DailyAdapter.OnItemChangeClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mDataPresenter.getChangeDailyData(mResultBeans.get(position).getResults().get(0).getType(),position);
            }
        });
    }
}
