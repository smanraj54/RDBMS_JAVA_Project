package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.utils.BasicInformation;

public class UseDatabase {

    public UseDatabase(){}

    public boolean UseThisDatabase(String database){
        if(AllDatabases.getInstance().databaseMap.containsKey(database)){
            BasicInformation.getInstance().setLockedDatabase(database);
            return true;
        }
        return false;
    }

}
