package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Database implements Serializable {

    public Map<String, Table > tables;
    public Set<String> lockedTables;

    public Database(){
        tables = new HashMap<>();
        lockedTables = new HashSet<>();
    }
}
