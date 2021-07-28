package com.dal.database.CreateQueries;

import com.dal.database.DataStorage.Table;
import com.dal.database.PrintInfo;
import com.dal.database.utils.BasicInformation;

import java.util.Map;

public class DescribeTable {

    public boolean descThisTable(Table table){
        if(table == null || table.columnNamesAndInputType == null){
            PrintInfo.getInstance().printError("\n\tTable is Invalid!!\n");
            return false;
        }
        PrintInfo.getInstance().printMessage("\n#----------------------------------------------#\n");
        PrintInfo.getInstance().printMessage("\t Database::"+BasicInformation.getInstance().getLockedDatabase()
                +"\t\t----->\t\tTableName:: "+table.tableName + "\n");
        PrintInfo.getInstance().printMessage("#----------------------------------------------#\n");
        for(Map.Entry<String, String> entry : table.columnNamesAndInputType.entrySet()){
            PrintInfo.getInstance().printMessage("\n\t\t"+entry.getKey()+"\t\t----->\t\t"+ entry.getValue());
        }
        PrintInfo.getInstance().printMessage("\n#----------------------------------------------#\n");

        return true;
    }
}
