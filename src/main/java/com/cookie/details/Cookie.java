package com.cookie.details;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the CookieProcessor.
 *
 * <p>author: Sekhar Karedla
 */
public class Cookie implements CookieProcessor {
  private static Logger logger = Logger.getLogger(Cookie.class.getName());

  private final List<String> cookieData;
  private final TreeMap<Date, String> cookieSortedData;

  public Cookie() {
    cookieData = new ArrayList<>();
    cookieSortedData = new TreeMap<>();
  }

  @Override
  public void readLogFile(String filePath) throws IllegalArgumentException {
    if (filePath == null || filePath.equals("")) {
      throw new IllegalArgumentException("invalid file path");
    }
    // try to open the file
    File cookieFile = new File(filePath);
    Scanner cookieScanner = null;
    try {
      cookieScanner = new Scanner(cookieFile);
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "file not found");
      throw new IllegalArgumentException("wrong file path");
    }
    // now will read the file and gather all the cookie data
    List<String> cookieData = new ArrayList<>();
    while (cookieScanner.hasNext()) {
      cookieData.add(cookieScanner.nextLine().strip());
    }
    // printing cookie data
    logger.log(Level.INFO, cookieData.toString());
    // storing cookie data in treemap
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    try {
      for (String eachCookieUsage : cookieData) {
        cookieSortedData.put(df.parse(eachCookieUsage.strip().split(",")[1]),
            eachCookieUsage.strip().split(",")[0]);
      }
    } catch (ParseException e) {
      logger.log(Level.SEVERE, "invalid date format");
      throw new IllegalArgumentException("invalid date format");
    }
  }

  @Override
  public List<String> getMostActiveCookie(String date)
      throws IllegalArgumentException, IllegalStateException {
    List<String> result = new ArrayList<>();
    if (cookieData.size() == 0) {
      logger.log(Level.SEVERE, "log file not yet processed");
      throw new IllegalStateException("log file not yet processed");
    }
    if (date == null) {
      logger.log(Level.SEVERE, "date not sent");
      throw new IllegalArgumentException("date not sent");
    }
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date dateToBeConsidered;
    try {
      dateToBeConsidered = df.parse(date);
    } catch (ParseException e) {
      logger.log(Level.SEVERE, "invalid date format");
      throw new IllegalArgumentException("invalid date format");
    }
    Date theNextDayOfDateToBeConsidered =
        new Date(dateToBeConsidered.getTime() + 24 * 60 * 60 * 1000);
    // using treeset to reduce search space
    TreeMap<Date, String> searchSpace =
        (TreeMap<Date, String>) cookieSortedData.subMap(dateToBeConsidered, true,
            theNextDayOfDateToBeConsidered, false);
    if (searchSpace == null || searchSpace.size() == 0) {
      return result;
    }
    int maxCookiesCount = 0;
    Map<String, Integer> cookieCountOfTheDay = new HashMap<>();
    for (String eachCookie : searchSpace.values()) {
      cookieCountOfTheDay.put(eachCookie, cookieCountOfTheDay.getOrDefault(eachCookie, 0) + 1);
      maxCookiesCount = Math.max(maxCookiesCount, cookieCountOfTheDay.get(eachCookie));
    }
    for (Map.Entry<String, Integer> eachCookieCount : cookieCountOfTheDay.entrySet()) {
      if (eachCookieCount.getValue() == maxCookiesCount) {
        result.add(eachCookieCount.getKey());
      }
    }
    return result;
  }
}
