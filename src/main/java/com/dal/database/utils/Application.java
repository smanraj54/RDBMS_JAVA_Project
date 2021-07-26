package com.dal.database.utils;

import com.dal.database.Login.RegisterUser;
import com.dal.database.queryManagement.SplitQuery;

import java.util.Scanner;

public class Application {

  public static String pathOfUsers = "./RawData/LoginUsersData/AllUsers";
  public static String pathOfDataBase = "./RawData/databases/allDatabases";

  public static void main(String[] args) {
        /*RegisterUser registerUser = new RegisterUser();
        registerUser.registerNewUser("root", "root123");
        registerUser.registerNewUser("manu", "manu123");
        registerUser.writeToObjectFile();*/

//        AttemptLogin login = new AttemptLogin();
//        login.loginUser(new Scanner(System.in));
//
    //SplitQuery splitQuery = new SplitQuery(" Select (name, value, data) from oss_ref_data where name = 'erfverf vrfvr';");
//        SplitQuery splitQuery = new SplitQuery(" CREATE TABLE Persons (" +
//                "    PersonID int," +
//                "    LastName String," +
//                "    FirstName String," +
//                "    Address String," +
//                "    City String" +
//                ");");
    //splitQuery.splitQueryTokens();

    InputFromUser.getInstance().inputsFromUser(new Scanner(System.in));


  }

}
