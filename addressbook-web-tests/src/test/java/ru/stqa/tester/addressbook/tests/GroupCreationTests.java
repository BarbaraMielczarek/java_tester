package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test10", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
  }

}
