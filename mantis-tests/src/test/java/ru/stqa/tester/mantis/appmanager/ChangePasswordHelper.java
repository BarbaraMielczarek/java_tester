package ru.stqa.tester.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login (String username, String password){

    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username") ,username);
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void managementPage ()
  {
    click(By.cssSelector("a[href='/mantisbt-2.19.0/manage_overview_page.php'"));
    click(By.cssSelector("a[href='/mantisbt-2.19.0/manage_user_page.php'"));

  }

  public void resetPassword (String userName){
    //click(By.cssSelector("input[class='input-sm']"));
    type(By.cssSelector("input[class='input-sm']"), userName);
    click(By.cssSelector("input[value='Apply Filter']"));
    click(By.tagName("td"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void end (String confirmationLink, String password)
  {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

}
