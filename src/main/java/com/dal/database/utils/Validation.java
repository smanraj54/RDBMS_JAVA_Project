package com.dal.database.utils;

public class Validation {

  public static boolean isValidInput(String input) {
    try {
      if (input.isEmpty() && input.trim().isEmpty()) {
        return false;
      } else {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isAlphaNumeric(String input) {
    try {
      if (input.isEmpty() && input.trim().isEmpty()) {
        return false;
      } else if (input.matches("[A-Za-z0-9]+")) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }
}
