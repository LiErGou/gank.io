package com.example.administrator.myapplication.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.app.Contants;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.EndlessOnScrollListener;
import com.example.administrator.myapplication.ui.activity.WebActivity;
import com.example.administrator.myapplication.ui.adapter.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends BaseFragment {
    @BindView(R.id.info_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.info_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String TYPE = "type";
    private String mType;
    private OnFragmentInteractionListener mListener;
    private InfoAdapter mInfoAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public InfoFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String type) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
        }
        mDataPresenter=new DataPresenter(getContext());
        mDataPresenter.setBaseFragment(this);
        countOfRequestInfo =10;
        timesOfRequestInfo =1;

        mDataPresenter.getDataUrls(mType,mResultBeans,countOfRequestInfo, timesOfRequestInfo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this,view);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    @Override
    void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                mInfoAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
//                gImagesAdapter.length+=1;
                timesOfRequestInfo++;
                mDataPresenter.getDataUrls(mType,mResultBeans, countOfRequestInfo,timesOfRequestInfo);

            }
        });
        mInfoAdapter.setOnItemClickLitener(new InfoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",mResultBeans.get(position/ Contants.PER_REQUEST_COUNT).getResults().get(position% Contants.PER_REQUEST_COUNT).getUrl());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initCallback() {
        mInfoAdapter=new InfoAdapter(mResultBeans);
        mRecyclerView.setAdapter(mInfoAdapter);
        initListeners();
    }

    @Override
    public void loadMoreCallback() {
        mInfoAdapter.setResultBeans(mResultBeans);
        mInfoAdapter.insertItems(timesOfRequestInfo,countOfRequestInfo);
    }

}
