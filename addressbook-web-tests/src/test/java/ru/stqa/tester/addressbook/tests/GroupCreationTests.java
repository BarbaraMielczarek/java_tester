package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test2", "test2", "test2"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
