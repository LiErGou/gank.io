package com.example.administrator.myapplication.service.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.service.entity.UserBean;
import com.example.administrator.myapplication.service.manager.DataManager;
import com.example.administrator.myapplication.service.utils.ImageUtils;
import com.example.administrator.myapplication.ui.fragment.BaseFragment;
import com.example.administrator.myapplication.ui.fragment.InfoFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;



public class DataPresenter implements Presenter {

    private BaseFragment mBaseFragment;
    private Context mContext;
    private ImageView mImageView;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;
    private List<UserBean> mResultBean;
    private int curRandomNum;
    public DataPresenter(Context context) {
        mContext = context;
        mDataManager = new DataManager(context);
        mCompositeSubscription = new CompositeSubscription();
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
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void attachMyView(View view) {
        mImageView = (ImageView) view;
    }

    public void getDataUrls(String name, final List<UserBean> resultBeans, final int count, final int page) {

        mCompositeSubscription.add(mDataManager.getUsers(name, count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserBean>>() {
                               @Override
                               public void onCompleted() {
                                   if (mResultBean != null) {
                                       resultBeans.addAll(mResultBean);
                                       mBaseFragment.setResultBeans(resultBeans);
                                       if (page == 1) {
                                           mBaseFragment.initCallback();
                                       } else {
                                           mBaseFragment.loadMoreCallback();
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   ((InfoFragment)mBaseFragment).swichToNoResult("net work error,Please check network.");
                                   Toast.makeText(mContext, "Download Failed" +e.getMessage(), Toast.LENGTH_LONG).show();
                               }

                               @Override
                               public void onNext(List<UserBean> resultBean) {
                                   mResultBean = resultBean;
                               }
                           }
                )
        );
    }

    private int getRandomNum(){
        int tmp=(int) (Math.random() * 10);
        if(tmp!=curRandomNum){
            curRandomNum=tmp;
            return tmp;
        }else return tmp+1;
    }

}
