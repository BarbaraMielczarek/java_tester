package ru.stqa.tester.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("admin", "root"));
    assertTrue(session.isLoggedInAs("admin"));
  }
}
