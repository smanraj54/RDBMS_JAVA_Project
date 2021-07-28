package com.dal.database.utils;

import java.io.*;

public class LogGenerator {

  public static String pathOfGeneralLogFiles = "./RawData/Logs/GeneralLogs.txt";
  public static String pathOfEventLogFiles = "./RawData/Logs/EventLogs.txt";
  public static String pathOfQueryLogFiles = "./RawData/Logs/QueryLogs.txt";

  public static void logFileGenerator() throws IOException {
    File eventFile = new File("EventLogs.txt"); //default event log text file
    File generalFile = new File("GeneralLogs.txt");  //default general log text
    File queryFile = new File("QueryLogs.txt");  //default general log text
    // file
    if (eventFile.createNewFile())   //if no file exists, we create one
    {
      System.out.println("New Event Logs created!");
    }
    if (generalFile.createNewFile())   //if no file exists, we create one
    {
      System.out.println("New General Logs created!");
    }
    if (queryFile.createNewFile())   //if no file exists, we create one
    {
      System.out.println("New General Logs created!");
    }
    FileWriter fw = new FileWriter(eventFile, true);   //true means while is appended
    FileWriter fw1 = new FileWriter(generalFile, true);
    FileWriter fw2 = new FileWriter(queryFile, true);
  }

}
