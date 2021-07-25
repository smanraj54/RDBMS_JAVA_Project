package com.dal.database.utils;

import com.dal.database.CreateQueries.Commit;
import com.dal.database.Login.AttemptLogin;
import com.dal.database.Login.FetchAllUsers;
import com.dal.database.PrintInfo;
import com.dal.database.fetchdatabase.FetchDataFromFiles;
import com.dal.database.queryManagement.SplitQuery;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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

        List<String> newTokens = null;
        if(tokens.size()>1){
            newTokens = tokens.subList(1, tokens.size());
        }
        switch (tokens.get(0).toLowerCase()){
            case "exit":{
                return(exitQuery(newTokens));
            }
            case "commit":{
                commit(newTokens);
                return false;

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


}
