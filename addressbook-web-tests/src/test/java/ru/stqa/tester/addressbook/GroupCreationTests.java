package ru.stqa.tester.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test2", "test2", "test2"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
