package com.example.administrator.myapplication.service.presenter;

import android.content.Context;
import android.view.View;

import com.example.administrator.myapplication.service.entity.Book;
import com.example.administrator.myapplication.service.manager.DataManager;
import com.example.administrator.myapplication.service.view.BookView;
import com.example.administrator.myapplication.service.view.MyView;

import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/10/31.
 */

public class BookPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;

    public BookPresenter(){

    }
    public BookPresenter(Context context){
        mContext=context;

    }
    @Override
    public void onCreate() {
        manager=new DataManager(mContext);
        mCompositeSubscription=new CompositeSubscription();
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

    }


    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(manager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null){
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook=book;
                    }
                })
        );
    }
}
