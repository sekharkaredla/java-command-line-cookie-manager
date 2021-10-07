package com.cookie.active;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "active", description = "Gets the most active cookie",
    mixinStandardHelpOptions = true)
public final class ActiveCookie implements Runnable {

  @Option(names = {"-f", "--file"}, description = "File we want to process")
  String filePath = "";

  @Override
  public void run() {
    System.out.println("Active cookie filter running...");
  }
}
