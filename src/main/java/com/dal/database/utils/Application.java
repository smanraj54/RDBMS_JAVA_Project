package com.dal.database.utils;

import com.dal.database.Login.AttemptLogin;
import com.dal.database.Login.RegisterUser;
import com.dal.database.PrintInfo;
import com.dal.database.fetchdatabase.FetchDataFromFiles;
import com.dal.database.queryManagement.SplitQuery;

import java.util.Scanner;

public class Application {

  public static String pathOfUsers = "./RawData/LoginUsersData/AllUsers";
  public static String pathOfDataBase = "./RawData/databases/allDatabases";

  public static void main(String[] args) {

    FetchDataFromFiles.fetchAllDatabases();
    FetchDataFromFiles.fetchAllUsers();

    final Scanner scanner = new Scanner(System.in);
    final PrintInfo printer = PrintInfo.getInstance();
    final InputFromUser inputFromUser = InputFromUser.getInstance();


    printer.printMessage("\t####################################\n");
    printer.printMessage("\tWelcome to DVM Relational Database:\n");
    printer.printMessage("\t####################################\n");

    while (true) {
      printer.printMessage("\n1. User registration.\n");
      printer.printMessage("2. User login.\n");
      printer.printMessage("3. Exit.\n");
      printer.printMessage("Select an option:\n");
      final String input = scanner.nextLine();

      switch (input) {
        case "1":
          inputFromUser.registerUser(scanner);
          break;
        case "2":
          inputFromUser.inputsFromUser(scanner);
          break;
        case "3":
          System.exit(0);
        default:
          break;
      }
    }


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

  }

}
