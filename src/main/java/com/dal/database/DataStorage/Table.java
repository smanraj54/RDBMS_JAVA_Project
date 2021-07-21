package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table implements Serializable {

    Map<String, String> columnNamesAndInputType = null;
    List<TableRowEntryStructure> rows = new ArrayList<>();

    public Table(){
        columnNamesAndInputType = new HashMap<>();
    }

}
