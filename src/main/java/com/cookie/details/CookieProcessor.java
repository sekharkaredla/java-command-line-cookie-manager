package com.cookie.details;

import java.util.List;
import java.util.SortedMap;

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
   * @return - A sortedMap of timings as keys and cookies as values.
   * @throws IllegalArgumentException - when filePath is invalid.
   */
  public SortedMap<Long, String> readLogFile(String filePath) throws IllegalArgumentException;

  /**
   * Get most active cookie on a given day.
   *
   * @param data - sortedMap of timings and corresponding cookies.
   * @param date - date for which we want most active cookie.
   * @return - list of most active cookies that day.
   * @throws IllegalArgumentException - when invalid inputs are given.
   * @throws IllegalStateException    - when log file is not processed yet.
   */
  public List<String> getMostActiveCookie(SortedMap<Long, String> data, String date)
      throws IllegalArgumentException, IllegalStateException;
}
