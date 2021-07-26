package com.dal.database.utils;

import com.dal.database.CreateQueries.Commit;
import com.dal.database.CreateQueries.CreateDatabase;
import com.dal.database.Login.AttemptLogin;
import com.dal.database.Login.FetchAllUsers;
import com.dal.database.PrintInfo;
import com.dal.database.fetchdatabase.FetchDataFromFiles;
import com.dal.database.queryManagement.SplitQuery;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFromUser {

    private InputFromUser(){
        PrintInfo.getInstance().printMessage("\n\t####################################\n");
        PrintInfo.getInstance().printMessage("Welcome to DVM Distributed Database:\n");
        PrintInfo.getInstance().printMessage("\n\t####################################\n");

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
            default :{
                PrintInfo.getInstance().commandError();
                return false;
            }
        }

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
                    createDatabase(getSubTokens(tokens));
                    break;
                }
                default:{
                    PrintInfo.getInstance().commandError();
                    return false;
                }
            }
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

}
