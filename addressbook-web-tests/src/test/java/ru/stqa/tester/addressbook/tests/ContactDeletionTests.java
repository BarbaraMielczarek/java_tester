package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletation(){

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.stopAllert();
  }
}
