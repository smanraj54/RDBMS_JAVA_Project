package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllDatabases implements Serializable {

    public Map<String, Database> databaseMap = null;

    public AllDatabases(){
        databaseMap = new HashMap<>();
    }

}
