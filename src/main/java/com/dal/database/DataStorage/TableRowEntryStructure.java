package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRowEntryStructure implements Serializable {
    Map<String, Object> Inputs = null;

    public TableRowEntryStructure(){
        Inputs = new LinkedHashMap<>();
    }

}
