package com.example.administrator.myapplication.ui.activity;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.adapter.MyFragmentAdapter;
import com.example.administrator.myapplication.ui.fragment.ImageFragment;
import com.example.administrator.myapplication.ui.fragment.InfoFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CoordinatorLayout coordinator;
    private MyFragmentAdapter mMyFragmentAdapter;
    private static List<String> types =
            Arrays.asList("all", "Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
    }

    private void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        coordinator=(CoordinatorLayout)findViewById(R.id.coordinator);
        mMyFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager());
//        mMyFragmentAdapter.addFragment(new ImageFragment(),"福利");//
        addFragments();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
        viewPager.setAdapter(mMyFragmentAdapter);
    }

    private void addFragments(){
        mMyFragmentAdapter.addFragment(new ImageFragment(),"福利");
        for(int i=0;i<types.size();i++){
            mMyFragmentAdapter.addFragment(InfoFragment.newInstance(types.get(i)),types.get(i));
        }
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }
}
