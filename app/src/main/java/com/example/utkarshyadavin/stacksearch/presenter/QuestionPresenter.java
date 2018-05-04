package com.example.utkarshyadavin.stacksearch.presenter;

import android.view.View;

import com.example.utkarshyadavin.stacksearch.StackQuestionViewInterface;
import com.example.utkarshyadavin.stacksearch.api.ApiService;
import com.example.utkarshyadavin.stacksearch.helper.StackOverflowClient;
import com.example.utkarshyadavin.stacksearch.models.Question;
import com.example.utkarshyadavin.stacksearch.models.QuestionList;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by utkarshyadavin on 3/5/18.
 */

public class QuestionPresenter  {

    private StackQuestionViewInterface mQuestionView ;
    private ApiService StackApi ;

    public QuestionPresenter(StackQuestionViewInterface mQuestionView){
        this.mQuestionView = mQuestionView ;
        StackApi = StackOverflowClient.getApiService() ;
    }

    // Method fetched data and updates the ArrayList with new data each time it is called

    public void getApiResponse(String tag , final int page , String orderBy , String sort){

        Call<QuestionList> call = StackApi.getQuestionData(tag , page , orderBy , sort);
        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                if(response.isSuccessful()){
                    mQuestionView.hideProgressBar();
                    List<Question> receivedQuestions = response.body().getQuestions() ;
                    if(receivedQuestions.size()==0 && page==1){
                        // No Results were found
                        mQuestionView.noResultFoundError();
                    }
                    mQuestionView.addReceivedItems(receivedQuestions);
                }
                else {
                    mQuestionView.httpError();
                }
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof HttpException) {
                    mQuestionView.httpError();
                }
                else if (t instanceof IOException){
                    mQuestionView.connectionError();
                }
                }
        });
    }

}
