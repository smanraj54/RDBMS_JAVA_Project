package com.dal.database.utils;

import com.dal.database.PrintInfo;

import java.io.File;
import java.io.FileWriter;

public class LogGenerator {

  private static LogGenerator instance = null;

  private LogGenerator() {
    logFileGenerator();
  }

  public static LogGenerator getInstance() {
    if (instance == null) {
      instance = new LogGenerator();
    }
    return instance;
  }

  public static String pathOfGeneralLogFiles = "./FilesOfData/Logs/GeneralLogs.txt";
  public static String pathOfEventLogFiles = "./FilesOfData/Logs/EventLogs.txt";
  public static String pathOfQueryLogFiles = "./FilesOfData/Logs/QueryLogs.txt";
  final PrintInfo printer = PrintInfo.getInstance();

  FileWriter event;
  FileWriter general;
  FileWriter query;

  private void logFileGenerator() {
    try {
      File eventFile = new File(pathOfEventLogFiles); //default event log text file
      File generalFile = new File(pathOfGeneralLogFiles);  //default general log text
      File queryFile = new File(pathOfQueryLogFiles);  //default query log text
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
      event = new FileWriter(eventFile, true);   //true means while is
      // appended
      general = new FileWriter(generalFile, true);
      query = new FileWriter(queryFile, true);
    } catch (Exception e) {
      printer.printError(e.getMessage());
    }
  }

  public void writeToGeneralLogFile(String input) {
    try {
      general.append(input);
      general.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void writeToEventLogFile(String input) {
    try {
      event.append(input);
      event.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void writeToQueryLogFile(String input) {
    try {
      query.append(input);
      query.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
