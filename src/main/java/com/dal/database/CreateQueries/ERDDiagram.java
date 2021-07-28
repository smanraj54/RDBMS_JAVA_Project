package com.dal.database.CreateQueries;
import com.dal.database.DataStorage.Database;
import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Table;

import java.util.Map;

public class ERDDiagram {

    public boolean makeERDDiagram(){
        Map<String, Database> allDatabases = AllDatabases.getInstance().databaseMap;

        for(Map.Entry<String, Database> entry : allDatabases.entrySet()){
            DescribeTable desc = new DescribeTable();
            Database database = entry.getValue();
            if(database == null){
                continue;
            }
            for(Map.Entry<String, Table> table : database.tables.entrySet()){
                desc.descThisTable(table.getValue(), database, true);
            }
            }
            return true;
    }
}
