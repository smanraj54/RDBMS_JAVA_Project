package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Database;
import com.dal.database.saveData.WriteDatabaseToFile;

public class CreateDatabase {

    Database database;
    public CreateDatabase(){
        database = new Database();
    }

    public void addDatabase(String databaseName){
        database.databaseName = databaseName;
        AllDatabases.getInstance().databaseMap.put(databaseName, database);
        WriteDatabaseToFile.getInstance().writeThisDatabasesList(AllDatabases.getInstance());
    }

}
