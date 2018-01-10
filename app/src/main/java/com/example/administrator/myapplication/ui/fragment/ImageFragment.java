package com.example.administrator.myapplication.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.EndlessOnScrollListener;
import com.example.administrator.myapplication.ui.adapter.GImagesAdapter;

import java.util.ArrayList;
import java.util.List;


public class ImageFragment extends BaseFragment {


    private GImagesAdapter gImagesAdapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private OnFragmentInteractionListener mListener;


    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUrls(List<String> urls) {
        this.mUrls = urls;
    }
    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image, container, false);
        mDataPresenter =new DataPresenter(getContext());
        mDataPresenter.setBaseFragment(this);
        countOfRequestInfo=10;
        timesOfRequestInfo=1;
        mUrls=new ArrayList<>();
        mDataPresenter.getGImageUrls(mUrls,countOfRequestInfo,timesOfRequestInfo,mResultTypes);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.image_recyclerview);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.layout_swipe_refresh);
        mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);



        return view;
    }

    @Override
    public void initCallback(){
        gImagesAdapter=new GImagesAdapter(mUrls);
        mRecyclerView.setAdapter(gImagesAdapter);
        initListeners();
    }

    @Override
    public void loadMoreCallback(){
        gImagesAdapter.setUrls(mUrls);
        gImagesAdapter.insertItems(timesOfRequestInfo,countOfRequestInfo);
    }

    @Override
    void initListeners(){
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);

                gImagesAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mRecyclerView.addOnScrollListener(new EndlessOnScrollListener(mStaggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
//                gImagesAdapter.length+=1;
                timesOfRequestInfo++;
                mDataPresenter.getGImageUrls(mUrls,countOfRequestInfo,timesOfRequestInfo,mResultTypes);

            }
        });
        gImagesAdapter.setOnItemClickLitener(new GImagesAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), position + "Long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
