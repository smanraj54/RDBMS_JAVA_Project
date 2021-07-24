package com.dal.database.utils;

import java.io.Serializable;

public class BasicInformation {

    private String lockedDatabase = null;
    private static BasicInformation instance = null;

    private BasicInformation(){
    }

    public static BasicInformation getInstance(){
        if(instance == null){
            instance = new BasicInformation();
        }
        return instance;
    }

    public String getLockedDatabase(){
        return lockedDatabase;
    }

    public void setLockedDatabase(String lockedDatabase){
        this.lockedDatabase = lockedDatabase;
    }




}
