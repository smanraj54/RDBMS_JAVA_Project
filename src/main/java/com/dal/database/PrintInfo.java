package com.dal.database;

import static java.lang.Thread.sleep;

public class PrintInfo {

  private static PrintInfo instance = null;

  private PrintInfo() {
    // required private constructor
  }

  public static PrintInfo getInstance() {
    if (instance == null) {
      instance = new PrintInfo();
    }
    return instance;
  }

  public void printError(String message) {
    System.err.print(message);
    try {
      sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void printMessage(String message) {
    System.out.print(message);
  }

  public void commandError() {
    printError("\n\tEnter Correct Command\n");
  }

  public void createLogFiles() {

  }
}
