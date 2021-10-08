package com.cookie.details;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This interface models the behaviour of cookie processor.
 *
 * <p>author: Sekhar Karedla
 */
public interface CookieProcessor {
  /**
   * Reads the log file and loads the data into the class.
   *
   * @param filePath - path of file.
   * @throws IllegalArgumentException - if file not found.
   */
  public TreeMap<Long, String> readLogFile(String filePath) throws IllegalArgumentException;

  /**
   * Get most active cookie on a given day.
   *
   * @param date - date in String format.
   * @return - list of most active cookies of that day
   * @throws IllegalArgumentException - if given date is invalid.
   * @throws IllegalStateException - if data has been not loaded.
   */
  public List<String> getMostActiveCookie(TreeMap<Long, String> data, String date)
      throws IllegalArgumentException, IllegalStateException;
}
