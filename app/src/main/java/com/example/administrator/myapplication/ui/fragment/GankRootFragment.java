package com.example.administrator.myapplication.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.adapter.MyFragmentAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/13.
 */

public class GankRootFragment extends Fragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    private MyFragmentAdapter mMyFragmentAdapter;
    private InfoFragment[] mInfoFragments=new InfoFragment[types.size()];
    private static List<String> types =
            Arrays.asList("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmeny_gank,container,false);
        ButterKnife.bind(this,view);
        Log.d("licl","GankRootFragment onCreateView");
        initView();
        return view;
    }
    private void initView(){
        mMyFragmentAdapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager());
        addFragments();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(mMyFragmentAdapter);
    }
    private void addFragments() {
        for (int i = 0; i < types.size(); i++) {
            if(mInfoFragments[i]==null){
                mInfoFragments[i]=InfoFragment.newInstance(types.get(i));
            }
            mMyFragmentAdapter.addFragment(mInfoFragments[i], types.get(i));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }




}
