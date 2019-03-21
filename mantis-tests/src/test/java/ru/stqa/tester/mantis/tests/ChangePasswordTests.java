package ru.stqa.tester.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.tester.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;



public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException {
    app.newSession().login("admin", "root");
  }

  @AfterMethod
  public  void  stopMailServer (){
    app.mail().stop();
  }
}


