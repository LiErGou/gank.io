package com.example.administrator.myapplication.service.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.service.entity.ResultBean;
import com.example.administrator.myapplication.service.manager.DataManager;
import com.example.administrator.myapplication.service.utils.ImageUtils;
import com.example.administrator.myapplication.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/11/1.
 */

public class DataPresenter implements Presenter {




    private BaseFragment mBaseFragment;
    private Context mContext;
    private ImageView mImageView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;
    private ResultBean mResultBean;

    public DataPresenter(Context context) {
        mContext = context;
        mDataManager=new DataManager(context);
        mCompositeSubscription=new CompositeSubscription();
    }
    public void setBaseFragment(BaseFragment GImagesAdapter) {
        mBaseFragment = GImagesAdapter;
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

    public void getDataUrls(String type, final List<String> urls, final List<String> titles,
                            final List<String> whos, final List<String> times, final int count, final int page, final List<String> resultTypes, final List<String> imageUrls){

        mCompositeSubscription.add(mDataManager.getData(type,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                               @Override
                               public void onCompleted() {
                                   if(mResultBean !=null){
                                       for(int i=0;i<count;i++){
                                           urls.add(mResultBean.getResults().get(i).getUrl());
                                           titles.add(mResultBean.getResults().get(i).getDesc());
                                           whos.add(mResultBean.getResults().get(i).getWho());
                                           times.add(mResultBean.getResults().get(i).getPublishedAt());
                                           resultTypes.add(mResultBean.getResults().get(i).getType());
                                           if(mResultBean.getResults().get(i).getImages().size()>0){
                                               imageUrls.add(mResultBean.getResults().get(i).getImages().get(0));
                                           }else{
                                               imageUrls.add(null);
                                           }
                                       }
                                       mBaseFragment.setUrls(urls);
                                       mBaseFragment.setTitles(titles);
                                       mBaseFragment .setWhos(whos);
                                       mBaseFragment.setTimes(times);
                                       mBaseFragment.setResultTypes(resultTypes);
                                       mBaseFragment.setImageUrls(imageUrls);
                                       if(page==1){
                                           mBaseFragment.initCallback();
                                       }else {
                                           mBaseFragment.loadMoreCallback();
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Toast.makeText(mContext,"Download Failed",Toast.LENGTH_LONG);
                               }

                               @Override
                               public void onNext(ResultBean resultBean) {
                                   mResultBean = resultBean;
                               }
                           }
                )
        );

    }

    public void getGImageUrls(final List<String> urls, final int count, final int page,final List<String> resultTypes){
        List<String> titles=new ArrayList<>();
        List<String> whos=new ArrayList<>();
        List<String> times=new ArrayList<>();
        List<String> imageUrls=new ArrayList<>();
        getDataUrls("福利",urls,titles,whos,times,count,page,resultTypes,imageUrls);
    }


    public void getGImage(int count,int page){
        mCompositeSubscription.add(mDataManager.getGImage(count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBean>() {
                    @Override
                    public void onCompleted() {
                        if(mResultBean !=null){
                            ImageUtils.loadPic(mContext, mResultBean,mImageView);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext,"Download Failed",Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onNext(ResultBean resultBean) {
                        mResultBean = resultBean;
                    }
                }
                )
        );
    }

}
