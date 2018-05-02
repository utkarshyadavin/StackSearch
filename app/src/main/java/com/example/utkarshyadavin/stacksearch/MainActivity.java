package com.example.utkarshyadavin.stacksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.utkarshyadavin.stacksearch.adapter.QuestionAdapter;
import com.example.utkarshyadavin.stacksearch.models.Question;
import com.example.utkarshyadavin.stacksearch.models.QuestionList;
import com.example.utkarshyadavin.stacksearch.models.User;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView ;
    private QuestionAdapter qAdapter ;
    private QuestionList mQuestionList ;
    private ProgressBar mProgressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.question_list_item_recyclerview);
        mQuestionList = new QuestionList() ;
        User firstUser = new User() ;
        firstUser.setProfileImageUrl("http://cdn.shopify.com/s/files/1/1482/3564/products/hello_grande.jpg?v=1480478786");
        firstUser.setUsername("Utkarsh");
        Question firstQuestion = new Question() ;
        firstQuestion.setOwner(firstUser);
        firstQuestion.setQuestiondetails("How to find the rank of an array element in java");
        firstQuestion.setQuestionTitle("Array Rank");
        firstQuestion.setQuestionLink("https://www.geeksforgeeks.org/rank-elements-array/");
        List<Question> questions = mQuestionList.getQuestions() ;
        questions.add(firstQuestion);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        qAdapter = new QuestionAdapter(MainActivity.this , questions);
        mRecyclerView.setAdapter(qAdapter);




    }
}
