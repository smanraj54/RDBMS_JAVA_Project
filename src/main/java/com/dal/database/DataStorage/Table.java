package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.*;

public class Table implements Serializable {

    public Map<String, String> columnNamesAndInputType;
    public List<TableRowEntryStructure> rows = new ArrayList<>();
    public String tableName = null;

    public Table() {
        columnNamesAndInputType = new LinkedHashMap<>();
    }

}
