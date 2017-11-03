package com.example.administrator.myapplication.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/2.
 */

public abstract class EndlessOnScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage = 0;
    //声明一个LinearLayoutManager
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    //当前页，从0开始    private int currentPage = 0;
    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;
    private int[] firstVisibleItems;
    //是否正在上拉数据
    private boolean loading = true;

    public EndlessOnScrollListener(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        mStaggeredGridLayoutManager = staggeredGridLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mStaggeredGridLayoutManager.getItemCount();
        firstVisibleItems = mStaggeredGridLayoutManager.findFirstVisibleItemPositions (firstVisibleItems);
        if(loading){
            Log.d("wnwn","firstVisibleItem: " +firstVisibleItems[0]);
            Log.d("wnwn","totalPageCount:" +totalItemCount);
            Log.d("wnwn", "visibleItemCount:" + visibleItemCount);

            if(totalItemCount > previousTotal){
                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        //这里需要好好理解
        if (!loading && totalItemCount-visibleItemCount <= firstVisibleItems[0]){
            currentPage ++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore(int currentPage);

}
