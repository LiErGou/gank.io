package com.example.administrator.myapplication.service.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.service.entity.UserBean;
import com.example.administrator.myapplication.service.manager.DataManager;
import com.example.administrator.myapplication.service.utils.ImageUtils;
import com.example.administrator.myapplication.ui.fragment.BaseFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/11/1.
 */

public class DataPresenter implements Presenter {


    static final String[] types = {"Android", "iOS", "休息视频", "拓展资源", "前端", "瞎推荐", "App", "福利"};
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

//    public void getChangeDailyData(String type, final int position) {
//        mCompositeSubscription.add(mDataManager.getData(type, 1,getRandomNum() )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<UserBean>() {
//                               @Override
//                               public void onCompleted() {
//                                   if (mResultBean != null) {
//                                       mBaseFragment.setCurrentResultBeans(mResultBean);
//                                       mBaseFragment.changeCallback(position);
//                                   }
//                               }
//
//                               @Override
//                               public void onError(Throwable e) {
//                                   Toast.makeText(mContext, "Download Failed", Toast.LENGTH_LONG);
//                               }
//
//                               @Override
//                               public void onNext(UserBean resultBean) {
//                                   mResultBean = resultBean;
//                               }
//                           }
//                )
//        );
//    }
//
//    public void getInitDailyUrls(List<UserBean> resultBeans) {
//        for (int i = 0; i < types.length; i++) {
//            getDataUrls(types[i], resultBeans, 1, 1);
//        }
//
//    }
//
//    public void getGImageUrls(List<UserBean> resultBeans, final int count, final int page) {
//
//        getDataUrls("福利", resultBeans, count, page);
//    }
//
//
//    public void getGImage(int count, int page) {
//        mCompositeSubscription.add(mDataManager.getUsers(count, page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResultBean>() {
//                               @Override
//                               public void onCompleted() {
//                                   if (mResultBean != null) {
//                                       ImageUtils.loadPic(mContext, mResultBean, mImageView);
//                                   }
//                               }
//
//                               @Override
//                               public void onError(Throwable e) {
//                                   Toast.makeText(mContext, "Download Failed", Toast.LENGTH_LONG);
//                               }
//
//                               @Override
//                               public void onNext(ResultBean resultBean) {
//                                   mResultBean = resultBean;
//                               }
//                           }
//                )
//        );
//    }

}
