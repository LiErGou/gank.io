package com.example.administrator.myapplication.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.fragment.ImageFragment;

public class HomeActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFragmentManager=getSupportFragmentManager();
        Fragment GImageFragment = mFragmentManager.findFragmentById(R.id.framelayout);
        if (GImageFragment == null) {
            GImageFragment = new ImageFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.framelayout, GImageFragment)
                    .commit();
        }
    }
}
