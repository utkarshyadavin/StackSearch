package com.example.utkarshyadavin.stacksearch.helper;

import com.example.utkarshyadavin.stacksearch.api.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by utkarshyadavin on 2/5/18.
 */

public class StackOverflowClient {

    // Root Url for Stack Search
    private static final String ROOT_URL = "https://api.stackexchange.com/" ;


    // Getting a retrofit instance
    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;
    }


    // Getting the Api Client object for making network calls
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class) ;
    }

}