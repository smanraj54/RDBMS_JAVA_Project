package com.dal.database.utils;

import com.dal.database.CreateQueries.*;
import com.dal.database.DataStorage.AllDatabases;
import com.dal.database.DataStorage.Table;
import com.dal.database.Login.AttemptLogin;
import com.dal.database.PrintInfo;
import com.dal.database.fetchdatabase.FetchDataFromFiles;
import com.dal.database.queryManagement.SplitQuery;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class InputFromUser {

    private InputFromUser(){
        PrintInfo.getInstance().printMessage("\n\t####################################\n");
        PrintInfo.getInstance().printMessage("Welcome to DVM Relational Database:\n");
        PrintInfo.getInstance().printMessage("\n\t####################################\n");

        try {
            new BasicFolderStructure();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static InputFromUser instance = null;

    public static InputFromUser getInstance(){
        if(instance == null){
            instance = new InputFromUser();
        }
        return instance;
    }

    public void inputsFromUser(Scanner sc){
        FetchDataFromFiles.fetchAllDatabases();
        FetchDataFromFiles.fetchAllUsers();
        AttemptLogin attemptLogin = new AttemptLogin();
        if(!attemptLogin.loginUser(new Scanner(System.in))){
            System.exit(0);
        }
        for(;;) {

            System.out.print(BasicInformation.getInstance().getLoginUser() + "@DVM.sql >>>>> ");
            String input = sc.nextLine();
            SplitQuery splitQuery = new SplitQuery(input);
            List<String> inputTokens = splitQuery.splitQueryTokens();
            if(!evaluateInput(inputTokens)){
                continue;
            }

        }
    }

    private boolean evaluateInput(List<String> tokens){
        if(tokens == null || tokens.size()<=0){
            return false;
        }

        List<String> newTokens = getSubTokens(tokens);

        switch (tokens.get(0).toLowerCase()){
            case "exit":{
                return(exitQuery(newTokens));
            }
            case "commit":{
                return(commit(newTokens));

            }
            case "create":{
                return(create(newTokens));
            }
            case "use":{
                return(useDatabase(newTokens));
            }
            case "show":{
                return showDatabases(newTokens);
            }
            case "insert":{
                return InsertIntoTable(newTokens);
            }
            case "rollback":{
                return RollbackValues(newTokens);
            }

            default :{
                PrintInfo.getInstance().commandError();
                return false;
            }
        }

    }

    private boolean RollbackValues(List<String> tokens){
        if(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0))){
            PrintInfo.getInstance().printMessage("\n\tRollback Successful!\n\t");
            new Rollback();
            return true;
        }

        PrintInfo.getInstance().commandError();
        return false;
    }

    private boolean InsertIntoTable(List<String> tokens){
        if(!tableQueryBasicCheck()){
            return false;
        }

        if(!tokenListValidation(tokens) || !"into".equalsIgnoreCase(tokens.get(0))){
            PrintInfo.getInstance().commandError();
            return false;
        }

        tokens = getSubTokens(tokens);
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        String tableName = tokens.get(0);
        if(!BasicInformation.getInstance().fetchDatabase().tables.containsKey(tableName.toUpperCase())){
            PrintInfo.getInstance().printError("\n\tTable does not exist!!!\n");
            return false;
        }

        tokens = getSubTokens(tokens);
        if(!tokenListValidation(tokens) || !"(".equals(tokens.get(0))){
            PrintInfo.getInstance().commandError();
            return false;
        }
        tokens = getSubTokens(tokens);
        List<String> columnNames = getBracketTokens(tokens, true);
        if(columnNames == null){
            return false;
        }
        int index = getIndexOfClosingBracket(tokens);
        if(index < 0){
            PrintInfo.getInstance().commandError();
            return false;
        }
        tokens = tokens.subList(index, tokens.size());
        tokens = getSubTokens(tokens);
        if(endOfQuery(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        if(!"values".equalsIgnoreCase(tokens.get(0))){
            PrintInfo.getInstance().commandError();
            return false;
        }
        tokens = getSubTokens(tokens);
        if(endOfQuery(tokens) || !"(".equals(tokens.get(0))){
            PrintInfo.getInstance().commandError();
            return false;
        }
        
        tokens  = getSubTokens(tokens);
        
        List<String> columnValues = getBracketTokens(tokens, false);
        if(columnValues == null){
            return false;
        }
        
        if(columnNames.size() != columnValues.size()){
            PrintInfo.getInstance().commandError();
            return false;
        }
        
        
        InsertIntoTable insert = new InsertIntoTable();
        Map<String, Object> row = fetchMapForRow(tableName, columnNames, columnValues);
        if(row == null){
            return false;
        }

        insert.InsertIntoTableValues(tableName, row);
        
        return true;
    }

    private boolean useDatabase(List<String> tokens){
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        String databaseName = tokens.get(0);
        tokens = getSubTokens(tokens);

        if(!(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0)))){
            PrintInfo.getInstance().commandError();
            return false;
        }
        UseDatabase useDatabase = new UseDatabase();
        return useDatabase.UseThisDatabase(databaseName);

    }

    private boolean showDatabases(List<String> tokens){
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        String query = tokens.get(0);
        tokens = getSubTokens(tokens);

        if(!endOfQuery(tokens) || !"databases".equalsIgnoreCase(query)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        ShowDatabases showDatabases = new ShowDatabases();
        showDatabases.showAllDatabases();

        return true;

    }

    private boolean exitQuery(List<String> tokens){
        if(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0))){
            PrintInfo.getInstance().printMessage("\n\tExit\n\t");
            System.exit(0);
        }

        PrintInfo.getInstance().commandError();
        return false;
    }

    private boolean commit(List<String> tokens){
        if(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0))){
            PrintInfo.getInstance().printMessage("\n\tCommit Successful!\n\t");
            Commit commit = new Commit();

            return true;
        }

        PrintInfo.getInstance().commandError();
        return false;

    }

    private boolean create(List<String> tokens){
        if(tokenListValidation(tokens)){
            switch (tokens.get(0).toLowerCase()){
                case "database" : {
                    return (createDatabase(getSubTokens(tokens)));
                }
                case "table" : {
                    return(createTable(getSubTokens(tokens)));
                }
                default:{
                    PrintInfo.getInstance().commandError();
                    return false;
                }
            }
        }
        else{
            PrintInfo.getInstance().commandError();
        }
        return false;
    }

    private boolean createDatabase(List<String> tokens){
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        if(!regexValidationOfName(tokens.get(0))){
            PrintInfo.getInstance().printError("\n\tDatabase Name should only contain characters\n");
            return false;
        }
        String name = tokens.get(0);
        tokens = getSubTokens(tokens);
        if(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0))){
            CreateDatabase createDatabase = new CreateDatabase();
            return(createDatabase.addDatabase(name));
        }
        PrintInfo.getInstance().commandError();
        return false;
    }

    private boolean createTable(List<String> tokens){
        if(!tableQueryBasicCheck()){
            return false;
        }
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return false;
        }
        if(!regexValidationOfName(tokens.get(0))){
            PrintInfo.getInstance().printError("\n\tTable Name should only contain characters\n");
            return false;
        }
        String name = tokens.get(0);
        tokens = getSubTokens(tokens);
        if(tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0))){
            PrintInfo.getInstance().printError("\n\tEnter input columns for the tables\n");
            return false;
        }
        List<String> columns = fetchColumnsForTableCreate(tokens);
        if(columns == null){
            return false;
        }
        Map<String, String> columnAndType = fetchColumnAndTypeMap(columns);
        if(columnAndType == null){
            return false;
        }

        CreateTable createTable = new CreateTable();
        return createTable.addTable(name, columnAndType);
    }

    private List<String> fetchColumnsForTableCreate(List<String> tokens){
        if(!("(").equals(tokens.get(0))){
            PrintInfo.getInstance().commandError();
            return null;
        }
        tokens = getSubTokens(tokens);
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return null;
        }
        int lastIndex = tokens.size()-1;
        String last = tokens.get(lastIndex);
        String secondLast = tokens.get(lastIndex-1);
        if(")".equals(last)){
            return tokens.subList(0, lastIndex);
        }
        else if(";".equals(last) && ")".equals(secondLast)){
            return tokens.subList(0, lastIndex-1);
        }

        PrintInfo.getInstance().commandError();
        return null;
    }

    private Map<String, String> fetchColumnAndTypeMap(List<String> tokens){
        Map<String, String> columnsWithDataType = new LinkedHashMap<>();
        for(;;){
            if(tokens == null || tokens.size()<=0){
                break;
            }
            if(tokens.size() < 2){
                PrintInfo.getInstance().commandError();
                return null;
            }
            String columnName = tokens.get(0).toUpperCase();
            String columnType = tokens.get(1);
            if(!regexValidationOfName(columnName) || !regexValidationOfName(columnType) || columnDataTypeValidation(columnType) == null){
                PrintInfo.getInstance().commandError();
                return null;
            }

            columnType = columnDataTypeValidation(columnType);

            columnsWithDataType.put(columnName, columnType);
            tokens = getSubTokens(getSubTokens(tokens));
            if(tokens!=null && tokens.size()>0){
                if((",").equals(tokens.get(0))){
                    tokens = getSubTokens(tokens);
                }
                else{
                    PrintInfo.getInstance().commandError();
                    return null;
                }
            }
        }
        return columnsWithDataType;
    }

    private boolean regexValidationOfName(String name){
        String regex = "^[a-zA-Z]++$";

        Pattern VALIDATE = Pattern.compile(regex,
                Pattern.CASE_INSENSITIVE);
        if (!VALIDATE.matcher(name).matches()) {
            return false;
        }
        return true;
    }

    private List<String> getSubTokens(List<String> tokens){
        List<String> newTokens = null;
        if(tokens.size()>1){
            newTokens = tokens.subList(1, tokens.size());
        }
        return newTokens;
    }

    private boolean tokenListValidation(List<String> tokens){
        return(tokens != null && tokens.size()>=1);
    }

    private String columnDataTypeValidation(String datatype){
        switch(datatype.toLowerCase()){
            case "string" : {
                return "String";
            }
            case "double" : {
                return "Double";
            }
            case "boolean" : {
                return "Boolean";
            }
            case "int" : {
                return "Integer";
            }
            case "integer" : {
                return "Integer";
            }
            default:{
                return null;
            }

        }
    }

    private boolean tableQueryBasicCheck(){
        if(BasicInformation.getInstance().fetchDatabase() == null){
            PrintInfo.getInstance().printError("\n\tSelect Database First!!!\n");
            return false;
        }
        return true;
    }

    private boolean endOfQuery(List<String> tokens){
        return (tokens == null || tokens.size()<=0 || (";").equals(tokens.get(0)));
    }

    private int getIndexOfClosingBracket(List<String> tokens){
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return -1;
        }
        int t=0;
        for(;;){
            if(endOfQuery(tokens)){
                return -1;
            }
            if((")").equals(tokens.get(t))){
                break;
            }
            t++;
        }
        return t;
    }

    private Map<String, Object> fetchMapForRow(String tableName,List<String> columnNames, List<String> columnValues){
        Map<String, Object> row = new LinkedHashMap<>();
        Map<String , String> columnAndDatatype = BasicInformation.getInstance().fetchDatabase().tables.get(tableName).columnNamesAndInputType;
        for(int t=0; t<columnNames.size(); t++){
            if(!columnAndDatatype.containsKey(columnNames.get(t).toUpperCase())){
                PrintInfo.getInstance().printError("\n\tRow does not exist\n");
                return null;
            }
            switch(columnAndDatatype.get(columnNames.get(t)).toLowerCase()){
                case "integer":{
                    try{
                        Integer.parseInt(columnValues.get(t));
                        row.put(columnNames.get(t).toUpperCase(), columnValues.get(t));
                    }catch(Exception e){
                        PrintInfo.getInstance().printError("\n\tData type of input parameter: \" "+columnNames.get(t)+" \" is wrong\n");
                        return null;
                    }
                }
                case "double":{
                    try{
                        Double.parseDouble(columnValues.get(t));
                        row.put(columnNames.get(t).toUpperCase(), columnValues.get(t));
                    }catch(Exception e){
                        PrintInfo.getInstance().printError("\n\tData type of input parameter: \" "+columnNames.get(t)+" \" is wrong\n");
                        return null;
                    }
                }
                case "boolean":{
                    try{
                        Boolean.parseBoolean(columnValues.get(t));
                        row.put(columnNames.get(t).toUpperCase(), columnValues.get(t));
                    }catch(Exception e){
                        PrintInfo.getInstance().printError("\n\tData type of input parameter: \" "+columnNames.get(t)+" \" is wrong\n");
                        return null;
                    }
                }
                case "string":{
                    try{
                        (columnValues.get(t)).toString();
                        row.put(columnNames.get(t).toUpperCase(), columnValues.get(t));
                    }catch(Exception e){
                        PrintInfo.getInstance().printError("\n\tData type of input parameter: \" "+columnNames.get(t)+" \" is wrong\n");
                        return null;
                    }
                }
                default:{
                    PrintInfo.getInstance().printError("\n\tData type of input parameter: \" "+columnNames.get(t)+" \" is wrong\n");
                    return null;
                }
            }


        }
        return row;
    }


    private List<String> getBracketTokens(List<String> tokens, boolean RegexRequired){
        if(!tokenListValidation(tokens)){
            PrintInfo.getInstance().commandError();
            return null;
        }

        List<String> newTokens = new ArrayList<>();

        for(;;){
            if(!tokenListValidation(tokens)){
                PrintInfo.getInstance().commandError();
                return null;
            }
            if(!regexValidationOfName(tokens.get(0)) && RegexRequired){
                PrintInfo.getInstance().commandError();
                return null;
            }
            newTokens.add(tokens.get(0));
            tokens = getSubTokens(tokens);
            if(")".equals(tokens.get(0))){
                break;
            }
            if(!",".equals(tokens.get(0))){
                PrintInfo.getInstance().commandError();
                return null;
            }
            if(tokens == null){
                PrintInfo.getInstance().commandError();
                return null;
            }
            tokens = getSubTokens(tokens);
        }
        return newTokens;
    }



}
