package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.*;

public class Table implements Serializable {

    Map<String, String> columnNamesAndInputType;
    List<TableRowEntryStructure> rows = new ArrayList<>();

    public Table() {
        columnNamesAndInputType = new LinkedHashMap<>();
    }

}
