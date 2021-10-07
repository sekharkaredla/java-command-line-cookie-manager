package com.cookie.details;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

  public Cookie() {
    cookieData = new ArrayList<>();
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
  }

  @Override
  public void getMostActiveCookie(String date)
      throws IllegalArgumentException, IllegalStateException {
    if (cookieData.size() == 0) {
      logger.log(Level.SEVERE, "log file not yet processed");
      throw new IllegalStateException("log file not yet processed");
    }
    if (date == null) {
      logger.log(Level.SEVERE, "date not sent");
      throw new IllegalArgumentException("date not sent");
    }
    
  }
}
