package com.dal.database.utils;

import com.dal.database.fetchdatabase.FetchDataFromDataFile;
import com.dal.database.fetchdatabase.FetchDataFromFiles;

import static com.dal.database.utils.Application.AllDatabasesPath;

public class Script {
    public static void runScript(){

        FetchDataFromDataFile.fetchDataFromFile(AllDatabasesPath);
        //FetchDataFromFiles.fetchAllDatabases();
        FetchDataFromFiles.fetchAllUsers();

    }
}
