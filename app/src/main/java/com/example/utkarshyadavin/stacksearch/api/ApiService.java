package com.example.utkarshyadavin.stacksearch.api;

import com.example.utkarshyadavin.stacksearch.models.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by utkarshyadavin on 2/5/18.
 */

public interface ApiService {

    @GET("/2.2/questions/?site=stackoverflow&filter=withbody")
    // Specifying the Api End Points and the return type in the Call Object

    Call<QuestionList> getQuestionData(
            @Query("tagged") String tag ,
            @Query("page") int page ,
            @Query("order") String orderBy ,
            @Query("sort") String sortBy
            ) ;
}
