package ru.stqa.tester.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.tester.mantis.appmanager.HttpSession;
import ru.stqa.tester.mantis.model.MailMessage;
import ru.stqa.tester.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    String admin = app.getProperty("web.adminLogin");
    String admin_password = app.getProperty("web.adminPassword");
    String user_password = "password";
    app.changePassword().login(admin, admin_password);
    app.changePassword().managementPage();

    Iterator<UserData> iteratorUsers = app.db().users().iterator();
    UserData selectedUserData = iteratorUsers.next();
    String userName = selectedUserData.getUsername();
    String email = selectedUserData.getEmail();

    if (userName.equals("admin")) {
      selectedUserData = iteratorUsers.next();
      userName = selectedUserData.getUsername();
      email = selectedUserData.getEmail();
    }
    app.changePassword().resetPassword(userName);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.changePassword().end(confirmationLink, user_password);
    HttpSession session = app.newSession();
    assertTrue(session.login(userName, user_password));
    assertTrue(session.isLoggedInAs(userName));
  }
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {

    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  @AfterMethod
  public  void  stopMailServer (){
    app.mail().stop();
  }
}


