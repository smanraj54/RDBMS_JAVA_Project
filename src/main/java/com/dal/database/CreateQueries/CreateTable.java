package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Database;
import com.dal.database.DataStorage.Table;
import com.dal.database.PrintInfo;
import com.dal.database.utils.BasicInformation;
import com.sun.jdi.request.DuplicateRequestException;

import java.util.DuplicateFormatFlagsException;
import java.util.Map;

import static java.lang.Thread.sleep;

public class CreateTable {
    private Database database;

    public CreateTable(){
        database = BasicInformation.getInstance().fetchDatabase();
        if(database == null){
            PrintInfo.getInstance().printError("\n\tSelect Database First!!!!");
        }
    }

    public boolean addTable(String tableName, Map<String, String> columnNamesAndInputType){
        if(database == null){
            return false;
        }
        if(database.tables.containsKey(tableName)){
            PrintInfo.getInstance().printMessage("\n\tTable name already present");
            return false;
        }
        Table table = new Table();
        table.tableName = tableName;
        table.columnNamesAndInputType = columnNamesAndInputType;
        database.tables.put(tableName, table);
        return true;
    }

}
