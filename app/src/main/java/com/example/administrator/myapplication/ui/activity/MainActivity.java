package com.example.administrator.myapplication.ui.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.BottomNavigationViewHelper;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.adapter.MyFragmentAdapter;
import com.example.administrator.myapplication.ui.fragment.DailyFragment;
import com.example.administrator.myapplication.ui.fragment.GankRootFragment;
import com.example.administrator.myapplication.ui.fragment.ImageFragment;
import com.example.administrator.myapplication.ui.fragment.InfoFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root_framelayout)
    FrameLayout mRootFrameLayout;
    @BindView(R.id.bottom_bar)
    BottomNavigationView mBottomNavigationView;
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        mFragments=new Fragment[3];
        initView();
        initToolbar();
    }

    private void initView() {

        showFragement(new DailyFragment());
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_daily:
                        if(mFragments[0]==null){
                            mFragments[0]=new DailyFragment();
                        }
                        showFragement(mFragments[0]);
                        break;
                    case R.id.menu_item_list:
                        if(mFragments[1]==null){
                            mFragments[1]=new GankRootFragment();
                        }
                        showFragement(mFragments[1]);
                        break;
                    case R.id.menu_item_welfare:
                        if(mFragments[2]==null){
                            mFragments[2]=new ImageFragment();
                        }
                        showFragement(mFragments[2]);
                        break;
                }
                return true;
            }
        });
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    public void showFragement(Fragment fragment){
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(!fragment.isAdded()){
            ft.add(R.id.root_framelayout,fragment);
        }
        ft = ft.show((Fragment) fragment);
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null) {
            for (Fragment f : fragmentList) {
                if (f!= null && f!= fragment) {
                    ft.hide(f);
                }
            }
        }
        ft.commit();
    }
}
