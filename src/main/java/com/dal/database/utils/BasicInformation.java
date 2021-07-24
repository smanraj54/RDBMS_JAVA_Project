package com.dal.database.utils;

import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Database;
import com.dal.database.PrintInfo;

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

    public Database fetchDatabase(){

        String databaseName = BasicInformation.getInstance().getLockedDatabase();
        if(databaseName == null){
            return null;
        }

        Database database = AllDatabases.getInstance().databaseMap.get(databaseName);

        return database;
    }


}
