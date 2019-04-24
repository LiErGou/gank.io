package com.example.administrator.myapplication.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.ui.fragment.InfoFragment;
import com.example.administrator.myapplication.ui.fragment.StatusFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    FragmentManager mFragmentManager;
    @BindView(R.id.search_btn)
    ImageButton mSearchButton;
    @BindView(R.id.username_et)
    EditText mUserNameet;
    InfoFragment mInfoFragment;
    StatusFragment mStatusFragment;

    @OnClick(R.id.search_btn)
    public void search(){
        String userName=mUserNameet.getText().toString().trim();
        if(getFragment() instanceof InfoFragment){
            mInfoFragment.getFirstRepos(userName);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("user",userName);
            mInfoFragment.setArguments(bundle);
            changeFragment(mInfoFragment);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mFragmentManager=getSupportFragmentManager();
        mInfoFragment=new InfoFragment();
        mStatusFragment=new StatusFragment();
//        Fragment GImageFragment = mFragmentManager.findFragmentById(R.id.framelayout);
        changeFragment(mStatusFragment);

    }

    private void changeFragment(Fragment fragment){
        mFragmentManager.beginTransaction()
                .replace(R.id.framelayout, fragment)
                .commit();
    }

    private Fragment getFragment(){
        return mFragmentManager.findFragmentById(R.id.framelayout);
    }
}
