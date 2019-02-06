package ru.stqa.tester.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.tester.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillAddressBookEntry(new ContactData("Kamila", "Potocka", "Finance and Administration Manager ", "Niko", "Prosta 12, 00-850 Warszawa", "502698990", "225894990", "kamila.potocka@niko.com"));
    app.getContactHelper().submitAddressBookEntry();
    app.getContactHelper().returnToHomePage();

  }

}
