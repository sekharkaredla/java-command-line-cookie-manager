package com.cookie.active;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "active", description = "Gets the most active cookie",
    mixinStandardHelpOptions = true)
public final class ActiveCookie implements Runnable {

  private static Logger logger = Logger.getLogger(ActiveCookie.class.getName());

  @Option(names = {"-f", "--file"}, description = "File we want to process")
  String filePath = "";

  @Override
  public void run() {
    // checking if filePath has been given or not
    if (filePath == null || filePath.equals("")) {
      logger.log(Level.SEVERE, "empty file path");
      return;
    }
    // trying to open the file
    File file = new File(filePath);
    Scanner sc = null;
    try {
      sc = new Scanner(file);
    } catch (FileNotFoundException e) {
      logger.log(Level.SEVERE, "file not found");
    }
    // now will read the file and gather all the cookie data
    List<String> cookieData = new ArrayList<>();
    while (true) {
      assert sc != null;
      if (!sc.hasNext()) {
        break;
      }
      cookieData.add(sc.nextLine().strip());
    }
    // printing cookie data
    logger.log(Level.INFO, cookieData.toString());
  }
}
