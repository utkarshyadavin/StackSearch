package com.example.utkarshyadavin.stacksearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by utkarshyadavin on 2/5/18.
 */

public class User {

    @SerializedName("display_name")
    @Expose
    private String username ;

    @SerializedName("profile_image")
    @Expose
    private String profileImageUrl ;


    //Getter methods


    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }


    //Setter methods


    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}
