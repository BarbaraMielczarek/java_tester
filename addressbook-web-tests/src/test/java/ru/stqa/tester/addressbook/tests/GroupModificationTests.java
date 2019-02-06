package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {


  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test10", "testM10", "testM10a"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
