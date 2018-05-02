package com.example.utkarshyadavin.stacksearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by utkarshyadavin on 2/5/18.
 */

public class Question {

    @SerializedName("owner")
    @Expose
    private User owner ;

    @SerializedName("link")
    @Expose
    private String questionLink ;

    @SerializedName("title")
    @Expose
    private String questionTitle ;

    @SerializedName("body")
    @Expose
    private String questiondetails ;


    // getter methods


    public User getOwner() {
        return owner;
    }

    public String getQuestionLink() {
        return questionLink;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestiondetails() {
        return questiondetails;
    }


    // setter methods


    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setQuestionLink(String questionLink) {
        this.questionLink = questionLink;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setQuestiondetails(String questiondetails) {
        this.questiondetails = questiondetails;
    }

}
