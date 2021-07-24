package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllDatabases implements Serializable {

    public Map<String, Database> databaseMap = null;
    private static AllDatabases instance = null;

    private AllDatabases(){
        databaseMap = new HashMap<>();
    }

    public static AllDatabases getInstance() {
        if (instance == null) {
            instance = new AllDatabases();
        }

        return instance;
    }
}
