package com.example.utkarshyadavin.stacksearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call ;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.utkarshyadavin.stacksearch.adapter.QuestionAdapter;
import com.example.utkarshyadavin.stacksearch.api.ApiService;
import com.example.utkarshyadavin.stacksearch.helper.StackOverflowClient;
import com.example.utkarshyadavin.stacksearch.models.Question;
import com.example.utkarshyadavin.stacksearch.models.QuestionList;
import com.example.utkarshyadavin.stacksearch.models.User;


import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private RecyclerView mRecyclerView ;
    private QuestionAdapter qAdapter ;
    private QuestionList mQuestionList ;
    private ProgressBar mProgressBar ;
    private ApiService StackApi ;
    private List<Question> questions ;
    private String ORDER_BY = "desc" ;
    private String SORT_BY = "activity" ;
    private String DEFAULT_TAG = "cpp" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MAinactivity" , "Main Activity created");
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.question_list_item_recyclerview);
        mQuestionList = new QuestionList() ;
        questions = mQuestionList.getQuestions() ;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        qAdapter = new QuestionAdapter(MainActivity.this , questions);
        mRecyclerView.setAdapter(qAdapter);
        StackApi = StackOverflowClient.getApiService() ;
        mProgressBar.setVisibility(View.VISIBLE);
        getApiResponse(DEFAULT_TAG , 1 , ORDER_BY , SORT_BY);

    }
















    public void getApiResponse(String tag , int page , String orderBy , String sort){

        Call<QuestionList> call = StackApi.getQuestionData(tag , page , orderBy , sort);
        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {

                if(response.isSuccessful()){
                    mProgressBar.setVisibility(View.INVISIBLE);
                    List<Question> receivedQuestions = response.body().getQuestions() ;
                    questions.addAll(receivedQuestions) ;
                    qAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });
    }




}
