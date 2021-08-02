package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllDatabases implements Serializable {

    public Map<String, Database> databaseMap = null;
    private static AllDatabases instance = null;
    public static String space = "\t";

    private AllDatabases(){
        databaseMap = new HashMap<>();
    }

    public static AllDatabases getInstance() {
        if (instance == null) {
            instance = new AllDatabases();
        }

        return instance;
    }

    public static void setInstance(AllDatabases allDatabases){
        instance = allDatabases;
    }

    public String getAllMyDatabases(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append(space+"[\n");

        boolean first = true;
        for(Map.Entry<String, Database> entry : databaseMap.entrySet()){
            if(!first){
                stringBuilder.append(" , \n");
            }
            else{
                first = false;
            }
            stringBuilder.append(space + "\t" + entry.getKey() + " : " + entry.getValue().getMyDatabase());
        }
        stringBuilder.append("\n");

        stringBuilder.append(space +"]\n");
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }
}
