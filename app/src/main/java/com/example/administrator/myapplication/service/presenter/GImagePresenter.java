package com.example.administrator.myapplication.service.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.service.entity.GImageBean;
import com.example.administrator.myapplication.service.manager.DataManager;
import com.example.administrator.myapplication.service.utils.ImageUtils;
import com.example.administrator.myapplication.service.view.MyView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/11/1.
 */

public class GImagePresenter implements Presenter {

    private Context mContext;
    private ImageView mImageView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;
    private GImageBean mGImageBean;

    public GImagePresenter(Context context) {
        mContext = context;
        mDataManager=new DataManager(context);
        mCompositeSubscription=new CompositeSubscription();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        if(mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachMyView(View view) {
        mImageView=(ImageView) view;
    }

    public void getGImage(int count,int page){
        mCompositeSubscription.add(mDataManager.getGImage(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GImageBean>() {
                    @Override
                    public void onCompleted() {
                        if(mGImageBean!=null){
                            ImageUtils.loadPic(mContext,mGImageBean,mImageView);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext,"Download Failed",Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onNext(GImageBean gImageBean) {
                        mGImageBean=gImageBean;
                    }
                }));
    }

}
