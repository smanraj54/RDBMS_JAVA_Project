package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.Database;
import com.dal.database.PrintInfo;
import com.dal.database.utils.BasicInformation;

import java.util.ArrayList;
import java.util.List;

public class DeleteTableEntry {
    private Database database;

    private List<Integer> indexes = null;
    private String tableName;

    public DeleteTableEntry(){
        database = BasicInformation.getInstance().fetchDatabase();
        if(database == null) {
            PrintInfo.getInstance().printError("\n\tSelect Database First!!!!");
            return;
        }

        indexes = new ArrayList<>();
    }

    public boolean deleteTableEntriesWithCondition(String table, List<String> conditionFromQuery){
        if(database == null){
            return false;
        }

        return true;
    }

//    private List<Integer> evaluateCondition(List<String> condition){
//        List<Integer> indexes = new ArrayList<>();
//
//    }





}
