package com.example.demo.util;

public class SharedUtils {

  public static String adjustLocalDate(String date) {

    if (date.contains("/")) {
      date = date.replace("/", "-");
    }
    if (date.contains(".")) {
      date = date.replace(".", "-");
    }

    return date;
  }
}
