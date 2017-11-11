package com.example.administrator.myapplication.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.EndlessOnScrollListener;
import com.example.administrator.myapplication.ui.activity.WebActivity;
import com.example.administrator.myapplication.ui.adapter.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends BaseFragment {

    private static final String TYPE = "type";
    private String mType;
    private OnFragmentInteractionListener mListener;
    private InfoAdapter mInfoAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public InfoFragment() {
        // Required empty public constructor
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
        mUrls=new ArrayList<>();
        mTitles=new ArrayList<>();
        mWhos=new ArrayList<>();
        mTimes =new ArrayList<>();
        countOfRequestInfo =10;
        timesOfRequestInfo =1;

        mDataPresenter.getDataUrls(mType, mUrls, mTitles,mWhos,mTimes,countOfRequestInfo, timesOfRequestInfo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        mRecyclerView=view.findViewById(R.id.info_recycleview);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.info_swiperefreshlayout);
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
    public void setUrls(List<String> urls) {
        this.mUrls = urls;
    }

    @Override
    public void setTitles(List<String> titles) {
        mTitles=titles;
    }

    @Override
    public void setWhos(List<String> whos) {
        mWhos=whos;
    }

    @Override
    public void setTimes(List<String> times) {
        mTimes = times;
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
                mDataPresenter.getDataUrls(mType,mUrls,mTitles,mWhos,mTimes,
                        countOfRequestInfo,timesOfRequestInfo);

            }
        });
        mInfoAdapter.setOnItemClickLitener(new InfoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",mUrls.get(position));
                startActivity(intent);
                Toast.makeText(getContext(),"click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initCallback() {
        mInfoAdapter=new InfoAdapter(mUrls);
        mInfoAdapter.setTime(mTimes);
        mInfoAdapter.setTitles(mTitles);
        mInfoAdapter.setWhos(mWhos);
        mRecyclerView.setAdapter(mInfoAdapter);
        initListeners();
    }

    @Override
    public void loadMoreCallback() {
        mInfoAdapter.setUrls(mUrls);
        mInfoAdapter.setTime(mTimes);
        mInfoAdapter.setTitles(mTitles);
        mInfoAdapter.setWhos(mWhos);
        mInfoAdapter.insertItems(timesOfRequestInfo,countOfRequestInfo);
    }

}
