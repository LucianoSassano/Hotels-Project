package com.example.demo.util;

public class ErrorMessage {

  public static final String BEDDING_NOT_FOUND =
      "There wasn't any bedding according to the request";
  public static final String ROOM_NOT_FOUND = "There wasn't any room according to the request";
  public static final String HOTEL_NOT_FOUND = "There wasn't any hotel according to the request";
  public static final String RESERVATION_NOT_FOUND =
      "There wasn't any reservation according to the request";
  public static final String NOT_NULL = "Field cannot be null";
  public static final String NOT_BLANK = "Field cannot be null or blank";
  public static final String NOT_NEGATIVE = "Field must be a positive number";
  public static final String INVALID_EMAIL = "Field must provide a valid E-mail";
  public static final String INVALID_STRING_SIZE = "Field must be between 1 and 50 characters";
  public static final String INVALID_DATE_FORMAT = "Date format must be " + Constants.DATE_PATTERN;
}
