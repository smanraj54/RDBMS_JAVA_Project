package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.*;

public class Table implements Serializable {

    public Map<String, String> columnNamesAndInputType;
    public List<TableRowEntryStructure> rows;
    public String tableName = null;
    public String primaryKey = null;
    public String foreignKey = null;

    public Table() {
        columnNamesAndInputType = new LinkedHashMap<>();
        rows = new ArrayList<>();
    }

    public Table duplicateTable(){
        Table dup = new Table();
        dup.columnNamesAndInputType = this.columnNamesAndInputType;
        dup.rows = new ArrayList<>(this.rows);
        dup.tableName = this.tableName;
        return dup;
    }

}
