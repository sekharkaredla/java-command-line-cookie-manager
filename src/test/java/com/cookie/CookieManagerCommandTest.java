package com.cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class CookieManagerCommandTest {

  @Test
  public void testWithCommandLineOption() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));

    try (ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)) {
      String[] args = new String[] {"active"};
      PicocliRunner.run(CookieManagerCommand.class, ctx, args);
      // Cookie-Manager
      assertTrue(baos.toString().contains("empty file path"));
    }
  }
}
