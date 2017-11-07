package com.example.administrator.myapplication.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.DataPresenter;
import com.example.administrator.myapplication.ui.adapter.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends BaseFragment {

    private static final String TYPE = "type";
    private String mType;
    private OnFragmentInteractionListener mListener;
    private InfoAdapter mInfoAdapter;


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
        countOfRequestInfo =10;
        timesOfRequestInfo =1;
        mDataPresenter.getDataUrls(mType, mUrls, countOfRequestInfo, timesOfRequestInfo);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_info, container, false);
        mRecyclerView=view.findViewById(R.id.info_recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void initCallback() {
        mInfoAdapter=new InfoAdapter(mUrls);
        mRecyclerView.setAdapter(mInfoAdapter);
    }

    @Override
    public void loadMoreCallback() {

    }

}
