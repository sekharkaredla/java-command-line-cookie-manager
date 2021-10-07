package com.cookie;

import com.cookie.active.ActiveCookie;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Cookie-Manager", description = "...",
    mixinStandardHelpOptions = true, subcommands = {ActiveCookie.class})
public class CookieManagerCommand implements Runnable {

  @Option(names = {"-v", "--verbose"}, description = "...")
  boolean verbose;

  public static void main(String[] args) throws Exception {
    PicocliRunner.run(CookieManagerCommand.class, args);
  }

  public void run() {
    if (verbose) {
      System.out.println("Cookie Manager CLI says \"How you doin?\"");
    }
  }
}
