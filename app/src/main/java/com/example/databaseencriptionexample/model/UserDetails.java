package com.example.databaseencriptionexample.model;

public class UserDetails {

    private String mUserFirstName;
    private String mUserLastName;

    public UserDetails(String mUserFirstName, String mUserLastName) {
        this.mUserFirstName = mUserFirstName;
        this.mUserLastName = mUserLastName;
    }

    public String getmUserFirstName() {
        return mUserFirstName;
    }

    public void setmUserFirstName(String mUserFirstName) {
        this.mUserFirstName = mUserFirstName;
    }

    public String getmUserLastName() {
        return mUserLastName;
    }

    public void setmUserLastName(String mUserLastName) {
        this.mUserLastName = mUserLastName;
    }
}
