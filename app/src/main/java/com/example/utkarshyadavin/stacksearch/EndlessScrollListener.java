package com.example.utkarshyadavin.stacksearch;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by utkarshyadavin on 4/5/18.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    // Total number of items which are yet to be displayed from Scroll position
    private int visibleThreshold = 5 ;
    //Total number of items before the previous load of data
    private int previousItemCount = 0 ;
    // Indicates whether we are still fetching data
    private boolean isLoading = true;
    private int currentPage = 1 ;
    private RecyclerView.LayoutManager mLayoutManager ;

    protected EndlessScrollListener(LinearLayoutManager mLayoutManager){
        this.mLayoutManager = mLayoutManager ;
    }

    @Override
    public void onScrolled(RecyclerView view , int dx , int dy){
        int lastVisibleItemPosition = 0 ;
        lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        int totalItemCount = mLayoutManager.getItemCount();

        if(isLoading && totalItemCount > previousItemCount){
            isLoading = false ;
            previousItemCount = totalItemCount ;
        }

        if(!isLoading && ((lastVisibleItemPosition + visibleThreshold) > totalItemCount)){
            currentPage++ ;
            onLoadMore(currentPage , totalItemCount , view);
        }
    }

    public abstract void onLoadMore(int currentPage , int totalItemCount , RecyclerView view) ;


}
