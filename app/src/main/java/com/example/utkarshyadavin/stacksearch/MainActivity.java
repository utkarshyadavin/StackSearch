package com.example.utkarshyadavin.stacksearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;


import com.example.utkarshyadavin.stacksearch.adapter.QuestionAdapter;
import com.example.utkarshyadavin.stacksearch.models.Question;
import com.example.utkarshyadavin.stacksearch.models.QuestionList;
import com.example.utkarshyadavin.stacksearch.presenter.QuestionPresenter;


import java.util.List;

public class MainActivity extends AppCompatActivity implements StackQuestionViewInterface  {
    private QuestionPresenter mPresenter ;
    private RecyclerView mRecyclerView ;
    private QuestionAdapter qAdapter ;
    private QuestionList mQuestionList ;
    private List<Question> questions ;
    private ProgressBar mProgressBar ;
    private EndlessScrollListener mScrollListener ;
    private String ORDER_BY = "desc" ;
    private String SORT_BY = "activity" ;
    private String DEFAULT_TAG = "cpp" ;
    private String query ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new QuestionPresenter(this) ;
        query = getQuestionQueryTag() ;
        if(query==null)
            query = DEFAULT_TAG ;
        updateUI(query);
    }

    public void updateUI(final String query){
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.question_list_item_recyclerview);
        mQuestionList = new QuestionList() ;
        questions = mQuestionList.getQuestions() ;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        qAdapter = new QuestionAdapter(MainActivity.this , questions);
        mRecyclerView.setAdapter(qAdapter);
        mScrollListener = new EndlessScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, RecyclerView view) {
                showProgressBar();
                mPresenter.getApiResponse(query, currentPage , ORDER_BY , SORT_BY);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        showProgressBar();
        mPresenter.getApiResponse(query ,1 , ORDER_BY , SORT_BY);
    }

    // Method returns the query tag enterred by the user

    public String getQuestionQueryTag(){
        Intent intent = getIntent() ;
        if(intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY) ;
            return query ;
        }
        else{
        return null ; }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu , menu);
        SearchManager mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(mSearchManager!=null ? mSearchManager.getSearchableInfo(getComponentName()):null);
        mSearchView.setIconified(true);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryRefinementEnabled(true);
        return true ;
    }

    @Override
    public void addReceivedItems(List<Question> receivedQuestions){
        questions.addAll(receivedQuestions) ;
        qAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
    }

}
