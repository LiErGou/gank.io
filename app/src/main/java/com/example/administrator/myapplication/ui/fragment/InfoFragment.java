package com.example.administrator.myapplication.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.EndlessOnScrollListener;
import com.example.administrator.myapplication.ui.activity.HomeActivity;
import com.example.administrator.myapplication.ui.adapter.InfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends BaseFragment {
    @BindView(R.id.info_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.info_swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
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

    public void getFirstRepos(String user){
        mResultBeans.clear();
        countOfRequestInfo =10;
        timesOfRequestInfo =1;
        mType=user;
        mDataPresenter.getDataUrls(mType,mResultBeans,countOfRequestInfo, timesOfRequestInfo);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mType = getArguments().getString(TYPE);
//        }
//        mType="airbnb";
        mDataPresenter=new DataPresenter(getContext());
        mDataPresenter.setBaseFragment(this);
//        if()
        String user=this.getArguments().getString("user");
        getFirstRepos(user);

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
                showProgress();
                timesOfRequestInfo++;
                mDataPresenter.getDataUrls(mType,mResultBeans, countOfRequestInfo,timesOfRequestInfo);

            }
        });
        mInfoAdapter.setOnItemClickLitener(new InfoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void initCallback() {
        if(mResultBeans.size()!=0){
            mProgressBar.setVisibility(View.GONE);
            mInfoAdapter=new InfoAdapter(mResultBeans);
            mRecyclerView.setAdapter(mInfoAdapter);
            initListeners();
        }else{
            swichToNoResult("No user or no repository.");
        }

    }

    public void swichToNoResult(String msg){
        ((HomeActivity)getActivity()).switchToNoResult(msg);
    }

    @Override
    public void loadMoreCallback() {
        hideProgress();
        mProgressBar.setVisibility(View.GONE);
        mInfoAdapter.setResultBeans(mResultBeans);
        mInfoAdapter.insertItems(timesOfRequestInfo,countOfRequestInfo);
    }

    private void showProgress(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        mProgressBar.setVisibility(View.GONE);
    }


}
