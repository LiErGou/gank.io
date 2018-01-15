package com.example.administrator.myapplication.ui.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.administrator.myapplication.ui.fragment.InfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private FragmentManager mFragmentManager;
    private ArrayList<String> mTitles;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager=fm;
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }



    public void addFragment(Fragment fragment, String title){
        mFragments.add(fragment);
        mTitles.add(title);
    }

}
