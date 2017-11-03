package com.example.administrator.myapplication.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.service.presenter.GImagePresenter;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    private GImagePresenter mGImagePresenter;
    private ImageView mImageView;
    private ScaleAnimation scaleAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mImageView=(ImageView)findViewById(R.id.splashimageView);
        mGImagePresenter=new GImagePresenter(this);
        mGImagePresenter.onCreate();
        mGImagePresenter.attachMyView(mImageView);
        int picNum = new Random().nextInt(6) + 1;
        mGImagePresenter.getGImage(1,picNum);
        initAnimation();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mGImagePresenter.onStop();
    }

    private void initAnimation(){
        scaleAnimation=new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        mImageView.setAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
