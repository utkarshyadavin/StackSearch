package com.example.utkarshyadavin.stacksearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by utkarshyadavin on 2/5/18.
 */

public class QuestionList {

    @SerializedName("items")
    @Expose
    private List<Question> questions = new ArrayList<Question>() ;

    //getter method

    public List<Question> getQuestions() {
        return questions;
    }

    //Setter method

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}



