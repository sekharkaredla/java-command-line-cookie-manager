package com.cookie.active;

import com.cookie.details.Cookie;
import com.cookie.details.CookieProcessor;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "active", description = "Gets the most active cookie",
    mixinStandardHelpOptions = true)
public final class ActiveCookie implements Runnable {

  private static Logger logger = Logger.getLogger(ActiveCookie.class.getName());

  private CookieProcessor cookieProcessor;

  @Option(names = {"-f", "--file"}, description = "File we want to process")
  String filePath = "";

  @Option(names = {"-d", "--date"}, description = "Date for which we want the most active cookie")
  String date = "";

  @Override
  public void run() {
    cookieProcessor = new Cookie();
    //trying to read the file
    TreeMap<Long, String> data;
    try {
      data = cookieProcessor.readLogFile(filePath);
    } catch (IllegalArgumentException e) {
      logger.log(Level.SEVERE, "error while processing the file");
      System.out.println("error while processing the file : " + e.getMessage());
      return;
    }
    // trying to get the most used cookie
    try {
      System.out.println(cookieProcessor.getMostActiveCookie(data, date));
    } catch (Exception e) {
      logger.log(Level.SEVERE, "error while getting most active cookie");
      System.out.println("error while getting most active cookie : " + e.getMessage());
    }
  }
}
