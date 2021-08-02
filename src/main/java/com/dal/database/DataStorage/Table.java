package com.dal.database.DataStorage;

import java.io.Serializable;
import java.util.*;

public class Table implements Serializable {

    public Map<String, String> columnNamesAndInputType;
    public List<TableRowEntryStructure> rows;
    public String tableName = null;
    public String primaryKey = null;
    public String foreignKey = null;
    public static String space = Database.space+"\t\t";

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

    public String getMyTableData(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        stringBuilder.append(space+"\t"+ "PrimaryKey"+" : " + primaryKey + "\n");
        stringBuilder.append(space+"\t"+ "ForeignKey"+" : " + foreignKey + "\n");

        stringBuilder.append(space+"\t"+ tableName+" : [\n");

        boolean first = true;

        for(TableRowEntryStructure row : rows){
            if(!first){
                stringBuilder.append(" , \n");
            }
            else{
                first = false;
            }
            stringBuilder.append(space+"\t"+row.getMyRowData());
        }
        stringBuilder.append("\n");

        stringBuilder.append(space + "\t\t"+"]\n");
        stringBuilder.append(space+"}");

        return stringBuilder.toString();
    }

}
