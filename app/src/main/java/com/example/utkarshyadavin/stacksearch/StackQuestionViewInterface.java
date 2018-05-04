package com.example.utkarshyadavin.stacksearch;

import com.example.utkarshyadavin.stacksearch.models.Question;

import java.util.List;
import java.util.Queue;

/**
 * Created by utkarshyadavin on 3/5/18.
 */

public interface StackQuestionViewInterface {

    public void showProgressBar();
    public void hideProgressBar() ;
    public void addReceivedItems(List<Question> receivedQuestions) ;
    public void noResultFoundError() ;
    public void httpError() ;
    public void connectionError() ;
}
