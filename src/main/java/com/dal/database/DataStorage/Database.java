package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {

    Map<String, Table > tables = null;

    public Database(){
        tables = new HashMap<>();
    }
}
