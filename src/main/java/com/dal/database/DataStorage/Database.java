package com.dal.database.DataStorage;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database implements Serializable {

    public Map<String, Table > tables;
    public Set<String> lockedTables;
    public String databaseName = null;
    public static String space = AllDatabases.space+"\t";

    public Database(){
        tables = new HashMap<>();
        lockedTables = new HashSet<>();
    }

    public String getMyDatabase(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "{\n");
        stringBuilder.append(space+"\t"+ databaseName +" : [\n");

        boolean first = true;
        for(Map.Entry<String, Table> entry : tables.entrySet()){
            if(!first){
                stringBuilder.append(" , \n");
            }
            else{
                first = false;
            }
            stringBuilder.append(space + "\t\t" + entry.getKey() + " : " + entry.getValue().getMyTableData());
        }
        stringBuilder.append("\n");

        stringBuilder.append(space + "\t"+"]\n");
        stringBuilder.append(space+ "}");

        return stringBuilder.toString();
    }


}
