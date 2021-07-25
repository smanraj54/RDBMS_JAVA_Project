package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Database;

import java.util.Map;

public class ShowDatabases {

    public ShowDatabases(){
        System.out.println("All Database Tables:");
        System.out.println("###---------------------------------####");

    }

    public void showAllDatabases(){

        for(Map.Entry<String, Database> databaseEntry : AllDatabases.getInstance().databaseMap.entrySet()){
            System.out.println(databaseEntry.getKey());
        }
    }

}
